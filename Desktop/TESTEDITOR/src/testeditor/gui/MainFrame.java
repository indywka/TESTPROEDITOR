package testeditor.gui;

import testeditor.gui.test_view.TestView;

public class MainFrame extends BaseMainFrame {

    public MainFrame() {

        TestView testView = new TestView();
        add(testView);

    }
}
