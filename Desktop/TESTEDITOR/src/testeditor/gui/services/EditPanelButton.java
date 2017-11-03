package testeditor.gui.services;

import javax.swing.*;
import java.awt.*;


public class EditPanelButton extends JButton {  //размер кнопок в панели  кнопок для редактирования вопроса

    public EditPanelButton(Action a) {
        super(a);
        setHorizontalAlignment(SwingConstants.LEFT);
        setMargin(new Insets(0, 10, 5, 10));
    }
}
