package testeditor.view.question.view;

import testeditor.question.Answer;
import testeditor.question.Question;
import testeditor.view.beauty.classes.GBC;
import testeditor.view.beauty.classes.QTextArea;
import testeditor.view.beauty.classes.error.message.SaveQuestionException;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;


public class MultiChoiceFrame extends QuestionFrame {
    private final List<Answer> aList;
    private final List<JCheckBox> checkBoxList = new ArrayList<>();
    private final List<JSpinner> spinnerList = new ArrayList<>();
    private final JPanel answers = new JPanel();
    private int aCount;

    public MultiChoiceFrame(Question q) {
        super(q);

        GridBagLayout gbl = new GridBagLayout();
        answers.setLayout(gbl);

        String[] titles = {
                "Верно/<br>Неверно",
                "Вариант ответа",
                "-",
                "-",
                "Удалить"
        };
        addTitles(titles);

        answers.add(new JSeparator(), new GBC(0, 1, 9, 1, 0, 0, 0, 0).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));

        aList = q.getAnswerList();

        addAnswers();
        checkAnswers();

        JButton addButton = new JButton("<html><font color='green' size=+1>&nbsp;<b>&#10010;&nbsp;</b></font>Добавить&nbsp;</html>");

        answerPanel.add(answers, BorderLayout.NORTH);

        JPanel addButtonPanel = new JPanel(new FlowLayout());
        addButtonPanel.add(addButton);

        aCount = aList.size();
        addButton.addActionListener(e -> {
            addAnswerAtAnswerPanel(aCount * 2 + 1, "", 0);
            answers.add(new JSeparator(), new GBC(0, aCount * 2 + 2, 9, 1, 0, 0, 0, 0).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
            aScrollPane.getViewport()
                    .setViewPosition(
                            new Point(aScrollPane.getX(),
                                    aScrollPane.getHeight()));
            answers.updateUI();
            aCount++;
            checkAnswers();
        });

        answerPanel.add(addButtonPanel, BorderLayout.CENTER);
    }

    private void addTitles(String[] titles) {
        for (int colNum = 0; colNum < titles.length; colNum++) {
            answers.add(new JLabel("<html><p><b>" + titles[colNum] + "</b></p></html>"),
                    new GBC(colNum + colNum, 0, 1, 1, 0, 0, 0, 0).setFill(GBC.HORIZONTAL).setInsets(0, 5, 0, 5));
            if (colNum + 1 != titles.length) {
                answers.add(new JSeparator(JSeparator.VERTICAL),
                        new GBC(colNum + colNum + 1, 0, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));
            }
        }
    }

    private void addAnswers() {
        for (int i = 0; i < aList.size(); i++) {
            addAnswerAtAnswerPanel(i + i + 2, aList.get(i).getAText(), aList.get(i).getDegree());
            answers.add(new JSeparator(), new GBC(0, i + i + 3, 9, 1, 0, 0, 0, 0).setFill(GBC.BOTH).setInsets(0, 0, 5, 0));
        }
    }

    private void addAnswerAtAnswerPanel(int pos, String text, int degree) {
        JCheckBox check = new JCheckBox();
        check.setSelected(degree > 0);
        checkBoxList.add(check);

        check.addChangeListener(event -> checkAnswers());

        answers.add(check, new GBC(0, pos, 1, 1, 0, 0, 0, 0).setInsets(5, 5, 5, 5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(1, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));

        QTextArea answerText = new QTextArea(text);

        answerText.addCaretListener(e -> answerText.changeSize());
        answerText.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                answerText.changeSize();
                answers.updateUI();
            }

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                answerText.changeSize();
                answers.updateUI();
            }
        });

        fields.add(answerText);
        answers.add(answerText, new GBC(2, pos, 1, 1, 0, 0, 100, 100).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(3, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));


        answers.add(new JSeparator(), new GBC(4, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.BOTH).setInsets(5, 5, 5, 5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(5, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));


        getSaveButton().setEnabled(false);
        answers.add(new JSeparator()
                , new GBC(6, pos, 1, 1, 0, 0, 0, 0).setAnchor(GBC.BASELINE).setFill(GBC.HORIZONTAL).setInsets(5, 5, 5, 5));
        answers.add(new JSeparator(JSeparator.VERTICAL), new GBC(7, pos, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));

        JButton delButton = new JButton("<html><font color='red'><b>&nbsp;&#10006;&nbsp;</b></font></html>");
        delButton.addActionListener(e -> deleteAnswer(answers.getComponentZOrder(delButton)));
        answers.add(delButton, new GBC(8, pos, 1, 1, 0, 0, 0, 0).setAnchor(GBC.BASELINE).setInsets(5, 10, 5, 5));
    }

    private void deleteAnswer(int delButtonIndex) {
        for (int i = -1; i < getColsNumber(); i++) {
            answers.remove(delButtonIndex - i);
        }
        updateCheckBox();
        answers.updateUI();
        aCount--;
        checkAnswers();
    }

    private void updateCheckBox() {
        checkBoxList.clear();
        for (int i = 0; i < answers.getComponentCount(); i++) {
            JComponent comp = (JComponent) answers.getComponent(i);
            if (comp instanceof JCheckBox) {
                checkBoxList.add((JCheckBox) comp);
            }
        }
    }

    private void checkAnswers() {
        int countSelected = 0;
        String checkBoxErrorMessage = "";

        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                getSaveButton().setEnabled(true);
                countSelected += 1;

            }
        }
        if (countSelected == 0) {
            getSaveButton().setEnabled(false);
        }

        ErrorLabel.info(DEFAULT_MESSAGE);

        if (countSelected < 2 && countSelected == 0) {
            checkBoxErrorMessage = "Хотя бы один вариант ответа должен быть отмечен, как правильный";
        } else {
            if (checkBoxList.stream().allMatch(JCheckBox::isSelected)) {
                checkBoxErrorMessage = "Все варианты ответа не могут быть правильными";
                getSaveButton().setEnabled(false);
            }
        }
        if (!checkBoxErrorMessage.isEmpty()) {
            ErrorLabel.error(checkBoxErrorMessage);
        }

    }

    protected List<Answer> collectAnswers() throws SaveQuestionException {
        List<Answer> aList = new ArrayList<>();
        int cols = getColsNumber();
        int rows = getRowsNumber();
        int compsNumber = 0;
        for (int i = 0; i < rows; i++) {
            if (compsNumber == answers.getComponentCount()) {
                break;
            }
            if (answers.getComponent(compsNumber) instanceof JSeparator) {
                compsNumber++;
                continue;
            }
            String text = "";
            int degree = Answer.MIN_DEGREE;
            int textCompCount = 0;

            for (int j = 0; j < cols; j++, compsNumber++) {
                Component comp = answers.getComponent(compsNumber);
                if (comp instanceof JTextComponent) {
                    if (textCompCount == 0) {
                        text = ((JTextComponent) comp).getText();
                        textCompCount++;
                    }
                } else if (comp instanceof JCheckBox) {
                    if (((JCheckBox) comp).isSelected()) {
                        degree = Answer.MAX_DEGREE;
                        updateCheckBox();
                    } else {
                        degree = Answer.MIN_DEGREE;
                        updateCheckBox();
                    }
                }
            }
            if (!text.isEmpty()) {
                aList.add(new Answer(text, degree));
            } else if (degree != 0) {
                throw new SaveQuestionException("Вы оставили пустым отмеченный вариант ответа");
            }
        }

        if (aList.isEmpty())
            throw new SaveQuestionException("Нет ни одного заполненного варианта ответа");
        if (aList.stream().noneMatch(answer -> answer.getDegree() != 0)) {
            throw new SaveQuestionException("Среди заполненных вариантов ответа нет ни одного правильного");
        }
        if (aList.size() == 1)
            throw new SaveQuestionException("Для этого типа вопроса не допустим только один вариант ответа");
        return aList;
    }

    private int getColsNumber() {
        GridBagLayout gbl = (GridBagLayout) answers.getLayout();
        int[][] dim = gbl.getLayoutDimensions();
        return dim[0].length;
    }

    private int getRowsNumber() {
        GridBagLayout gbl = (GridBagLayout) answers.getLayout();
        int[][] dim = gbl.getLayoutDimensions();
        return dim[1].length;
    }
}