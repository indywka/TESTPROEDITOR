package testeditor.gui.question_view.actions;

import testeditor.question.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Класс-слушатель для события открытия файла
 */
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
        this.putValue(Action.SHORT_DESCRIPTION, "Создать новый тест");
    }

    public void actionPerformed(ActionEvent event) {
        int index = list.getSelectedIndex();
        DefaultListModel<Question> listModel = (DefaultListModel<Question>) list.getModel();
        listModel.remove(index);
        if (!listModel.isEmpty()) {
            list.setSelectedIndex(0);
        }
    }
}