package testeditor.gui;

import javax.swing.*;
import java.awt.*;

public class ParentFrame extends JFrame {

    protected final int SCREEN_HEIGHT;
    final int SCREEN_WIDTH;
    protected int initialHeight;

    ParentFrame() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        SCREEN_WIDTH = screenSize.width;
        SCREEN_HEIGHT = screenSize.height;

        setIconImage(new ImageIcon("src/testeditor/gui/img/main.png").getImage());
        setTitle("Редактор тестов");

        int DEFAULT_HEIGHT = 600;
        int DEFAULT_WIDTH = 600;
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    //------- Устанавливаем расположение и размер окна -------//

    public void setSize(int initialWidth, int initialHeight) {
        this.initialHeight = initialHeight;
        super.setSize(initialWidth, initialHeight);
        setLocation((SCREEN_WIDTH - initialWidth) / 2,
                (SCREEN_HEIGHT - initialHeight) / 2);
    }
}
