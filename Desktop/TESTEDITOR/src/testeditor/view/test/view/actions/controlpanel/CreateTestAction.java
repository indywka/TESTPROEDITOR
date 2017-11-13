package testeditor.view.test.view.actions.controlpanel;

import testeditor.Test;
import testeditor.question.Question;
import testeditor.view.MainFrame;
import testeditor.view.beauty.classes.QListModel;
import testeditor.view.test.view.ControlPanel;
import testeditor.view.test.view.EditPanel;
import testeditor.view.test.view.TestView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CreateTestAction extends AbstractAction {

    private final JList<Question> list;

    public CreateTestAction(JList<Question> qList) {

        this.list = qList;

        this.putValue(Action.NAME, "Создать");
        this.putValue(Action.SHORT_DESCRIPTION, "Создать новый тест");
        this.putValue(Action.SMALL_ICON, UIManager.getIcon("FileView.fileIcon"));
    }

    public void actionPerformed(ActionEvent event) {

        MainFrame parentFrame = (MainFrame) SwingUtilities.getRoot((Component) event.getSource());
        parentFrame.setTitle("Редактор тестов");

        Test.getTest().clear();
        ((DefaultListModel) list.getModel()).clear();
        QListModel listModel = (QListModel) list.getModel();
        listModel.removeAllElements();

        TestView testView = (TestView) list.getParent().getParent().getParent(); //возвращаем родительский элемент компонента...слои
        if (!testView.getEditPanel().isVisible()) testView.getEditPanel().setVisible(true);

        EditPanel editPanel = testView.getEditPanel();
        editPanel.getButtons().forEach(b -> b.setEnabled(false));
        editPanel.getCreateButton().setEnabled(true);

        ControlPanel controlPanel = testView.getControlPanel();
        controlPanel.getSaveAsButton().setEnabled(false);


    }
}
