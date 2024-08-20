package com.surveymanager.question.infrastructure;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.question.application.CreateQuestionUseCase;
import com.surveymanager.question.application.DeleteQuestionUseCase;
import com.surveymanager.question.application.FindAllQuestionUseCase;
import com.surveymanager.question.application.FindByIdQuestionUseCase;
import com.surveymanager.question.application.UpdateQuestionUseCase;
import com.surveymanager.question.domain.entity.Question;
import com.surveymanager.question.domain.service.QuestionService;

public class QuestionUi {
    private QuestionService questionService;

    private CreateQuestionUseCase createQuestionUseCase;
    private UpdateQuestionUseCase updateQuestionUseCase;
    private DeleteQuestionUseCase deleteQuestionUseCase;
    private FindAllQuestionUseCase findAllQuestionUseCase;
    private FindByIdQuestionUseCase findByIdQuestionUseCase;

    public QuestionUi() {
        this.questionService = new QuestionRepository();
        this.createQuestionUseCase = new CreateQuestionUseCase(questionService);
        this.updateQuestionUseCase = new UpdateQuestionUseCase(questionService);
        this.deleteQuestionUseCase = new DeleteQuestionUseCase(questionService);
        this.findAllQuestionUseCase = new FindAllQuestionUseCase(questionService);
        this.findByIdQuestionUseCase = new FindByIdQuestionUseCase(questionService);
    }

    public void menuQuestion() {
        int opt = 0;
        String opts = "1. Create Question\n2. Update Question\n3. Delete Question\n4. Search Questions\n5. Search Question\n6. Quit";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    createQuestion();
                    break;
                case 2:
                    updateQuestion();
                    break;
                case 3:
                    deleteQuestion();
                    break;
                case 4:
                    findAllQuestions();
                    break;
                case 5:
                    findByIdQuestion();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada");
                    break;
            }
        } while (opt != 6);
    }

    public void createQuestion() {
        Question question = new Question();
        question.updateDate();
        question.createdDate();
        question.setQuestion_number(JOptionPane.showInputDialog(null, "Ingrese el numero de la pregunta"));
        question.setResponse_type(JOptionPane.showInputDialog(null, "Ingrese el tipo de respuesta de la pregunta"));
        question.setComment_question(JOptionPane.showInputDialog(null, "Ingrese el comentario de la pregunta"));
        question.setQuestion_text(JOptionPane.showInputDialog(null, "Ingrese el texto de la pregunta"));
        question.setChapter_id(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del capitulo de la pregunta")));

        createQuestionUseCase.execute(question);
    }

    public void showByIdQuestion(Question question) {
        String[] columns = { "ID", "Created", "Updated", "Number", "Type Res", "Comment", "Text", "Chapter" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        Object[] row = {
                question.getId(),
                question.getCreated_at(),
                question.getUpdated_at(),
                question.getQuestion_number(),
                question.getResponse_type(),
                question.getComment_question(),
                question.getQuestion_text(),
                question.getChapter_id()
        };
        model.addRow(row);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Question By Id", JOptionPane.PLAIN_MESSAGE);

    }

    public Question findByIdQuestion() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la pregunta"));
        Question question = findByIdQuestionUseCase.execute(id);
        showByIdQuestion(question);
        return question;
    }

    public void updateQuestion() {
        Question question = findByIdQuestion();
        question.updateDate();
        question.setQuestion_number(JOptionPane.showInputDialog(null, "Ingrese el numero de la pregunta"));
        question.setResponse_type(JOptionPane.showInputDialog(null, "Ingrese el tipo de respuesta de la pregunta"));
        question.setComment_question(JOptionPane.showInputDialog(null, "Ingrese el comentario de la pregunta"));
        question.setQuestion_text(JOptionPane.showInputDialog(null, "Ingrese el texto de la pregunta"));
        question.setChapter_id(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del capitulo de la pregunta")));
        updateQuestionUseCase.execute(question);
    }

    public void deleteQuestion() {
        Question question = findByIdQuestion();
        deleteQuestionUseCase.execute(question.getId());
    }

    public void showAllQuestions(List<Question> questions) {
        String[] columns = { "ID", "Created", "Updated", "Number", "Type Res", "Comment", "Text", "Chapter" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        questions.forEach(question -> {
            Object[] row = {
                    question.getId(),
                    question.getCreated_at(),
                    question.getUpdated_at(),
                    question.getQuestion_number(),
                    question.getResponse_type(),
                    question.getComment_question(),
                    question.getQuestion_text(),
                    question.getChapter_id()
            };
            model.addRow(row);
        });
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        JOptionPane.showMessageDialog(null, panel, "Questions List", JOptionPane.PLAIN_MESSAGE);
    }

    public void findAllQuestions() {
        List<Question> questions = findAllQuestionUseCase.execute();
        showAllQuestions(questions);
    }
}
