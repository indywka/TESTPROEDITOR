package testeditor.view.test.view;

import testeditor.Test;
import testeditor.contoller.Question;
import testeditor.model.QListModel;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

/**
 * Панель, отображающая общий вид теста и кнопки управления его содержимым
 */

public class TestView extends JPanel {

    private final QListModel listModel;  // Модель для компонета JList со списком вопросов
    private JList<Question> questionList; //Список вопросов
    private ControlPanel controlPanel;  // управление файлом теста
    private EditPanel editPanel;  // управление элементами теста

    public TestView() {

        listModel = new QListModel();

        listModel.addListDataListener(new ListDataListener() {   //чтобы узнать, когда изменится содержмиое модели,
            // связываем ListDataListener c моделью списка
            @Override
            public void intervalAdded(ListDataEvent listDataEvent) {

                Test.getTest().update(Collections.list(listModel.elements()));

                if (!controlPanel.getSaveAsButton().isEnabled()) {

                    controlPanel.getSaveAsButton().setEnabled(true);
                    editPanel.getButtons().forEach(b -> b.setEnabled(true));

                }
            }

            @Override
            public void intervalRemoved(ListDataEvent listDataEvent) {
                Test.getTest().update(Collections.list(listModel.elements()));

                if (listModel.isEmpty()) {
                    controlPanel.getSaveAsButton().setEnabled(false);
                    editPanel.getButtons().forEach(b -> b.setEnabled(false));
                    editPanel.getCreateButton().setEnabled(true);
                }
            }

            @Override
            public void contentsChanged(ListDataEvent listDataEvent) {
                Test.getTest().update(Collections.list(listModel.elements()));
            }
        });

        //------- Создаем и настраиваем компоненты графического интерфейса -------//

        setLayout(new BorderLayout());

        questionList = new JList<>(listModel);
        questionList.setBackground(Color.GRAY);
        questionList.setCellRenderer(new ListRenderer());//покраска ячеек списка как панелей


        questionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) editPanel.getEditButton().doClick();

            }
        });


        questionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent a) {
                super.mouseClicked(a);
                if (a.getButton() == MouseEvent.BUTTON3 && a.getClickCount() == 2)
                    editPanel.getDeleteButton().doClick();

            }
        });


        questionList.addListSelectionListener(e ->
                questionList.ensureIndexIsVisible(questionList.getSelectedIndex()));


        questionList.addListSelectionListener(a ->
                questionList.ensureIndexIsVisible(questionList.getSelectedIndex()));


        questionList.addListSelectionListener(y ->
                questionList.ensureIndexIsVisible(questionList.getSelectedIndex()));


        JScrollPane scrollPane = new JScrollPane(questionList); // полоса прокрутки для списка
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        controlPanel = new ControlPanel(questionList);
        editPanel = new EditPanel(questionList);
        editPanel.setVisible(false);

        add(new JPanel(), BorderLayout.SOUTH);
        add(scrollPane);
        add(controlPanel, BorderLayout.NORTH);
        add(editPanel, BorderLayout.WEST);
    }

    public EditPanel getEditPanel() {
        return editPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public class ListRenderer extends JPanel implements ListCellRenderer<Question> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Question> list, Question value, int index, boolean isSelected, boolean cellHasFocus) {


            setLayout(new GridBagLayout());

            setBackground(isSelected ? new Color(219, 249, 252) : new Color(230, 230, 230));

            removeAll();

        /*номер вопроса*/

            JLabel labelNumber = new JLabel("Вопрос №" + Integer.toString(index + 1));

            labelNumber.setFont(new Font("Sans-Serif", Font.BOLD, 12));
            add(labelNumber, new GBC(0, 0, 1, 1, 0, 0, 0, 0).setFill(GBC.HORIZONTAL)
                    .setInsets(10, 10, 10, 10));

            add(new JSeparator(JSeparator.VERTICAL), new GBC(1, 0, 1, 1, 0, 0, 0, 0).setFill(GBC.VERTICAL));

        /*название и текст вопроса*/

            JLabel labelQuestion = new JLabel("<html><p>" +
                    "<b>" + value.getQName() + "</b>" +
                    "<br>" + value.getQText() +
                    "</p><br></html>");

            labelQuestion.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
            add(labelQuestion, new GBC(2, 0, 1, 1, 0, 0, 100, 0).setFill(GBC.BOTH)
                    .setInsets(10, 5, 10, 10));


            JSeparator lineSeparator = new JSeparator();
            lineSeparator.setBorder(BorderFactory.createEmptyBorder());
            add(lineSeparator, new GBC(0, 1, 5, 1, 0, 0, 100, 0).setFill(GBC.HORIZONTAL));

            return this;

        }

    }

    //Возвращаем компонент, который был настроен для отображения указанного значения.


}