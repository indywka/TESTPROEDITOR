package testeditor.gui.services;

import testeditor.Test;
import testeditor.question.Question;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Arrays;
import java.util.Vector;


public class QListModel extends DefaultListModel<Question> implements DocumentListener {

    private Vector<Question> defaultList;

    private String lastFilter = "";

    public QListModel() {
        defaultList = new Vector<>();
    }

    public void addElement(Question question) {
        defaultList.add(question);
        filter(lastFilter);
        fireIntervalAdded(this, defaultList.size(), defaultList.size());
    }

    private void filter(String search) {

        super.clear();

        for (Question question : defaultList) {
            if (question.getQHead().toLowerCase().indexOf(search.toLowerCase(), 0) != -1)
                super.addElement(question);
        }
        fireContentsChanged(this, 0, getSize());
    }


    // Реализация интерфейса Document Listener: методы insertUpdate, removeUpdate, changedUpdated

    /* System.err — ваше сообщение тут же выводится на консоль,
    но когда пишете в System.out, то оно может на какое-то время быть буферизированно.
    Stacktrace необработанного исключение выводится через System.err,
    что позволяет им обгонять «обычные» сообщения.*/

    public void insertUpdate(DocumentEvent event) {
        Document document = event.getDocument();
        try {
            lastFilter = document.getText(0, document.getLength());
            filter(lastFilter);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    public void removeUpdate(DocumentEvent event) {
        Document document = event.getDocument();
        try {
            lastFilter = document.getText(0, document.getLength());
            filter(lastFilter);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    public void changedUpdate(DocumentEvent event) {
    }


    public void update(JList<Question> list) {

        try {
            ListDataListener[] listeners = this.getListDataListeners();

            //гасим обработку событий и работаем со списком. Потом снова включаем обработку событий
            Arrays.stream(listeners).forEach(this::removeListDataListener);
            this.clear();
            Test.getTest().forEach(this::addElement);
            Arrays.stream(listeners).forEach(this::addListDataListener);

            //генерируем событие добавления и обновляем UI вручную, т.к. во время обновления списка
            //эти события были погашены вместе с остальными
            fireIntervalAdded(list, 0, Test.getTest().size() - 1); // Here the event is fired
            list.updateUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}