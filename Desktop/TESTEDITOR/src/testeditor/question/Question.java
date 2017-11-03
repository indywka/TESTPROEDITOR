package testeditor.question;

import testeditor.gui.question_view.QuestionFrame;
import testeditor.saver.Saver;

import java.util.List;

/**
 * абстарктный класс "Вопрос"
 */
abstract public class Question  {

    public final String TYPE;
    private String qText;
    private String qName;
    QuestionFrame frame = null;
    private List<Answer> answers;
    private float defaultGrade;
    private float penalty;

    /**
     * @param qText   - заголовок вопроса
     * @param answers - списочный массив вариантов ответа к вопросу
     */
    Question(String type, String qName, String qText, List<Answer> answers) {
        this.answers = answers;
        answers.listIterator(); // to reset previously moved iterator
        this.qText = qText.trim();
        this.qName = qName;
        this.TYPE = type;
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

    public float getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(float defaultGrade) {
        this.defaultGrade = defaultGrade;
    }

    public float getPenalty() {
        return penalty;
    }

    public void setPenalty(float penalty) {
        this.penalty = penalty;
    }

    public void setAnswers(List<Answer> aList) {
        this.answers = aList;
    }
}
