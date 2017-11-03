package Controller;

import Model.DataBase;
import Model.Parent;
import Model.Student;

import javax.swing.*;

public class OkBtnInAdding {


    public void addNewStudentInfo(String surName, String firstName, String secName,
                                  String dadSurName, String dadFirstName, String dadSecName, String dadSalary,
                                  String mumSurName, String mumFirstName, String mumSecName, String mumSalary,
                                  String numberOfBrothers, String numberOfSisters,
                                  DataBase dataBase) {
        if (surName.equals("") || firstName.equals("") || secName.equals("")) {
            JOptionPane.showMessageDialog(null, "Fill student's name.");
            return;
        }
        if (dadSurName.equals("") || dadFirstName.equals("") || dadSecName.equals("") || dadSalary.equals("")) {
            JOptionPane.showMessageDialog(null, "Fill info about father.");
            return;
        }
        if (mumSurName.equals("") || mumFirstName.equals("") || mumSecName.equals("") || mumSalary.equals("")) {
            JOptionPane.showMessageDialog(null, "Fill info about mother.");
            return;
        }
        if (numberOfBrothers.equals("") || numberOfBrothers.equals("")) {
            JOptionPane.showMessageDialog(null, "Fill info about brothers & sisters.");
            return;
        }

        int intBrothers;
        int intSisters;
        int intDadSalary;
        int intMumSalary;

        try {
            intBrothers = Integer.parseInt(numberOfBrothers);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a NUMBER of brothers.");
            return;
        }
        try {
            intSisters = Integer.parseInt(numberOfSisters);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a NUMBER of sisters.");
            return;
        }
        try {
            intDadSalary = Integer.parseInt(dadSalary);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a NUMBER in dad salary.");
            return;
        }
        try {
            intMumSalary = Integer.parseInt(mumSalary);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a NUMBER in mum salary.");
            return;
        }


        Student stud = new Student(surName, firstName, secName, intBrothers, intSisters);
        Parent dad = new Parent(dadSurName, dadFirstName, dadSecName, intDadSalary);
        Parent mum = new Parent(mumSurName, mumFirstName, mumSecName, intMumSalary);

        dataBase.addStud(stud);
        dataBase.addDad(dad);
        dataBase.addMum(mum);
    }
}
