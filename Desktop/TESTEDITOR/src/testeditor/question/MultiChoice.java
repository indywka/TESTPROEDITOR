package testeditor.question;

import testeditor.saver.Saver;
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

    public String getLine(Saver saver) {
        return saver.doLineForMultiChoice(this);
    }

    public QuestionFrame getFrame() {
        return new MultiChoiceFrame(this);
    }
}
