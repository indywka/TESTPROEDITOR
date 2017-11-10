package testeditor.question;


public class Answer {

    public static final int MAX_DEGREE = 1;
    public static final int MIN_DEGREE = 0;

    private final int degree;
    private final String aText;


    public Answer(String text, int degree) {
        aText = text;
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
