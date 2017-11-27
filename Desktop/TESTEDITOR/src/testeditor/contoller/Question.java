package testeditor.contoller;

import testeditor.view.question.view.QuestionFrame;

import java.util.ArrayList;
import java.util.List;


abstract public class Question implements Comparable<Question> {

    private String qText;
    private String qName;
    private List<Answer> answers;

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

    Question(String qName, String qText) {
        this.qName = qName;
        this.qText = qText;
        this.answers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (!(obj instanceof Question)) return false;
        return this.getQHead().equals(((Question) obj).getQHead());
    }

    @Override
    public int compareTo(Question q) {
        return this.getQHead().compareTo(q.getQHead());
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

    abstract public QuestionFrame getFrame();

    public void setAnswers(List<Answer> aList) {
        this.answers = aList;
    }
}

