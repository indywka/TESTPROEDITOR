package testeditor.gui.services;

import javax.swing.*;
import java.util.function.Consumer;

/**

 */
public class HintLabel extends JLabel {
    public HintLabel() {
        super();
    }

    public void error(Consumer<JComponent> func, JComponent comp, String text) {
        setText("<html><p><font color='red'><b>" + text + "</font></b></p></html>");
        func.accept(comp);
    }

    public void info(String text) {
        setText("<html><p>" + text + "</p></html>");
    }
}