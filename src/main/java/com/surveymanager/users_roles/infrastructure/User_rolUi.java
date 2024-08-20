package com.surveymanager.users_roles.infrastructure;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.users_roles.aplication.CreateUser_rolUseCase;
import com.surveymanager.users_roles.aplication.DeleteUser_rolUseCase;
import com.surveymanager.users_roles.aplication.FindAllUser_rolUseCase;
import com.surveymanager.users_roles.aplication.FindUser_rolUseCase;
import com.surveymanager.users_roles.aplication.UpdateUser_rolUseCase;
import com.surveymanager.users_roles.domain.User_rol;
import com.surveymanager.users_roles.domain.User_rolService;

public class User_rolUi {
    private User_rolService user_rolService;
    private CreateUser_rolUseCase createUser_rolUseCase;
    private DeleteUser_rolUseCase deleteUser_rolUseCase;
    private FindAllUser_rolUseCase findAllUser_rolUseCase;
    private FindUser_rolUseCase findUser_rolUseCase;
    private UpdateUser_rolUseCase updateUser_rolUseCase;

    public User_rolUi() {
        this.user_rolService = new User_rolRepository();
        this.createUser_rolUseCase = new CreateUser_rolUseCase(user_rolService);
        this.deleteUser_rolUseCase = new DeleteUser_rolUseCase(user_rolService);
        this.findAllUser_rolUseCase = new FindAllUser_rolUseCase(user_rolService);
        this.findUser_rolUseCase = new FindUser_rolUseCase(user_rolService);
        this.updateUser_rolUseCase = new UpdateUser_rolUseCase(user_rolService);
    }

    public void mainMenu() {
        String opciones = "1. Add User_rol\n2. Search user_rol\n3. Update User_rol\n4. Delete User_rol\n5 List Users_user_roles\n6. Return to Main Menu";
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
                        addUser_rol();
                        break;
                    case 2:
                        findUser_rol();
                        break;
                    case 3:
                        updateUser_rol();
                        break;
                    case 4:
                        deleteUser_rol();
                        break;
                    case 5:
                        findAllUser_rol();
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

    public void addUser_rol() {
        int id_user = Integer.parseInt(JOptionPane.showInputDialog(null, "id_user Name:"));
        int id_rol = Integer.parseInt(JOptionPane.showInputDialog(null, "id_rol Name:"));
        User_rol user_rol = new User_rol();
        user_rol.setUser_id(id_user);
        user_rol.setRole_id(id_rol);
        createUser_rolUseCase.execute(user_rol);
        JOptionPane.showMessageDialog(null,
                "User_rol created:\nUser_id: " + user_rol.getUser_id() + "\nRole_id: " + user_rol.getRole_id());
    }

    public Optional<User_rol> findUser_rol() {
        int id_user = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID_User:"));
        int id_role = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID_rol:"));
        Optional<User_rol> user_rol = findUser_rolUseCase.execute(id_user, id_role);
        showUser_rol(user_rol);
        return user_rol;
    }

    public void updateUser_rol() {
        Optional<User_rol> user_rolOptional = findUser_rol();
        if (user_rolOptional.isPresent()) {
            User_rol user_rol = user_rolOptional.get();
            int id_user = Integer
                    .parseInt(JOptionPane.showInputDialog(null, "Ingrese el id_user", user_rol.getUser_id()));
            int id_rol = Integer
                    .parseInt(JOptionPane.showInputDialog(null, "Ingrese el id_rol", user_rol.getUser_id()));
            user_rol.setUser_id(id_user);
            user_rol.setRole_id(id_rol);
            updateUser_rolUseCase.execute(user_rol);
            showUser_rol(user_rolOptional);
        }
    }

    public void deleteUser_rol() {
        Optional<User_rol> user_rolOptional = findUser_rol();
        if (user_rolOptional.isPresent()) {
            User_rol user_rol = user_rolOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar el User_rol?\nuser_id: " + user_rol.getUser_id() + "\nrol_id: "
                            + user_rol.getRole_id(),
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteUser_rolUseCase.execute(user_rol.getUser_id(), user_rol.getUser_id());
                JOptionPane.showMessageDialog(null,
                        "User_rol deleted:\nid_user: " + user_rol.getUser_id() + "\nRol_id: " + user_rol.getRole_id());
            }
        }
    }

    public List<User_rol> findAllUser_rol() {
        List<User_rol> users_user_roles = findAllUser_rolUseCase.execute();

        String[] columns = { "User_id", "Rol_id" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (User_rol user_rol : users_user_roles) {
            Object[] row = {
                    user_rol.getUser_id(),
                    user_rol.getRole_id()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scuser_rollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scuser_rollPane);

        JOptionPane.showMessageDialog(null, panel, "User_rol List", JOptionPane.PLAIN_MESSAGE);
        return users_user_roles;
    }

    public void showUser_rol(Optional<User_rol> user_rol) {
        String[] columns = { "User_id", "Rol_id" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (user_rol.isPresent()) {
            User_rol c = user_rol.get();
            Object[] row = {
                    c.getUser_id(),
                    c.getRole_id()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Users_roles found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scuser_rollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scuser_rollPane);

        JOptionPane.showMessageDialog(null, panel, "User_rol Details", JOptionPane.PLAIN_MESSAGE);
    }
}
