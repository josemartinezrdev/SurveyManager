package com.surveymanager.principalui.surveyUser.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.surveymanager.categories_catalog.application.FindAllCategorie_catalogUseCase;
import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.categories_catalog.domain.Categorie_catalogService;
import com.surveymanager.categories_catalog.infrastructure.Categorie_catalogRepository;
import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.principalui.surveyUser.application.FindChapterUseCase;
import com.surveymanager.principalui.surveyUser.application.FindQuestionUseCase;
import com.surveymanager.principalui.surveyUser.application.FindResponseUseCase;
import com.surveymanager.principalui.surveyUser.domain.SurveyUserService;
import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.survey.application.FindAllSurveyUseCase;
import com.surveymanager.survey.domain.entity.Survey;
import com.surveymanager.survey.domain.service.SurveyService;
import com.surveymanager.survey.infrastructure.SurveyRepository;

public class SurveyUserUi {
    private SurveyUserService surveyUserService;
    private SurveyService surveyService;
    private Categorie_catalogService categorie_catalogService;

    private FindChapterUseCase findChapterUseCase;
    private FindAllSurveyUseCase findAllSurveyUseCase;
    private FindAllCategorie_catalogUseCase findAllCategories;
    private FindQuestionUseCase findQuestionUseCase;
    private FindResponseUseCase findResponseUseCase;

    public SurveyUserUi() {
        this.surveyUserService = new SurveyUserRepository();
        this.findChapterUseCase = new FindChapterUseCase(surveyUserService);
        this.surveyService = new SurveyRepository();
        this.findAllSurveyUseCase = new FindAllSurveyUseCase(surveyService);
        this.categorie_catalogService = new Categorie_catalogRepository();
        this.findAllCategories = new FindAllCategorie_catalogUseCase(categorie_catalogService);
        this.findQuestionUseCase = new FindQuestionUseCase(surveyUserService);
        this.findResponseUseCase = new FindResponseUseCase(surveyUserService);
    }

    public void start() {
        int opt = 0;
        String opts = "1. Contestar Encuesta\n2. Volver";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    showSurveys(findAllSurveyUseCase.execute());
                    break;
                case 2:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada");
                    break;
            }
        } while (opt != 2);
    }

    public void showSurveys(List<Survey> surveys) {
        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        surveys.forEach(survey -> {
            String row = survey.getId() + " - " + survey.getName();
            dropDown.addItem(row);
            map.put(row, survey.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la encuesta",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);
            showChapters(findChapterUseCase.execute(id));
        }
    }

    public void showChapters(List<Chapter> chapters) {
        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        chapters.forEach(chapter -> {
            String row = chapter.getId() + " - " + chapter.getChapter_title();
            dropDown.addItem(row);
            map.put(row, chapter.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la capitulos",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int idcha = map.get(text);
            int idcat = showCategories(findAllCategories.execute());
            showQuestions(findQuestionUseCase.execute(idcha, idcat));
        }
    }

    public int showCategories(List<Categorie_catalog> categories) {
        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        categories.forEach(category -> {
            String row = category.getId() + " - " + category.getName();
            dropDown.addItem(row);
            map.put(row, category.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la categoria",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);
            return id;
        }
        return 0;
    }

    public void showQuestions(List<Question> questions) {
        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        questions.forEach(question -> {
            String row = question.getId() + " - " + question.getQuestion_text();
            dropDown.addItem(row);
            map.put(row, question.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la pregunta",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);
            showResponseOpt(findResponseUseCase.execute(id));
        }
    }

    public void showResponseOpt(List<Response> responses) {

        Map<String, Integer> map = new HashMap<>();
        JComboBox<String> dropDown = new JComboBox<>();
        responses.forEach(response -> {
            String row = response.getId() + " - " + response.getOptionText();
            dropDown.addItem(row);
            map.put(row, response.getId());
        });
        int result = JOptionPane.showConfirmDialog(null, dropDown, "Seleccione la respuesta",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String text = (String) dropDown.getSelectedItem();
            int id = map.get(text);
            System.out.println(id);
        }
    }
}
