package testeditor.gui.services;

import javax.swing.*;


public class HintLabel extends JLabel {

    public void error(String text) {
        setText("<html><p><font color='red'><b>" + text + "</font></b></p></html>");
    }

    public void info(String text) {
        setText("<html><p>" + text + "</p></html>");
    }
}