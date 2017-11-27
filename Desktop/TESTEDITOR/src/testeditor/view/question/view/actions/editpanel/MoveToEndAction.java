package testeditor.view.question.view.actions.editpanel;

import testeditor.contoller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveToEndAction extends MoveAction {

    public MoveToEndAction(JList<Question> qList, String title, String html_icon) {
        super(qList, title, html_icon);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        putQuestionToTheEnd(list.getSelectedValue());
        list.setSelectedIndex(list.getModel().getSize() - 1);
        list.updateUI();
    }
}