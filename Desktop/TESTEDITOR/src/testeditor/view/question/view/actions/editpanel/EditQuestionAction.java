package testeditor.view.question.view.actions.editpanel;

import testeditor.Test;
import testeditor.contoller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class EditQuestionAction extends AbstractAction {

    private final JList list;

    public EditQuestionAction(JList questionsList) {

        list = questionsList;

        this.putValue(Action.NAME,
                "<html>" +
                        "<b><font color='#2aa5a5' size=+1>&#9998;&nbsp;&nbsp;&nbsp;</font></b>" +
                        "Редактировать" +
                        "</html>");
    }

    public void actionPerformed(ActionEvent event) {
        int index = list.getSelectedIndex();
        Question q = (Question) Test.getTest().toArray()[index];
        JFrame qFrame = q.getFrame();
        qFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                list.repaint();
            }
        });
        qFrame.setVisible(true);
    }
}