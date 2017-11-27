package testeditor.view.question.view;

import testeditor.contoller.Answer;
import testeditor.contoller.Question;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ShortAnswerFrame extends QuestionFrame {
    private QTextArea answerText;

    public ShortAnswerFrame(Question q) {
        super(q);

        setSize((int) (this.initialHeight / 1.5),
                (int) (this.initialHeight / 1.5));

        List<Answer> answerList = q.getAnswerList();

        answerText = new QTextArea(answerList.get(0).getAText());

        fields.add(answerText);


        JPanel rightAnswerPanel = new JPanel(new BorderLayout());

        rightAnswerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Правильный ответ:"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        rightAnswerPanel.add(answerText);


        GroupLayout answerLayout = new GroupLayout(answerPanel);
        answerLayout.setAutoCreateContainerGaps(true);
        answerLayout.setAutoCreateGaps(true);

        answerLayout.setHorizontalGroup(
                answerLayout.createParallelGroup()
                        .addComponent(rightAnswerPanel,
                                0,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
        );

        answerLayout.setVerticalGroup(
                answerLayout.createSequentialGroup()
                        .addComponent(rightAnswerPanel,
                                GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE)
        );

        answerPanel.setLayout(answerLayout);

        add(answerPanel);
    }

    protected List<Answer> collectAnswers() {
        List<Answer> aList = new ArrayList<>();

        Answer answer;

        answer = new Answer(answerText.getText(), Answer.MAX_DEGREE);
        aList.add(answer);

        return aList;
    }
}
