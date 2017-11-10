package testeditor.question;

import testeditor.saver.Saver;
import testeditor.view.question.view.QuestionFrame;

import java.util.List;

abstract public class Question {

    private String qText;
    private String qName;
    private List<Answer> answers;
    ;

    /**
     * qText   - заголовок вопроса
     * answers - списочный массив вариантов ответа к вопросу
     */
    Question(String qName, String qText, List<Answer> answers) {
        this.answers = answers;
        answers.listIterator(); // to reset previously moved iterator
        this.qText = qText.trim();
        this.qName = qName;

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
