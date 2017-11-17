package testeditor.view.question.view.actions.editpanel;

import testeditor.question.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class MoveToBeginAction extends MoveAction {

    public MoveToBeginAction(JList<Question> qList, String title, String html_icon) {
        super(qList, title, html_icon);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        putQuestionToTheBegin(list.getSelectedValue());
        list.setSelectedIndex(0);
        list.updateUI();
    }
}