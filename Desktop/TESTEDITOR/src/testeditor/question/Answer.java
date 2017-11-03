package testeditor.question;

/**
 * Парсер для чтения файлов в формате Gift
 */
public class Answer {
    public static final int MAX_DEGREE = 100;
    public static final int MIN_DEGREE = 0;

    private int degree;
    private String aText;
    private String aComment;

    /**
     * @param text    - значение варианта ответа
     * @param degree  - указывает на степень правильности варианта ответа (от 0 до 1 с точностью до сотых)
     * @param comment - Комментарий (если есть)
     */
    public Answer(String text, int degree, String comment) {
        aText = text;
        aComment = comment;
        this.degree = degree;
    }

    public Answer(String text, int degree) {
        this(text, degree, "");
    }

    public Answer(String text) {
        this(text, Answer.MAX_DEGREE, "");
    }

    /**
     * @return возвращает строку с вариантом ответа
     */
    public String getAText() {
        return this.aText;
    }

    /**
     * @return возвращает строку с комментарием для вариантом ответа
     */
    public String getAComment() {
        return this.aComment;
    }

    /**
     * @return возвращает правильность варианта в процентах
     */
    public int getDegree() {
        return degree;
    }

}
