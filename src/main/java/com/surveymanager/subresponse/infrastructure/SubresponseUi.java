package com.surveymanager.subresponse.infrastructure;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.subresponse.application.CreateSubresponseUseCase;
import com.surveymanager.subresponse.application.DeleteSubresponseUseCase;
import com.surveymanager.subresponse.application.FindAllSubresponseUseCase;
import com.surveymanager.subresponse.application.FindSubresponseUseCase;
import com.surveymanager.subresponse.application.UpdateSubresponseUseCase;
import com.surveymanager.subresponse.domain.Subresponse;
import com.surveymanager.subresponse.domain.SubresponseService;

public class SubresponseUi {

    private SubresponseService subresponseService;
    private CreateSubresponseUseCase createSubresponseUseCase;
    private DeleteSubresponseUseCase deleteSubresponseUseCase;
    private FindAllSubresponseUseCase findAllSubresponseUseCase;
    private FindSubresponseUseCase findSubresponseUseCase;
    private UpdateSubresponseUseCase updateSubresponseUseCase;

    public SubresponseUi() {
        this.subresponseService = new SubresponseRepository();
        this.createSubresponseUseCase = new CreateSubresponseUseCase(subresponseService);
        this.deleteSubresponseUseCase = new DeleteSubresponseUseCase(subresponseService);
        this.findAllSubresponseUseCase = new FindAllSubresponseUseCase(subresponseService);
        this.findSubresponseUseCase = new FindSubresponseUseCase(subresponseService);
        this.updateSubresponseUseCase = new UpdateSubresponseUseCase(subresponseService);
    }

    public void mainMenu() {
        String opciones = "1. Add Subresponse\n2. Search subresponse\n3. Update Subresponse\n4. Delete Subresponse\n5 List Subresponsees\n6. Return to Main Menu";
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
                        addSubresponse();
                        break;
                    case 2:
                        findSubresponse();
                        break;
                    case 3:
                        updateSubresponse();
                        break;
                    case 4:
                        deleteSubresponse();
                        break;
                    case 5:
                        findAllSubresponse();
                        break;
                    case 6:
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
        } while (op != 6);
    }

    public void addSubresponse() {

        String component_html = JOptionPane.showInputDialog(null, " component_html:");
        String subresponse_text = JOptionPane.showInputDialog(null, " subresponse_text:");

        int subresponse_number = 0;
        int responseoptions_id = 0;

        try {
            subresponse_number = Integer.parseInt(JOptionPane.showInputDialog(null, " subresponse_number:"));
            responseoptions_id = Integer.parseInt(JOptionPane.showInputDialog(null, " responseoptions_id:"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el dato ingresado");
        }

        Subresponse subresponse = new Subresponse();
        subresponse.updateDate();
        subresponse.createdDate();

        subresponse.setSubresponse_number(subresponse_number);
        subresponse.setComponent_html(component_html);
        subresponse.setSubresponse_text(subresponse_text);
        subresponse.setResponseoptions_id(responseoptions_id);

        createSubresponseUseCase.execute(subresponse);
    }

    public Optional<Subresponse> findSubresponse() {
        int id = 0;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del Subresponse:"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el dato ingresado");
        }
        Optional<Subresponse> subresponse = findSubresponseUseCase.execute(id);
        showSubresponse(subresponse);
        return subresponse;
    }

    public void updateSubresponse() {
        Optional<Subresponse> subresponseOptional = findSubresponse();
        if (subresponseOptional.isPresent()) {
            Subresponse subresponse = subresponseOptional.get();
            String component_html = JOptionPane.showInputDialog(null, "Ingrese el component_html",
                    subresponse.getComponent_html());
            String subresponse_text = JOptionPane.showInputDialog(null, "Ingrese subresponse_text",
                    subresponse.getSubresponse_text());

            int subresponse_number = 0;
            int responseoptions_id = 0;

            try {

                subresponse_number = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Ingrese subresponse_number", subresponse.getSubresponse_number()));
                responseoptions_id = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Ingrese responseoptions_id", subresponse.getResponseoptions_id()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el dato ingresado");
            }

            subresponse.setSubresponse_number(subresponse_number);
            subresponse.setComponent_html(component_html);
            subresponse.setSubresponse_text(subresponse_text);
            subresponse.setResponseoptions_id(responseoptions_id);

            subresponse.updateDate();

            updateSubresponseUseCase.execute(subresponse);
            showSubresponse(subresponseOptional);
        }
    }

    public void deleteSubresponse() {
        Optional<Subresponse> subresponseOptional = findSubresponse();
        if (subresponseOptional.isPresent()) {
            Subresponse subresponse = subresponseOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar el Subresponse?\nCode: " + subresponse.getId(),
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteSubresponseUseCase.execute(subresponse.getId());
                JOptionPane.showMessageDialog(null, "Subresponse deleted:\nCode: " + subresponse.getId());
            }
        }
    }

    public List<Subresponse> findAllSubresponse() {
        List<Subresponse> subresponsees = findAllSubresponseUseCase.execute();

        String[] columns = { "Id", "Subresponse_number", "Created_at", "Updated_at", "Component_html",
                "Subresponse_text", "Responseoptions_id" };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Subresponse subresponse : subresponsees) {
            Object[] row = {
                    subresponse.getId(),
                    subresponse.getSubresponse_number(),
                    subresponse.getCreated_at(),
                    subresponse.getUpdated_at(),
                    subresponse.getComponent_html(),
                    subresponse.getSubresponse_text(),
                    subresponse.getResponseoptions_id()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scsubresponselPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scsubresponselPane);

        JOptionPane.showMessageDialog(null, panel, "Subresponse List", JOptionPane.PLAIN_MESSAGE);
        return subresponsees;
    }

    public void showSubresponse(Optional<Subresponse> subresponse) {

        String[] columns = { "Id", "Subresponse_number", "Created_at", "Updated_at", "Component_html",
                "Subresponse_text", "Responseoptions_id" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (subresponse.isPresent()) {
            Subresponse c = subresponse.get();
            Object[] row = {
                    c.getId(),
                    c.getSubresponse_number(),
                    c.getCreated_at(),
                    c.getUpdated_at(),
                    c.getComponent_html(),
                    c.getSubresponse_text(),
                    c.getResponseoptions_id()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Subresponsees found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scsubresponselPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scsubresponselPane);

        JOptionPane.showMessageDialog(null, panel, "Subresponse Details", JOptionPane.PLAIN_MESSAGE);
    }
}
