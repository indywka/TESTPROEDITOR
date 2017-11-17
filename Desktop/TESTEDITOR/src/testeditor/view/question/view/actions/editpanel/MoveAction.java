package testeditor.view.question.view.actions.editpanel;

import testeditor.question.Question;
import testeditor.view.beauty.classes.QListModel;

import javax.swing.*;
import java.awt.event.ActionEvent;


abstract public class MoveAction extends AbstractAction {
    protected JList<Question> list;

    MoveAction(JList<Question> qList, String title, String html_icon) {
        list = qList;
        String where = title.substring(0, 1).toLowerCase() + title.substring(1);

        this.putValue(Action.NAME,
                "<html>" +
                        "<b><font color='#4682B4' size=+1>" + html_icon + "&nbsp;&nbsp;&nbsp;</font></b>" +
                        title +
                        "</html>");

        this.putValue(Action.SHORT_DESCRIPTION, "Переместить " + where);
    }

    @Override
    abstract public void actionPerformed(ActionEvent event);

    void swapElements(int pos1, int pos2) {
        DefaultListModel<Question> listModel = (DefaultListModel<Question>) list.getModel();
        Question tmp = listModel.getElementAt(pos1);
        listModel.set(pos1, listModel.get(pos2));
        listModel.set(pos2, tmp);
    }

    void putQuestionToTheEnd(Question question) {
        DefaultListModel<Question> listModel = (DefaultListModel<Question>) list.getModel();
        QListModel listModel1 = (QListModel) list.getModel();
        listModel1.removeElement(question);
        listModel.removeElement(question);
        listModel.addElement(question);
    }

    void putQuestionToTheBegin(Question question) {
        DefaultListModel<Question> listModel = (DefaultListModel<Question>) list.getModel();
        QListModel listModel1 = (QListModel) list.getModel();
        listModel1.removeElement(question);
        listModel.removeElement(question);
        listModel.add(0, question);

    }
}
