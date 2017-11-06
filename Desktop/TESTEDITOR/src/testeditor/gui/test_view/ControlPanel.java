package testeditor.gui.test_view;

import testeditor.gui.services.VerticalButton;
import testeditor.gui.test_view.actions.CreateTestAction;
import testeditor.gui.test_view.actions.OpenTestAction;
import testeditor.gui.test_view.actions.SaveAsTestAction;
import testeditor.question.Question;

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

        saveAsButton = new VerticalButton(new SaveAsTestAction(questionList));
        saveAsButton.setEnabled(false);
        add(saveAsButton);


    }

    public JButton getSaveAsButton() {
        return saveAsButton;
    }

}