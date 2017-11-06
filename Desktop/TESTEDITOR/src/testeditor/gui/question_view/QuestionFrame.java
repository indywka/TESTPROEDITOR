package testeditor.gui.question_view;

import testeditor.gui.BaseMainFrame;
import testeditor.gui.services.HintLabel;
import testeditor.gui.services.QLabel;
import testeditor.gui.services.QTextArea;
import testeditor.gui.services.exceptions.SaveQuestionException;
import testeditor.question.Answer;
import testeditor.question.Question;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;


abstract public class QuestionFrame extends BaseMainFrame {

    final String DEFAULT_MESSAGE = "Вы можете добавлять новые, изменять или удалять имеющиеся варианты ответа";

    final JPanel answerPanel = new JPanel();

    final ArrayList<JTextComponent> fields = new ArrayList<>();

    final JScrollPane aScrollPane;

    private final Question question;

    final HintLabel hintLabel;

    private final QTextArea nameTextArea;

    private final QTextArea questionTextArea;

    private final JButton saveButton;



    QuestionFrame(Question thisQuestion) {

        this.question = thisQuestion;

        int WIDTH = 500;
        setMaximumSize(new Dimension(WIDTH, SCREEN_HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT_MIN));

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setVisible(true);
        setLayout(new BorderLayout(25,25));

        JPanel northPanel = new JPanel();
        GroupLayout northLayout = new GroupLayout(northPanel);
        northPanel.setLayout(northLayout);
        northLayout.setAutoCreateContainerGaps(true);
        northLayout.setAutoCreateGaps(true);
        TitledBorder northBorder = BorderFactory.createTitledBorder("<html><I>Тип вопроса: </I>" + thisQuestion.TYPE + "</html>");
        northBorder.setTitleJustification(TitledBorder.CENTER);
        northPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 5, 5, 5), northBorder));

        QLabel labelName = new QLabel("<html><b>Название:</b></html>");
        QLabel labelQuestion = new QLabel("<html><b>Вопрос:</b></html>");

        nameTextArea = new QTextArea(thisQuestion.getQName());
        questionTextArea = new QTextArea(thisQuestion.getQText());

        fields.add(nameTextArea);
        fields.add(questionTextArea);

        northLayout.setHorizontalGroup(northLayout.createSequentialGroup()
                .addGroup(northLayout.createParallelGroup(LEADING)
                        .addComponent(labelName, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelQuestion, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(northLayout.createParallelGroup(LEADING)
                        .addComponent(nameTextArea, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(questionTextArea, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        northLayout.linkSize(SwingConstants.HORIZONTAL, labelName, labelQuestion);

        northLayout.setVerticalGroup(northLayout.createSequentialGroup()
                .addGroup(northLayout.createParallelGroup(BASELINE)
                        .addComponent(labelName, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameTextArea, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(northLayout.createParallelGroup(BASELINE)
                        .addComponent(labelQuestion, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(questionTextArea, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(northPanel, BorderLayout.NORTH);

        answerPanel.setLayout(new BorderLayout(10, 10));

        aScrollPane = new JScrollPane(answerPanel);
        aScrollPane.setViewportBorder(null);
        aScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        TitledBorder aScrollPaneBorder = BorderFactory.createTitledBorder("Варианты ответа");
        aScrollPaneBorder.setTitleJustification(TitledBorder.CENTER);
        aScrollPane.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), aScrollPaneBorder));
        add(aScrollPane);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));

        saveButton = new JButton("Сохранить", new ImageIcon("src/testeditor/gui/img/save.png"));
        saveButton.addActionListener(e -> saveQuestion());

        JButton cancelButton = new JButton("Отмена", UIManager.getIcon("FileChooser.cancelIcon"));
        cancelButton.addActionListener(e -> this.setVisible(false));

        savePanel.add(saveButton);
        savePanel.add(cancelButton);

        JPanel hintPanel = new JPanel();
        hintPanel.setLayout(new BorderLayout());

        hintLabel = new HintLabel();
        hintLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        hintPanel.add(hintLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(savePanel, BorderLayout.NORTH);
        bottomPanel.add(hintPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void saveQuestion() {
        try {
            String name = nameTextArea.getText();
            String text = questionTextArea.getText();
            List<Answer> aList = collectAnswers();
            aList.removeIf(a -> a.getAText().isEmpty());

            if (name.isEmpty()) {
                name = "";
            }
            if (text.isEmpty()) {
                throw new SaveQuestionException("Поле \"Название\" должно быть заполнено");
            }
            if (aList.isEmpty()) {
                throw new SaveQuestionException("Вопрос должен иметь хотя бы один вариант ответа");
            }
            question.setQName(name);
            question.setQText(text);
            question.setAnswers(aList);
        } catch (SaveQuestionException e) {
            JOptionPane.showMessageDialog(this
                    , e.getMessage()
                    , "Ошибка!"
                    , JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        this.dispose();
    }

    JButton getSaveButton() {
        return saveButton;
    }

    abstract protected List<Answer> collectAnswers() throws SaveQuestionException;
}
