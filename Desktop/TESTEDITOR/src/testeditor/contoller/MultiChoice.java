package testeditor.contoller;

import testeditor.view.question.view.MultiChoiceFrame;
import testeditor.view.question.view.QuestionFrame;

import java.util.Collections;
import java.util.List;


public class MultiChoice extends Question {

    private MultiChoice(String qName, String qText, List<Answer> answers) {
        super(qName, qText, answers);
    }

    public MultiChoice() {
        this("", "", Collections.singletonList(new Answer()));
    }

    MultiChoice(String qName, String qText) {
        super(qName, qText);
    }

    public QuestionFrame getFrame() {
        return (frame == null) ? new MultiChoiceFrame(this) : frame;
    }
}
