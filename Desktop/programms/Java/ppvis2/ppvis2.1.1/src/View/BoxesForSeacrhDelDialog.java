package View;

import Controller.*;
import Model.DataBase;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;


class BoxesForSeacrhDelDialog {

    Box studentFioBox(String key, DataBase dataBase, TablePanel tablePanel) {

        JTextField studentSurName = new JTextField(10);
        studentSurName.setBorder(new TitledBorder("Фамилия:"));
        JTextField studentFirstName = new JTextField(10);
        studentFirstName.setBorder(new TitledBorder("Имя:"));
        JTextField studentSecondName = new JTextField(10);
        studentSecondName.setBorder(new TitledBorder("Отчество:"));


        Box unityBox = Box.createVerticalBox();
        Box studentFioBox = Box.createVerticalBox();
        Box txtLine1 = Box.createHorizontalBox();
        txtLine1.add(studentSurName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(studentFirstName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(studentSecondName);
        studentFioBox.add(txtLine1);
        studentFioBox.setBorder(new TitledBorder("ФИО студента:"));


        JPanel BtnPanel = new JPanel(new GridLayout(1, 1, 2, 2));
        JButton okBtn = new JButton("Выполнить");
        okBtn.setMnemonic(KeyEvent.VK_ENTER);
        okBtn.setBackground(new Color(255, 255, 255));
        okBtn.setPreferredSize(new Dimension(220, 25));
        okBtn.setBorder(BorderFactory.createBevelBorder(0, new Color(90, 255, 229), new Color(214, 47, 255)));
        if (key.equals("delete")) {
            okBtn.addActionListener(e -> {
                DeleteStudentFIO temp = new DeleteStudentFIO(dataBase,
                        studentSurName.getText(), studentFirstName.getText(), studentSecondName.getText());
            });
        }
        if (key.equals("search")) {
            okBtn.addActionListener(e -> {
                SearchStudentFIO temp = new SearchStudentFIO(dataBase,
                        studentSurName.getText(), studentFirstName.getText(), studentSecondName.getText());
                tablePanel.setStudents(temp.getSearchStudList(), temp.getSearchDadList(), temp.getSearchMumList());
                tablePanel.updateTable();
            });
        }
        unityBox.add(studentFioBox);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(BtnPanel);
        BtnPanel.add(okBtn);
        unityBox.add(flow);


        return unityBox;
    }


    Box parentFioBox(String key, DataBase dataBase, TablePanel tablePanel) {
        JTextField dadSurName = new JTextField(10);
        dadSurName.setBorder(new TitledBorder("Фамилия:"));
        JTextField dadFirstName = new JTextField(10);
        dadFirstName.setBorder(new TitledBorder("Имя:"));
        JTextField dadSecondName = new JTextField(10);
        dadSecondName.setBorder(new TitledBorder("Отчество:"));

        JTextField mumSurName = new JTextField(10);
        mumSurName.setBorder(new TitledBorder("Фамилия:"));
        JTextField mumFirstName = new JTextField(10);
        mumFirstName.setBorder(new TitledBorder("Имя:"));
        JTextField mumSecondName = new JTextField(10);
        mumSecondName.setBorder(new TitledBorder("Отчество:"));


        Box parentFioBox = Box.createVerticalBox();
        Box txtLine1 = Box.createHorizontalBox();
        txtLine1.add(dadSurName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(dadFirstName);
        txtLine1.add((Box.createHorizontalStrut(10)));
        txtLine1.add(dadSecondName);
        txtLine1.setBorder(new TitledBorder("ФИО отца:"));

        Box txtLine2 = Box.createHorizontalBox();
        txtLine2.add(mumSurName);
        txtLine2.add((Box.createHorizontalStrut(10)));
        txtLine2.add(mumFirstName);
        txtLine2.add((Box.createHorizontalStrut(10)));
        txtLine2.add(mumSecondName);
        txtLine2.setBorder(new TitledBorder("ФИО матери:"));

        JPanel BtnPanel = new JPanel(new GridLayout(1, 1, 2, 2));
        JButton okBtn = new JButton("Выполнить");
        okBtn.setMnemonic(KeyEvent.VK_ENTER);
        okBtn.setBackground(new Color(255, 255, 255));
        okBtn.setPreferredSize(new Dimension(220, 25));
        okBtn.setBorder(BorderFactory.createBevelBorder(0, new Color(90, 255, 229), new Color(214, 47, 255)));
        if (key.equals("delete")) {
            okBtn.addActionListener(e -> {
                DeleteParentFIO temp = new DeleteParentFIO(dataBase,
                        dadSurName.getText(), dadFirstName.getText(), dadSecondName.getText(),
                        mumSurName.getText(), mumFirstName.getText(), mumSecondName.getText());
            });
        }
        if (key.equals("search")) {
            okBtn.addActionListener(e -> {
                SearchParentFIO temp = new SearchParentFIO(dataBase,
                        dadSurName.getText(), dadFirstName.getText(), dadSecondName.getText(),
                        mumSurName.getText(), mumFirstName.getText(), mumSecondName.getText());
                tablePanel.setStudents(temp.getSearchStudList(), temp.getSearchDadList(), temp.getSearchMumList());
                tablePanel.updateTable();
            });
        }

        parentFioBox.add(txtLine1);
        parentFioBox.add(txtLine2);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(BtnPanel);
        BtnPanel.add(okBtn);
        parentFioBox.add(flow);

        return parentFioBox;
    }


    Box brotherSisterBox(String key, DataBase dataBase, TablePanel tablePanel) {
        Box brothNum = Box.createHorizontalBox();
        JTextField numOfBrothersMin = new JTextField(5);
        JTextField numOfBrothersMax = new JTextField(5);
        brothNum.add(new JLabel("От: "));
        brothNum.add(numOfBrothersMin);
        brothNum.add(Box.createHorizontalStrut(8));
        brothNum.add(new JLabel("до: "));
        brothNum.add(numOfBrothersMax);
        brothNum.setBorder(new TitledBorder("Число братьев:"));

        Box numberOfSisters = Box.createHorizontalBox();
        JTextField numOfSistersMin = new JTextField(5);
        JTextField numOfSistersMax = new JTextField(5);
        numberOfSisters.add(new JLabel("От: "));
        numberOfSisters.add(numOfSistersMin);
        numberOfSisters.add(Box.createHorizontalStrut(8));
        numberOfSisters.add(new JLabel("до: "));
        numberOfSisters.add(numOfSistersMax);
        numberOfSisters.setBorder(new TitledBorder("Кол-во сестер:"));

        Box unityBox = Box.createVerticalBox();
        Box brotherSisterBox = Box.createHorizontalBox();
        brotherSisterBox.add(Box.createHorizontalStrut(50));
        brotherSisterBox.add(brothNum);
        brotherSisterBox.add(Box.createHorizontalStrut(30));
        brotherSisterBox.add(numberOfSisters);
        brotherSisterBox.add(Box.createHorizontalStrut(50));

        JPanel BtnPanel = new JPanel(new GridLayout(1, 1, 2, 2));
        JButton okBtn = new JButton("Выполнить");
        okBtn.setMnemonic(KeyEvent.VK_ENTER);
        okBtn.setBackground(new Color(255, 255, 255));
        okBtn.setPreferredSize(new Dimension(220, 25));
        okBtn.setBorder(BorderFactory.createBevelBorder(0, new Color(90, 255, 229), new Color(214, 47, 255)));

        if (key.equals("search")) {
            okBtn.addActionListener(e -> {
                SearchBrotherSister temp = new SearchBrotherSister(dataBase,
                        numOfBrothersMin.getText(), numOfBrothersMax.getText(),
                        numOfSistersMin.getText(), numOfSistersMax.getText());
                tablePanel.setStudents(temp.getSearchStudList(), temp.getSearchDadList(), temp.getSearchMumList());
                tablePanel.updateTable();
            });
        }
        if (key.equals("delete")) {
            okBtn.addActionListener(e -> {
                DeleteBrotherSister temp = new DeleteBrotherSister(dataBase,
                        numOfBrothersMin.getText(), numOfBrothersMax.getText(),
                        numOfSistersMin.getText(), numOfSistersMax.getText());
            });
        }

        unityBox.add(brotherSisterBox);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(BtnPanel);
        BtnPanel.add(okBtn);
        unityBox.add(flow);

        return unityBox;
    }


    Box parentSalaryBox(String key, DataBase dataBase, TablePanel tablePanel) {
        Box dadSalary = Box.createHorizontalBox();
        JTextField dadSalaryMin = new JTextField(8);
        JTextField dadSalaryMax = new JTextField(8);
        dadSalary.add(new JLabel("От: "));
        dadSalary.add(dadSalaryMin);
        dadSalary.add(Box.createHorizontalStrut(8));
        dadSalary.add(new JLabel("до: "));
        dadSalary.add(dadSalaryMax);
        dadSalary.setBorder(new TitledBorder("Заработок отца:"));

        Box mumSalary = Box.createHorizontalBox();
        JTextField mumSalaryMin = new JTextField(8);
        JTextField mumSalaryMax = new JTextField(8);
        mumSalary.add(new JLabel("От: "));
        mumSalary.add(mumSalaryMin);
        mumSalary.add(Box.createHorizontalStrut(8));
        mumSalary.add(new JLabel("до: "));
        mumSalary.add(mumSalaryMax);
        mumSalary.setBorder(new TitledBorder("Заработок матери:"));


        Box unityBox = Box.createVerticalBox();
        Box parentSalaryBox = Box.createHorizontalBox();
        parentSalaryBox.add(Box.createHorizontalStrut(50));
        parentSalaryBox.add(dadSalary);
        parentSalaryBox.add(Box.createHorizontalStrut(30));
        parentSalaryBox.add(mumSalary);
        parentSalaryBox.add(Box.createHorizontalStrut(50));

        JPanel BtnPanel = new JPanel(new GridLayout(1, 1, 2, 2));
        JButton okBtn = new JButton("Выполнить");
        okBtn.setMnemonic(KeyEvent.VK_ENTER);
        okBtn.setBackground(new Color(255, 255, 255));
        okBtn.setPreferredSize(new Dimension(220, 25));
        okBtn.setBorder(BorderFactory.createBevelBorder(0, new Color(90, 255, 229), new Color(214, 47, 255)));

        if (key.equals("search")) {
            okBtn.addActionListener(e -> {
                SearchParentSalary temp = new SearchParentSalary(dataBase,
                        dadSalaryMin.getText(), dadSalaryMax.getText(),
                        mumSalaryMin.getText(), mumSalaryMax.getText());
                tablePanel.setStudents(temp.getSearchStudList(), temp.getSearchDadList(), temp.getSearchMumList());
                tablePanel.updateTable();
            });
        }
        if (key.equals("delete")) {
            okBtn.addActionListener(e -> {
                DeleteParentSalary temp = new DeleteParentSalary(dataBase,
                        dadSalaryMin.getText(), dadSalaryMax.getText(),
                        mumSalaryMin.getText(), mumSalaryMax.getText());
            });
        }

        unityBox.add(parentSalaryBox);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(BtnPanel);
        BtnPanel.add(okBtn);
        unityBox.add(flow);

        return unityBox;
    }


}
