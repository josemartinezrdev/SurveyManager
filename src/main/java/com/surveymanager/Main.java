package com.surveymanager;

import com.surveymanager.principalui.Crud;
import com.surveymanager.principalui.surveyUser.infrastructure.SurveyUserUi;
import com.surveymanager.users.infrastructure.UserUi;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        UserUi userUi = new UserUi();

        String opciones = "1. Sign in \n2. Log in \n3. Exit";
        int op = -1;
        do {

            String input = JOptionPane.showInputDialog(null, opciones);
            if (input == null || input.trim().isEmpty()) {
                return;
            }
            try {
                op = Integer.parseInt(input.trim());
                switch (op) {
                    case 1:
                        userUi.addUser();
                        break;
                    case 2:
                        Boolean isAdmin = userUi.findUserByName();

                        if (isAdmin == null) {
                            JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                        } else if (isAdmin) {
                            JOptionPane.showMessageDialog(null, "Bienvenido Dios Todo Poderoso");
                            Crud crud = new Crud();
                            crud.mainCrud();
                        } else {
                            JOptionPane.showMessageDialog(null, "Simple Mortal");
                            SurveyUserUi surveyUserUi = new SurveyUserUi();
                            surveyUserUi.start();
                        }
                        break;
                    case 3:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error en la opción ingresada", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, ingrese un número válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 3);

    }
}