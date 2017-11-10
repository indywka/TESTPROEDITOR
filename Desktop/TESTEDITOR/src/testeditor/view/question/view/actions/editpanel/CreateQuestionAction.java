package testeditor.view.question.view.actions.editpanel;

import testeditor.view.beauty.classes.QListModel;
import testeditor.question.MultiChoice;
import testeditor.question.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class CreateQuestionAction extends AbstractAction {

    private final JList<Question> list;
    private Question q = null;

    public CreateQuestionAction(JList<Question> qList) {
        list = qList;
        this.putValue(Action.NAME, "<html>" +
                "<font color='#2aa5a5' size=+1>" +
                "<b>&#10010;&nbsp;&nbsp;&nbsp;</b>" +
                "</font>" +
                "Создать" +
                "</html>"
        );
    }

    public void actionPerformed(ActionEvent event) {

        q = new MultiChoice();
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

