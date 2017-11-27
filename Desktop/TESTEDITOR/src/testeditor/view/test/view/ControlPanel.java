package testeditor.view.test.view;

import testeditor.contoller.Question;
import testeditor.view.test.view.actions.controlpanel.CreateTestAction;
import testeditor.view.test.view.actions.controlpanel.OpenTestAction;
import testeditor.view.test.view.actions.controlpanel.SaveAsTestAction;

import javax.swing.*;
import java.awt.*;

/**
 * Панель для управления созданием, открытием и сохранением теста
 */
public class ControlPanel extends JPanel {
    private JButton saveAsButton;


    ControlPanel(JList<Question> questionList) {

        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        JButton createButton = new VerticalButton(new CreateTestAction(questionList));
        add(createButton);

        JButton openButton = new VerticalButton(new OpenTestAction(questionList));
        add(openButton);

        saveAsButton = new VerticalButton(new SaveAsTestAction());
        saveAsButton.setEnabled(false);
        add(saveAsButton);

    }

    public JButton getSaveAsButton() {
        return saveAsButton;
    }

    private class VerticalButton extends JButton {
        VerticalButton(Action a) {
            super(a);
            setVerticalTextPosition(AbstractButton.BOTTOM);
            setHorizontalTextPosition(AbstractButton.CENTER);
        }
    }

}