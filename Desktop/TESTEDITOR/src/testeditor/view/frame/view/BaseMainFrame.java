package testeditor.view.frame.view;


import java.awt.*;

abstract public class BaseMainFrame extends ParentFrame {

    protected final int HEIGHT_MIN = 300;

    protected BaseMainFrame() {

        int width = (int) (SCREEN_WIDTH / 1.5);
        int height = (int) (SCREEN_HEIGHT / 1.5);
        int INITIAL_WIDTH_MIN = 900;
        int initialWidth = width < INITIAL_WIDTH_MIN ? INITIAL_WIDTH_MIN : width;
        int INITIAL_HEIGHT_MIN = 500;
        int initialHeight = height < INITIAL_HEIGHT_MIN ? INITIAL_HEIGHT_MIN : height;

        int WIDTH_MIN = 500;
        setMinimumSize(new Dimension(WIDTH_MIN, HEIGHT_MIN));

        //------- Устанавливаем расположение и размер окна -------//
        setSize(initialWidth, initialHeight);
    }
}
