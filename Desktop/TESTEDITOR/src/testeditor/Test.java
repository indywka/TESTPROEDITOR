package testeditor;

import testeditor.contoller.Question;

import java.util.LinkedHashSet;
import java.util.List;

public class Test extends LinkedHashSet<Question> {
    private static Test t = null;

    private Test() {
    }

    public static Test getTest() {
        t = (t != null) ? t : new Test();
        return t;
    }



    public void update(List<Question> list) {
        this.clear();
        this.addAll(list);
    }
}