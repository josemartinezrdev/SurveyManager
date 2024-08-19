package com.surveymanager.survey.infrastructure;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.survey.application.CreateSurveyUseCase;
import com.surveymanager.survey.application.DeleteSurveyUseCase;
import com.surveymanager.survey.application.FindAllSurveyUseCase;
import com.surveymanager.survey.application.FindByIdSurveyUseCase;
import com.surveymanager.survey.application.UpdateSurveyUseCase;
import com.surveymanager.survey.domain.entity.Survey;
import com.surveymanager.survey.domain.service.SurveyService;

public class SurveyUi {

    private SurveyService surveyService;

    private CreateSurveyUseCase createSurveyUseCase;
    private UpdateSurveyUseCase updateSurveyUseCase;
    private DeleteSurveyUseCase deleteSurveyUseCase;
    private FindAllSurveyUseCase findAllSurveyUseCase;
    private FindByIdSurveyUseCase findByIdSurveyUseCase;

    public SurveyUi() {
        this.surveyService = new SurveyRepository();
        this.createSurveyUseCase = new CreateSurveyUseCase(surveyService);
        this.updateSurveyUseCase = new UpdateSurveyUseCase(surveyService);
        this.deleteSurveyUseCase = new DeleteSurveyUseCase(surveyService);
        this.findAllSurveyUseCase = new FindAllSurveyUseCase(surveyService);
        this.findByIdSurveyUseCase = new FindByIdSurveyUseCase(surveyService);
    }

    public void menuSurvey() {
        int opt = 0;
        String opts = "1. Create Survey\n2. Update Survey\n3. Delete Survey\n4. Search Surveys\n5. Search Survey\n6. Quit";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    createSurvey();
                    break;
                case 2:
                    updateSurvey();
                    break;
                case 3:
                    deleteSurvey();
                    break;
                case 4:
                    findAllSurveys();
                    break;
                case 5:
                    findByIdSurvey();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada");
                    break;
            }
        } while (opt != 6);
    }

    public void createSurvey() {
        Survey survey = new Survey();
        survey.updateDate();
        survey.setDescription(JOptionPane.showInputDialog(null, "Ingrese la descripcion de la encuesta"));
        survey.setName(JOptionPane.showInputDialog(null, "Ingrese el nombre de la encuesta"));
        createSurveyUseCase.execute(survey);
    }

    public void showByIdSurvey(Survey survey) {
        String[] columns = { "ID", "Created", "Updated", "Desc", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        Object[] row = {
                survey.getId(),
                survey.getCreated_at(),
                survey.getUpdated_at(),
                survey.getDescription(),
                survey.getName()
        };
        model.addRow(row);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Survey By Id", JOptionPane.PLAIN_MESSAGE);

    }

    public Survey findByIdSurvey() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la encuesta"));
        Survey survey = findByIdSurveyUseCase.execute(id);
        showByIdSurvey(survey);
        return survey;
    }

    public void updateSurvey() {
        Survey survey = findByIdSurvey();
        survey.updateDate();
        survey.setDescription(JOptionPane.showInputDialog(null, "Ingrese la descripcion de la encuesta"));
        survey.setName(JOptionPane.showInputDialog(null, "Ingrese el nombre de la encuesta"));
        updateSurveyUseCase.execute(survey);
    }

    public void deleteSurvey() {
        Survey survey = findByIdSurvey();
        deleteSurveyUseCase.execute(survey.getId());
    }

    public void showAllSurveys(List<Survey> surveys) {
        String[] columns = { "ID", "Created", "Updated", "Desc", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        surveys.forEach(survey -> {
            Object[] row = {
                    survey.getId(),
                    survey.getCreated_at(),
                    survey.getUpdated_at(),
                    survey.getDescription(),
                    survey.getName()
            };
            model.addRow(row);
        });
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        JOptionPane.showMessageDialog(null, panel, "Surveys List", JOptionPane.PLAIN_MESSAGE);
    }

    public void findAllSurveys() {
        List<Survey> surveys = findAllSurveyUseCase.execute();
        showAllSurveys(surveys);
    }

}
