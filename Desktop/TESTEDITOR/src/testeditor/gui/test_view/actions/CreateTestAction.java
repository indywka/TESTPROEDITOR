package testeditor.gui.test_view.actions;

import testeditor.Test;
import testeditor.gui.test_view.EditPanel;
import testeditor.gui.test_view.TestView;
import testeditor.question.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;



public class CreateTestAction extends AbstractAction {

    private JList<Question> list;

    public CreateTestAction(JList<Question> qList) {

        this.list = qList;

        this.putValue(Action.NAME, "Создать");
        this.putValue(Action.SHORT_DESCRIPTION, "Создать новый тест");
        this.putValue(Action.SMALL_ICON, UIManager.getIcon("FileView.fileIcon"));
    }

    public void actionPerformed(ActionEvent event) {

        Test.getTest().clear();
        ((DefaultListModel) list.getModel()).clear();

        TestView tv = (TestView) list.getParent().getParent().getParent(); //возвращаем родительский элемент компонента...слои
//testeditor.gui.test_view.TestView[,0,0,1262x673,invalid,layout=java.awt.BorderLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=]


        if (!tv.getEditPanel().isVisible()) tv.getEditPanel().setVisible(true);


        EditPanel ep = tv.getEditPanel();
        ep.getButtons().forEach(b -> b.setEnabled(false));
        ep.getCreateButton().setEnabled(true);
    }
}
