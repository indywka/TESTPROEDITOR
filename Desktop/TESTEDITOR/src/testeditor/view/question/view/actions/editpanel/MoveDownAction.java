package testeditor.view.question.view.actions.editpanel;

import testeditor.contoller.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class MoveDownAction extends MoveAction {

    public MoveDownAction(JList<Question> qList, String title, String html_icon) {
        super(qList, title, html_icon);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int indexOfSelected = list.getSelectedIndex();
        if (indexOfSelected + 1 == list.getModel().getSize()) {
            return;
        }
        swapElements(indexOfSelected, indexOfSelected + 1);
        list.setSelectedIndex(indexOfSelected + 1);
        list.updateUI();
    }
}