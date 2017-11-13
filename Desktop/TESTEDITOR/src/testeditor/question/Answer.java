package testeditor.question;


public class Answer {
    public static final int MAX_DEGREE = 100;
    public static final int MIN_DEGREE = 0;

    private int degree;
    private String aText;


    /**
     * text   - значение варианта ответа
     * degree - указывает на степень правильности варианта ответа (от 0 до 1 с точностью до сотых)
     */
    public Answer(String text, int degree) {
        this.aText = text;
        this.degree = degree;
    }

    Answer() {
        this("", Answer.MAX_DEGREE);
    }

    public String getAText() {
        return this.aText;
    }

    public int getDegree() {
        return this.degree;
    }

}
