package com.surveymanager.roles.infrastructure;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.roles.application.CreateRolUseCase;
import com.surveymanager.roles.application.DeleteRolUseCase;
import com.surveymanager.roles.application.FindAllRolUseCase;
import com.surveymanager.roles.application.FindRolUseCase;
import com.surveymanager.roles.application.UpdateRolUseCase;
import com.surveymanager.roles.domain.Rol;
import com.surveymanager.roles.domain.RolService;

public class RolUi {
    private RolService rolService;
    private CreateRolUseCase createRolUseCase;
    private DeleteRolUseCase deleteRolUseCase;
    private FindAllRolUseCase findAllRolUseCase;
    private FindRolUseCase findRolUseCase;
    private UpdateRolUseCase updateRolUseCase;

    public RolUi() {
        this.rolService = new RolRepository();
        this.createRolUseCase = new CreateRolUseCase(rolService);
        this.deleteRolUseCase = new DeleteRolUseCase(rolService);
        this.findAllRolUseCase = new FindAllRolUseCase(rolService);
        this.findRolUseCase = new FindRolUseCase(rolService);
        this.updateRolUseCase = new UpdateRolUseCase(rolService);
    }

    public void mainMenu() {
        String opciones = "1. Add Rol\n2. Search rol\n3. Update Rol\n4. Delete Rol\n5 List Roles\n6. Return to Main Menu";
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
                        addRol();
                        break;
                    case 2:
                        findRol();
                        break;
                    case 3:
                        updateRol();
                        break;
                    case 4:
                        deleteRol();
                        break;
                    case 5:
                        findAllRol();
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

    public void addRol() {

        String name = JOptionPane.showInputDialog(null, "Rol Name:");
        Rol rol = new Rol();
        rol.setName(name);
        createRolUseCase.execute(rol);
        JOptionPane.showMessageDialog(null, "Rol created:\nId: " + rol.getId() + "\nName: " + rol.getName());
    }

    public Optional<Rol> findRol() {
        int id = 0;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del Rol:"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el dato ingresado");
        }
        Optional<Rol> rol = findRolUseCase.execute(id);
        showRol(rol);
        return rol;
    }

    public void updateRol() {
        Optional<Rol> rolOptional = findRol();
        if (rolOptional.isPresent()) {
            Rol rol = rolOptional.get();
            String newName = JOptionPane.showInputDialog(null, "Ingrese el Nombre de la Rol", rol.getName());
            if (newName == null)
                return;

            rol.setName(newName);
            updateRolUseCase.execute(rol);
            showRol(rolOptional);
        }
    }

    public void deleteRol() {
        Optional<Rol> rolOptional = findRol();
        if (rolOptional.isPresent()) {
            Rol rol = rolOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar el Rol?\nCode: " + rol.getId() + "\nName: " + rol.getName(),
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteRolUseCase.execute(rol.getId());
                JOptionPane.showMessageDialog(null, "Rol deleted:\nCode: " + rol.getId() + "\nName: " + rol.getName());
            }
        }
    }

    public List<Rol> findAllRol() {
        List<Rol> roles = findAllRolUseCase.execute();

        String[] columns = { "ID", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Rol rol : roles) {
            Object[] row = {
                    rol.getId(),
                    rol.getName()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Rol List", JOptionPane.PLAIN_MESSAGE);
        return roles;
    }

    public void showRol(Optional<Rol> rol) {
        String[] columns = { "ID", "Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (rol.isPresent()) {
            Rol c = rol.get();
            Object[] row = {
                    c.getId(),
                    c.getName()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Roles found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Rol Details", JOptionPane.PLAIN_MESSAGE);
    }
}
