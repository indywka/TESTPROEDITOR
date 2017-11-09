package testeditor.gui.test_view;

import testeditor.gui.question_view.actions.CreateQuestionAction;
import testeditor.gui.question_view.actions.EditQuestionAction;
import testeditor.gui.question_view.actions.RemoveQuestionAction;
import testeditor.gui.services.EditPanelButton;
import testeditor.question.Question;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Панель управления элементами списка
 */

public class EditPanel extends JPanel {

    private JButton editButton, createButton;
    private JList<Question> list;//was final
    private ArrayList<JButton> buttons;

    EditPanel(JList<Question> list) {

        this.list = list;

        EditingGroup editingGroup = new EditingGroup();
        FindField findField = new FindField();

        GroupLayout editPanelLayout = new GroupLayout(this); // Групповой компоновщик для EditPanel

        setLayout(editPanelLayout);
        editPanelLayout.setAutoCreateContainerGaps(true);//Устанавливает, должен ли автоматически создаваться разрыв между контейнером и компонентами,
        // которые касаются границы контейнера.
        editPanelLayout.setAutoCreateGaps(true);//Устанавливает, должен ли автоматически создаваться промежуток между компонентами.

        editPanelLayout.setHorizontalGroup(editPanelLayout.createParallelGroup()
                .addComponent(editingGroup)
                .addComponent(findField)
        );

        editPanelLayout.setVerticalGroup(editPanelLayout.createSequentialGroup()
                .addComponent(editingGroup
                        , GroupLayout.PREFERRED_SIZE
                        , GroupLayout.PREFERRED_SIZE
                        , GroupLayout.PREFERRED_SIZE)
                .addComponent(findField
                        , GroupLayout.PREFERRED_SIZE
                        , GroupLayout.PREFERRED_SIZE
                        , GroupLayout.PREFERRED_SIZE)
        );
    }


    public JButton getCreateButton() {
        return createButton;
    }

    JButton getEditButton() {
        return editButton;
    }

    public List<JButton> getButtons() {
        return buttons;
    }

    /**
     * Группа с кнопками редактирования, создания и удаления вопроса
     */

    class EditingGroup extends JPanel {
        EditingGroup() {
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
                    new TitledBorder("Редактировать: ")));

            GroupLayout editingPanelLayout = new GroupLayout(this);
            setLayout(editingPanelLayout);

            editingPanelLayout.setAutoCreateContainerGaps(true);
            editingPanelLayout.setAutoCreateGaps(true);

            editButton = new EditPanelButton(new EditQuestionAction(list));

            createButton = new EditPanelButton(new CreateQuestionAction(list));

            JButton deleteButton = new EditPanelButton(new RemoveQuestionAction(list));

            buttons = new ArrayList<>();
            buttons.add(editButton);
            buttons.add(createButton);
            buttons.add(deleteButton);

            editingPanelLayout.setHorizontalGroup(
                    editingPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(editButton)
                            .addComponent(createButton)
                            .addComponent(deleteButton)
            );

            editingPanelLayout.setVerticalGroup(
                    editingPanelLayout.createSequentialGroup()
                            .addComponent(editButton)
                            .addComponent(createButton)
                            .addComponent(deleteButton)
            );
        }
    }

    /**
     * Поле для поиска
     */

    class FindField extends JPanel {
        final JTextField findText = new JTextField();

        FindField() {
            setLayout(new BorderLayout());

            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Поиск: "),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));

            add(findText);

            findText.getDocument().addDocumentListener((DocumentListener) list.getModel());   //отслеживание изменений в документе

        }


    }

}