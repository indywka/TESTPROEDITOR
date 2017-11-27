package testeditor.view.frame.view;

import testeditor.view.test.view.TestView;

public class MainFrame extends BaseMainFrame {

    public MainFrame() {

        TestView testView = new TestView();
        add(testView);

    }
}
