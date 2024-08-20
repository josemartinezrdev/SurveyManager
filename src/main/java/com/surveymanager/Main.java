package com.surveymanager;

import javax.swing.JOptionPane;

import com.surveymanager.categories_catalog.infrastructure.Categorie_catalogUi;
import com.surveymanager.chapter.infrastructure.ChapterUi;
import com.surveymanager.question.infrastructure.QuestionUi;
import com.surveymanager.response.infrastructure.ResponseUi;
import com.surveymanager.roles.infrastructure.RolUi;
import com.surveymanager.survey.infrastructure.SurveyUi;
import com.surveymanager.users.infrastructure.UserUi;
import com.surveymanager.users_roles.infrastructure.User_rolUi;

public class Main {
    public static void main(String[] args) {

        int opt = 0;
        String opts = "1. Surveys\n2. Chapter\n3. Question\n4. User\n5. Role\n6. User Role\n7. Category Catalog\n8. Response\n11. Quit";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    SurveyUi surveyUi = new SurveyUi();
                    surveyUi.menuSurvey();
                    break;
                case 2:
                    ChapterUi chapterUi = new ChapterUi();
                    chapterUi.menuChapter();
                    break;
                case 3:
                    QuestionUi questionUi = new QuestionUi();
                    questionUi.menuQuestion();
                    break;
                case 4:
                    UserUi userUi = new UserUi();
                    userUi.mainMenu();
                    break;
                case 5:
                    RolUi rolUi = new RolUi();
                    rolUi.mainMenu();
                    break;
                case 6:
                    User_rolUi user_rolUi = new User_rolUi();
                    user_rolUi.mainMenu();
                    break;
                case 7:
                    Categorie_catalogUi categorie_catalogUi = new Categorie_catalogUi();
                    categorie_catalogUi.mainMenu();
                    break;
                case 8:
                    ResponseUi responseUi = new ResponseUi();
                    responseUi.menuResponse();
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