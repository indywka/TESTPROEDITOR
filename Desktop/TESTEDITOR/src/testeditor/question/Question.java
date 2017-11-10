package testeditor.question;

import testeditor.view.question.view.*;
import testeditor.saver.*;

import java.util.List;


abstract public class Question {

    private String qText;
    private String qName;
    private List<Answer> answers;

    /**
     * @param qText   - заголовок вопроса
     * @param answers - списочный массив вариантов ответа к вопросу
     */
    Question(String qName, String qText, List<Answer> answers) {
        this.answers = answers;
        answers.listIterator();
        this.qText = qText.trim();
        this.qName = qName;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (!(obj instanceof Question)) return false;
        return this.getQHead().equals(((Question) obj).getQHead());
    }

    public String getQHead() {
        return this.getQName() + this.getQText();
    }

    public String toString() {
        return qText;
    }

    public String getQText() {
        return this.qText;
    }

    public void setQText(String qText) {
        this.qText = qText;
    }

    public String getQName() {
        return this.qName;
    }

    public void setQName(String qName) {
        this.qName = qName;
    }

    public List<Answer> getAnswerList() {
        return this.answers;
    }

    abstract public String getLine(Saver saver);

    abstract public QuestionFrame getFrame();

    public void setAnswers(List<Answer> aList) {
        this.answers = aList;
    }
}
