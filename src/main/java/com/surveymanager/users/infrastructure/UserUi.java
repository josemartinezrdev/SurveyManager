package com.surveymanager.users.infrastructure;

import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.users.aplication.CreateUserUseCase;
import com.surveymanager.users.aplication.DeleteUserUseCase;
import com.surveymanager.users.aplication.FindAllUserUseCase;
import com.surveymanager.users.aplication.FindUserByNameUseCase;
import com.surveymanager.users.aplication.FindUserUseCase;
import com.surveymanager.users.aplication.UpdateUserUseCase;
import com.surveymanager.users.domain.User;
import com.surveymanager.users.domain.UserService;

public class UserUi {

    private UserService userService;
    private CreateUserUseCase createUserUseCase;
    private FindUserUseCase findUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    private FindAllUserUseCase findAllUserUseCase;
    private FindUserByNameUseCase findUserByNameUseCase;

    public UserUi() {
        this.userService = new UserRepository();
        this.createUserUseCase = new CreateUserUseCase(userService);
        this.deleteUserUseCase = new DeleteUserUseCase(userService);
        this.findAllUserUseCase = new FindAllUserUseCase(userService);
        this.findUserUseCase = new FindUserUseCase(userService);
        this.updateUserUseCase = new UpdateUserUseCase(userService);
        this.findUserByNameUseCase = new FindUserByNameUseCase(userService);
    }

    public void mainMenu() {
        String opciones = "1. Add User\n2. Search user\n3. Update User\n4. Delete User\n5 List Users\n6. Return to Main Menu";
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
                        addUser();
                        break;
                    case 2:
                        findUser();
                        break;
                    case 3:
                        updateUser();
                        break;
                    case 4:
                        deleteUser();
                        break;
                    case 5:
                        findAllUser();
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

    public void addUser() {
        User user = new User();
        user.setUsername(JOptionPane.showInputDialog(null, "Insert Username"));
        user.setPassword(JOptionPane.showInputDialog(null, "Insert Password"));
        createUserUseCase.execute(user);
    }

    public Optional<User> findUser() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la User: "));
        Optional<User> user = findUserUseCase.execute(id);
        showUser(user);
        return user;
    }

    public Boolean findUserByName() {
        String userName;
        String password;

        do {
            userName = JOptionPane.showInputDialog(null, "Ingrese el UserName: ");
            password = JOptionPane.showInputDialog(null, "Ingrese el password: ");

            if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "UserName y Password no pueden estar vacíos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty());

        return findUserByNameUseCase.execute(userName, password);
    }

    public void updateUser() {
        Optional<User> userOptional = findUser();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(JOptionPane.showInputDialog(null, "Insert Username"));
            user.setPassword(JOptionPane.showInputDialog(null, "Insert Password"));
            updateUserUseCase.execute(user);
            showUser(userOptional);
        }

    }

    public void deleteUser() {
        Optional<User> userOptional = findUser();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            deleteUserUseCase.execute(user.getId());
        }
    }

    public void findAllUser() {
        List<User> useres = findAllUserUseCase.execute();

        String[] columns = { "ID", "Enabled", "Username", "Password" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (User user : useres) {
            Object[] row = {
                    user.getId(),
                    user.getEnabled(),
                    user.getUsername(),
                    user.getPassword()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Users List", JOptionPane.PLAIN_MESSAGE);

    }

    public void showUser(Optional<User> user) {

        String[] columns = { "ID", "Enabled", "Username", "Password" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (user.isPresent()) {
            User userd = user.get();
            Object[] row = {
                    userd.getId(),
                    userd.getEnabled(),
                    userd.getUsername(),
                    userd.getPassword()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No hay user para mostrar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Users List", JOptionPane.PLAIN_MESSAGE);

    }
}
