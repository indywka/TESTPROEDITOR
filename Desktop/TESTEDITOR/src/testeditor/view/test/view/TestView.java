package testeditor.view.test.view;

import testeditor.Test;
import testeditor.question.Question;
import testeditor.view.beauty.classes.ListRenderer;
import testeditor.view.beauty.classes.QListModel;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

/**
 * Панель, отображающая общий вид теста и кнопки управления его содержимым
 */

public class TestView extends JPanel {

    private final QListModel listModel;  // Модель для компонета JList со списком вопросов
    public JList<Question> questionList; //Список вопросов
    private ControlPanel controlPanel;  // управление файлом теста
    private EditPanel editPanel;  // управление элементами теста

    public TestView() {

        listModel = new QListModel();

        listModel.addListDataListener(new ListDataListener() {   //чтобы узнать, когда изменится содержмиое модели,
            // связываем ListDataListener c моделью списка
            @Override
            public void intervalAdded(ListDataEvent listDataEvent) {

                Test.getTest().update(Collections.list(listModel.elements()));

                if (!controlPanel.getSaveAsButton().isEnabled()) {

                    controlPanel.getSaveAsButton().setEnabled(true);
                    editPanel.getButtons().forEach(b -> b.setEnabled(true));

                }
            }

            @Override
            public void intervalRemoved(ListDataEvent listDataEvent) {
                Test.getTest().update(Collections.list(listModel.elements()));

                if (listModel.isEmpty()) {
                    controlPanel.getSaveAsButton().setEnabled(false);
                    editPanel.getButtons().forEach(b -> b.setEnabled(false));
                    editPanel.getCreateButton().setEnabled(true);
                }
            }

            @Override
            public void contentsChanged(ListDataEvent listDataEvent) {
                Test.getTest().update(Collections.list(listModel.elements()));
            }
        });

        //------- Создаем и настраиваем компоненты графического интерфейса -------//

        setLayout(new BorderLayout());

        questionList = new JList<>(listModel);
        questionList.setBackground(Color.GRAY);
        questionList.setCellRenderer(new ListRenderer());//покраска ячеек списка как панелей

        questionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) editPanel.getEditButton().doClick();

            }
        });

        questionList.addListSelectionListener(e ->
                questionList.ensureIndexIsVisible(questionList.getSelectedIndex()));

        JScrollPane scrollPane = new JScrollPane(questionList); // полоса прокрутки для списка
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        controlPanel = new ControlPanel(questionList);
        editPanel = new EditPanel(questionList);
        editPanel.setVisible(false);

        add(new JPanel(), BorderLayout.SOUTH);
        add(scrollPane);
        add(controlPanel, BorderLayout.NORTH);
        add(editPanel, BorderLayout.WEST);
    }

    public EditPanel getEditPanel() {
        return editPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}