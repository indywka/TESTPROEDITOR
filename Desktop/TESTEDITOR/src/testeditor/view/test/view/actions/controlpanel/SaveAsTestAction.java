package testeditor.view.test.view.actions.controlpanel;

import testeditor.view.frame.view.MainFrame;
import testeditor.contoller.XMLFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;


public class SaveAsTestAction extends AbstractAction {

    public SaveAsTestAction() {

        this.putValue(Action.NAME, "Сохранить как...");
        this.putValue(Action.SHORT_DESCRIPTION, "Сохранить тест как...");
        URL imageURL = getClass().getResource("/testeditor/view/icons/save_as.png");
        this.putValue(Action.SMALL_ICON, new ImageIcon(imageURL));
    }

    public void actionPerformed(ActionEvent event) {
        JFileChooser saveAsDialog = new JFileChooser(); // объект диалогового окна

        //------- Настраиваем диалоговое окно -------//
        saveAsDialog.setCurrentDirectory(new File(".")); //корневая дирректория по умолчанию
        saveAsDialog.setAcceptAllFileFilterUsed(false); //убираем в фильтрах "All files"
        saveAsDialog.addChoosableFileFilter(new FileNameExtensionFilter("Все поддерживаемые форматы (*.xml)", "xml"));
        saveAsDialog.addChoosableFileFilter(new FileNameExtensionFilter("XML Moodle test (*.xml)", "xml"));
        saveAsDialog.setDialogTitle("Сохранить как...");
        saveAsDialog.setApproveButtonToolTipText("Сохранить тест");

        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Отмена");
        SwingUtilities.updateComponentTreeUI(saveAsDialog);

        MainFrame parentFrame = (MainFrame) SwingUtilities.getRoot((Component) event.getSource());

        //------- Обрабатываем файл теста -------//
        int result = saveAsDialog.showDialog(parentFrame, "Сохранить");
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                XMLFile s = new XMLFile(saveAsDialog.getSelectedFile().getPath());
                s.save();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            parentFrame.setTitle(saveAsDialog.getSelectedFile().getName());
        }
    }
}
