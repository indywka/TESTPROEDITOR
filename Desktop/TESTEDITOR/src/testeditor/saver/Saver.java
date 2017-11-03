package testeditor.saver;

import testeditor.Test;
import testeditor.question.Question;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * абстрактный класс, содержащий методы сохранения теста в файл
 */
abstract public class Saver {

    private String filepath;

    Saver(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Запись теста в файл
     *
     * @param answersText - строка для записи
     */
    private void toFile(String answersText) {

        File file = new File(filepath);

        try {

            //если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            out.println(answersText);
            out.close();
        } catch (IOException e) {
            System.err.println("Не могу записать данные в файл " + filepath);
        }
    }

    public void save() {
        toFile(getText());
    }

    private String getText() {
        StringBuilder text = new StringBuilder();
        for (Question q : Test.getTest()) {
            text.append(q.getLine(this)).append("\n\n");
        }
        return text.toString();
    }

    abstract public String doLineForMultiChoice(Question q);

    abstract public String doLineForShortAnswer(Question q);

}
