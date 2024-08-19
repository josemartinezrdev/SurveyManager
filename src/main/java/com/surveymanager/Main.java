package com.surveymanager;

import javax.swing.JOptionPane;

import com.surveymanager.chapter.infrastructure.ChapterUi;
import com.surveymanager.question.infrastructure.QuestionUi;
import com.surveymanager.survey.infrastructure.SurveyUi;

public class Main {
    public static void main(String[] args) {

        SurveyUi surveyUi = new SurveyUi();
        ChapterUi chapterUi = new ChapterUi();
        QuestionUi questionUi = new QuestionUi();

        int opt = 0;
        String opts = "1. Surveys\n2. Chapter\n3. Question\n11. Quit";
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