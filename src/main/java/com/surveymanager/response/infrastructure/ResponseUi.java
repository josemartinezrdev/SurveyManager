package com.surveymanager.response.infrastructure;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.response.application.CreateResponseUseCase;
import com.surveymanager.response.application.DeleteResponseUseCase;
import com.surveymanager.response.application.FindAllResponseUseCase;
import com.surveymanager.response.application.FindByIdResponseUseCase;
import com.surveymanager.response.application.UpdateResponseUseCase;
import com.surveymanager.response.domain.entity.Response;
import com.surveymanager.response.domain.service.ResponseService;

public class ResponseUi {
    private ResponseService responseService;

    private CreateResponseUseCase createResponseUseCase;
    private UpdateResponseUseCase updateResponseUseCase;
    private DeleteResponseUseCase deleteResponseUseCase;
    private FindAllResponseUseCase findAllResponseUseCase;
    private FindByIdResponseUseCase findByIdResponseUseCase;

    public ResponseUi() {
        this.responseService = new ResponseRepository();
        this.createResponseUseCase = new CreateResponseUseCase(responseService);
        this.updateResponseUseCase = new UpdateResponseUseCase(responseService);
        this.deleteResponseUseCase = new DeleteResponseUseCase(responseService);
        this.findAllResponseUseCase = new FindAllResponseUseCase(responseService);
        this.findByIdResponseUseCase = new FindByIdResponseUseCase(responseService);
    }

    public void menuResponse() {
        int opt = 0;
        String opts = "1. Create Response\n2. Update Response\n3. Delete Response\n4. Search Responses\n5. Search Response\n6. Quit";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    createResponse();
                    break;
                case 2:
                    updateResponse();
                    break;
                case 3:
                    deleteResponse();
                    break;
                case 4:
                    findAllResponses();
                    break;
                case 5:
                    findByIdResponse();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada");
                    break;
            }
        } while (opt != 6);
    }

    public void createResponse() {
        Response response = new Response();
        response.updateDate();
        response.createdDate();
        response.setOptionValue(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el value option")));
        response.setTypeComponentHtml(JOptionPane.showInputDialog(null, "Ingrese el typecomponent"));
        response.setCommentResponse(JOptionPane.showInputDialog(null, "Ingrese el comment response"));
        response.setOptionText(JOptionPane.showInputDialog(null, "Ingrese el option text"));
        response.setCategoryCatalogId(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la categoria")));
        response.setQuestionId(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la pregunta")));
        response.setParentResponseId(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del parent reponse")));

        createResponseUseCase.execute(response);
    }

    public void showByIdResponse(Response response) {
        String[] columns = { "ID", "Created", "Updated", "Opt Value", "Type Html", "Comment Res", "Opt Text",
                "Cat Catalog", "Question", "Parent" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        Object[] row = {
                response.getId(),
                response.getCreated_at(),
                response.getUpdated_at(),
                response.getOptionValue(),
                response.getTypeComponentHtml(),
                response.getCommentResponse(),
                response.getOptionText(),
                response.getCategoryCatalogId(),
                response.getQuestionId(),
                response.getParentResponseId()
        };
        model.addRow(row);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Response By Id", JOptionPane.PLAIN_MESSAGE);

    }

    public Response findByIdResponse() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la respuesta"));
        Response response = findByIdResponseUseCase.execute(id);
        showByIdResponse(response);
        return response;
    }

    public void updateResponse() {
        Response response = findByIdResponse();
        response.updateDate();
        response.setOptionValue(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el value option")));
        response.setTypeComponentHtml(JOptionPane.showInputDialog(null, "Ingrese el typecomponent"));
        response.setCommentResponse(JOptionPane.showInputDialog(null, "Ingrese el comment response"));
        response.setOptionText(JOptionPane.showInputDialog(null, "Ingrese el option text"));
        response.setCategoryCatalogId(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la categoria")));
        response.setQuestionId(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la pregunta")));
        response.setParentResponseId(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del parent reponse")));
        updateResponseUseCase.execute(response);
    }

    public void deleteResponse() {
        Response response = findByIdResponse();
        deleteResponseUseCase.execute(response.getId());
    }

    public void showAllResponses(List<Response> responses) {
        String[] columns = { "ID", "Created", "Updated", "Opt Value", "Type Html", "Comment Res", "Opt Text",
                "Cat Catalog", "Question", "Parent" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        responses.forEach(response -> {
            Object[] row = {
                    response.getId(),
                    response.getCreated_at(),
                    response.getUpdated_at(),
                    response.getOptionValue(),
                    response.getTypeComponentHtml(),
                    response.getCommentResponse(),
                    response.getOptionText(),
                    response.getCategoryCatalogId(),
                    response.getQuestionId(),
                    response.getParentResponseId()
            };
            model.addRow(row);
        });
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        JOptionPane.showMessageDialog(null, panel, "Responses List", JOptionPane.PLAIN_MESSAGE);
    }

    public void findAllResponses() {
        List<Response> responses = findAllResponseUseCase.execute();
        showAllResponses(responses);
    }
}
