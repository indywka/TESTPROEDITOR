package testeditor;

import testeditor.gui.MainFrame;
import testeditor.question.*;
import testeditor.saver.Saver;
import testeditor.saver.XmlSaver;

import javax.swing.*;
import java.awt.*;


public class TestEditor {
    public static void main(String[] args) throws Exception {

        try {
            if (isWindows()) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.err.println("Проблема с установкой темы ");
        }
        EventQueue.invokeLater(() -> {
            JFrame testFrame = new MainFrame();

            testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            testFrame.setVisible(true);
        });
    }

    private static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));
    }

}