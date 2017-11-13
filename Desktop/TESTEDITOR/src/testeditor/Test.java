package testeditor;

import testeditor.question.Question;

import java.util.LinkedHashSet;
import java.util.List;

public class Test extends LinkedHashSet<Question> {
    private static String filePath = "";
    private static Test t = null;

    private Test() {
    }

    public static Test getTest() {
        t = (t != null) ? t : new Test();
        return t;
    }

    public static Test createTest() {
        t = new Test();
        return t;
    }

    public String getFilePath() {
        return filePath;
    }

    public void update(List<Question> list) {
        this.clear();
        this.addAll(list);
    }
}