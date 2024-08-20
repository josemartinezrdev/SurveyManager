package com.surveymanager;

import javax.swing.JOptionPane;

import com.surveymanager.categories_catalog.infrastructure.Categorie_catalogUi;
import com.surveymanager.chapter.infrastructure.ChapterUi;
import com.surveymanager.question.infrastructure.QuestionUi;
import com.surveymanager.roles.infrastructure.RolUi;
import com.surveymanager.survey.infrastructure.SurveyUi;
import com.surveymanager.users.infrastructure.UserUi;
import com.surveymanager.users_roles.infrastructure.User_rolUi;

public class Main {
    public static void main(String[] args) {

        SurveyUi surveyUi = new SurveyUi();
        ChapterUi chapterUi = new ChapterUi();
        QuestionUi questionUi = new QuestionUi();
        UserUi userUi = new UserUi();
        RolUi  rolUi = new RolUi();
        User_rolUi user_rolUi = new User_rolUi();
        Categorie_catalogUi categorie_catalogUi = new Categorie_catalogUi();


        int opt = 0;
        String opts = "1. Surveys\n2. Chapter\n3. Question\n4. User\n5. Role\n6. User Role\n7. Category Catalog\n11. Quit";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    surveyUi.menuSurvey();
                    break;
                case 2:
                    chapterUi.menuChapter();
                    break;
                case 3:
                    questionUi.menuQuestion();
                    break;
                case 4:
                    userUi.mainMenu();
                    break;
                case 5:
                    rolUi.mainMenu();
                    break;
                case 6:
                    user_rolUi.mainMenu();
                    break;
                case 7:
                    categorie_catalogUi.mainMenu();
                    break;
                
                case 11:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada");
                    break;
            }
        } while (opt != 11);

    }
}