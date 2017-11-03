package testeditor.gui.question_view.actions;

import testeditor.gui.services.QListModel;
import testeditor.question.MultiChoice;
import testeditor.question.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Класс-слушатель для события открытия файла
 */
public class CreateQuestionAction extends AbstractAction {

    private JList<Question> list;
    private Question q = new MultiChoice();

    public CreateQuestionAction(JList<Question> qList) {
        list = qList;
        this.putValue(Action.NAME, "<html>" +
                "<font color='#2aa5a5' size=+1>" +
                "<b>&#10010;&nbsp;&nbsp;&nbsp;</b>" +
                "</font>" +
                "Создать" +
                "</html>"
        );
        this.putValue(Action.SHORT_DESCRIPTION, "Создать новый тест");
    }

    public void actionPerformed(ActionEvent event) {

        JFrame qFrame = q.getFrame();

        qFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                QListModel listModel = (QListModel) list.getModel();
                listModel.addElement(CreateQuestionAction.this.q);
                list.setSelectedIndex(listModel.getSize() - 1);
            }
        });
    }
}

