package testeditor.question;

import testeditor.gui.question_view.MultiChoiceFrame;
import testeditor.gui.question_view.QuestionFrame;
import testeditor.saver.Saver;

import java.util.Collections;
import java.util.List;


public class MultiChoice extends Question {

    public MultiChoice(String qName, String qText, List<Answer> answers) {
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
