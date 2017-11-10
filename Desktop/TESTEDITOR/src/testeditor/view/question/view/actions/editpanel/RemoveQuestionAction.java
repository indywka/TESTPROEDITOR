package testeditor.view.question.view.actions.editpanel;

import testeditor.view.beauty.classes.QListModel;
import testeditor.question.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class RemoveQuestionAction extends AbstractAction {

    private JList<Question> list;

    public RemoveQuestionAction(JList<Question> qList) {
        list = qList;
        this.putValue(Action.NAME, "<html>" +
                "<font color='#2aa5a5' size=+1>" +
                "<b>&#10006;&nbsp;&nbsp;&nbsp;</b>" +
                "</font>" +
                "Удалить" +
                "</html>"
        );
    }

    public void actionPerformed(ActionEvent event) {
        int index = list.getSelectedIndex();
        DefaultListModel<Question> listModel = (DefaultListModel<Question>) list.getModel();
        QListModel listModel1 = (QListModel) list.getModel();
        listModel1.removeElement(list.getModel().getElementAt(index));

        listModel.remove(index);
        if (!listModel.isEmpty()) {
            list.setSelectedIndex(0);
        }
    }
}