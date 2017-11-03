package Controller;


import javax.swing.*;


public class ChooseCondition {


    public Box addCondition(Box mainBox, JComboBox condition,
                           Box studentFIOBox, Box parentFioBox, Box brotherSisterBox, Box parentSalaryBox) {
        String choice = (String) condition.getSelectedItem();
        switch (choice) {
            case "": {
                mainBox.remove(2);
                mainBox.revalidate();
                break;
            }
            case "ФИО студента": {
                mainBox.remove(2);
                mainBox.add(studentFIOBox, 2);
                mainBox.revalidate();
                break;
            }
            case "ФИО родителя": {
                mainBox.remove(2);
                mainBox.add(parentFioBox, 2);
                mainBox.revalidate();
                break;
            }
            case "число братьев или сестёр": {
                mainBox.remove(2);
                mainBox.add(brotherSisterBox, 2);
                mainBox.revalidate();
                break;
            }
            case "заработок одного из родителей": {
                mainBox.remove(2);
                mainBox.add(parentSalaryBox, 2);
                mainBox.revalidate();
                break;
            }
        }

        return mainBox;
    }
}
