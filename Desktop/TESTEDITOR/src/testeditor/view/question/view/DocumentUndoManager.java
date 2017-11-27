package testeditor.view.question.view;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

public class DocumentUndoManager {

    private static DocumentUndoManager instanse;
    private UndoActionListener listener;
    private HashMap<JTextComponent, UndoManager> items = new HashMap<>();
    private EditPopup popup;

    private DocumentUndoManager() {

        listener = new UndoActionListener();
        popup = new EditPopup();
    }

    static DocumentUndoManager getInstance() {
        if (instanse == null) {
            instanse = new DocumentUndoManager();
        }
        return instanse;
    }

    void registerDocumentHolder(JTextComponent documentHolder) {
        //создаем новый менеджер изменений документа
        UndoManager undo = new UndoManager();
        //запоминаем его для данного текстового поля
        items.put(documentHolder, undo);
        //получаем модель документа такстового поля
        Document doc = documentHolder.getDocument();
        //добавляем слушатель изменений документа
        doc.addUndoableEditListener(undo);
        //добавляем слушатель нажатий клавиш (он будет обрабатывать ctrl+z)
        documentHolder.addKeyListener(listener);
        //добавляем слушатель нажатий кнопок мыши (он обработает нажатие правой кнопки)
        documentHolder.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //если нажата правая кнопка мыши
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //устанавливаем текстовый компонент для меню
                    popup.setDocumentHolder((JTextComponent) e.getSource());
                    //отображаем всплывающее меню
                    popup.show((JTextComponent) e.getSource(), e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * очищает историю изменений для текстовых компонент
     *
     * @param documentHolders список компонент (из JTextComponent)
     */
    public void clearChangeHistory(List<JTextComponent> documentHolders) {
        for (JTextComponent textComponent : documentHolders) {
            UndoManager manager = items.get(textComponent);
            manager.discardAllEdits();
        }
    }

    class EditPopup extends JPopupMenu {

        private JMenuItem copy;
        private JMenuItem cut;
        private JMenuItem paste;
        private JMenuItem undo;
        private JTextComponent documentHolder = null;

        EditPopup() {

            copy = new JMenuItem("Копировать");
            copy.addActionListener(e -> {
                if (documentHolder != null) {
                    documentHolder.copy();
                }
            });

            cut = new JMenuItem("Вырезать");
            cut.addActionListener(e -> {
                if (documentHolder != null) {
                    documentHolder.cut();
                }
            });

            paste = new JMenuItem("Вставить");
            paste.addActionListener(e -> {
                if (documentHolder != null) {
                    documentHolder.paste();
                }
            });

            undo = new JMenuItem("Отмена");
            undo.addActionListener(e -> {
                if (documentHolder != null) {
                    UndoManager manager = items.get(documentHolder);
                    if (manager.canUndo()) {
                        manager.undo();
                    }
                }
            });

            add(copy);
            add(cut);
            add(paste);
            addSeparator();
            add(undo);
        }

        void setDocumentHolder(JTextComponent documentHolder) {
            this.documentHolder = documentHolder;
            //устанавливаем доступность пунктов "копировать" и "вырезать"
            if (documentHolder.getSelectedText() != null && documentHolder.getSelectedText() != null) {
                copy.setEnabled(true);
                cut.setEnabled(true);
            } else {
                copy.setEnabled(false);
                cut.setEnabled(false);
            }

            //устанавливаем доступность пункта "вставить"
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(this);
            DataFlavor flavor = DataFlavor.stringFlavor;
            if (contents != null && contents.isDataFlavorSupported(flavor)) {
                paste.setEnabled(true);
            } else {
                paste.setEnabled(false);
            }

            //устанавливаем доступность пункта "отмена"
            UndoManager manager = items.get(documentHolder);
            if (manager.canUndo()) {
                undo.setEnabled(true);
            } else {
                undo.setEnabled(false);
            }

        }

    }

    class UndoActionListener extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown()) {
                UndoManager manager;
                manager = items.get(e.getSource());
                if (manager.canUndo()) {
                    manager.undo();
                }
            }

        }
    }
}