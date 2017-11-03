package testeditor.gui.services;

import javax.swing.*;

/**

 */
public class VerticalButton extends JButton {
    public VerticalButton(Action a) {
        super(a);
        setVerticalTextPosition(AbstractButton.BOTTOM);
        setHorizontalTextPosition(AbstractButton.CENTER);
    }
}
