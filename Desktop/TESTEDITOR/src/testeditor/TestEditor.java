package testeditor;

import testeditor.view.MainFrame;

import javax.swing.*;
import java.awt.*;


class TestEditor {
    public static void main(String[] args) throws Exception {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.err.println("Проблема с установкой темы ");
        }
        EventQueue.invokeLater(() -> {
            JFrame testFrame = new MainFrame();

            testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            testFrame.setVisible(true);
        });
    }
}