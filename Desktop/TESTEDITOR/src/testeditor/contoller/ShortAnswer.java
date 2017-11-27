package testeditor.contoller;

import testeditor.view.question.view.ShortAnswerFrame;
import testeditor.view.question.view.QuestionFrame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ShortAnswer extends Question {

    private ShortAnswer(String qName, String qText, List<Answer> answers) {
        super(qName, qText, answers);
    }

    public ShortAnswer() {
        this("", "", Collections.singletonList(new Answer()));
    }

    ShortAnswer(String qName, String qText) {
        super(qName, qText);
    }

    public QuestionFrame getFrame() {
        return (frame == null) ? new ShortAnswerFrame(this) : frame;
    }
}
