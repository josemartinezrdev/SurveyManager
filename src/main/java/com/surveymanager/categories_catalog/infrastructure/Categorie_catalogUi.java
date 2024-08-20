package com.surveymanager.categories_catalog.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.categories_catalog.aplication.CreateCategorie_catalogUseCase;
import com.surveymanager.categories_catalog.aplication.DeleteCategorie_catalogUseCase;
import com.surveymanager.categories_catalog.aplication.FindAllCategorie_catalogUseCase;
import com.surveymanager.categories_catalog.aplication.FindCategorie_catalogUseCase;
import com.surveymanager.categories_catalog.aplication.UpdateCategorie_catalogUseCase;
import com.surveymanager.categories_catalog.domain.Categorie_catalog;
import com.surveymanager.categories_catalog.domain.Categorie_catalogService;

public class Categorie_catalogUi {
    private Categorie_catalogService categorie_catalogService;
    private CreateCategorie_catalogUseCase createCategorie_catalogUseCase;
    private DeleteCategorie_catalogUseCase deleteCategorie_catalogUseCase;
    private FindAllCategorie_catalogUseCase findAllCategorie_catalogUseCase;
    private FindCategorie_catalogUseCase findCategorie_catalogUseCase;
    private UpdateCategorie_catalogUseCase updateCategorie_catalogUseCase;

    public Categorie_catalogUi() {
        this.categorie_catalogService = new Categorie_catalogRepository();
        this.createCategorie_catalogUseCase = new CreateCategorie_catalogUseCase(categorie_catalogService);
        this.deleteCategorie_catalogUseCase = new DeleteCategorie_catalogUseCase(categorie_catalogService);
        this.findAllCategorie_catalogUseCase = new FindAllCategorie_catalogUseCase(categorie_catalogService);
        this.findCategorie_catalogUseCase = new FindCategorie_catalogUseCase(categorie_catalogService);
        this.updateCategorie_catalogUseCase = new UpdateCategorie_catalogUseCase(categorie_catalogService);
    }

    public void mainMenu() {
        String opciones = "1. Add Categorie_catalog\n2. Search categorie_catalog\n3. Update Categorie_catalog\n4. Delete Categorie_catalog\n5 List Categorie_cataloges\n6. Return to Main Menu";
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
                        addCategorie_catalog();
                        break;
                    case 2:
                        findCategorie_catalog();
                        break;
                    case 3:
                        updateCategorie_catalog();
                        break;
                    case 4:
                        deleteCategorie_catalog();
                        break;
                    case 5:
                        findAllCategorie_catalog();
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

    public void addCategorie_catalog() {
        String name = JOptionPane.showInputDialog(null, "Categorie_catalog Name:");
        Categorie_catalog categorie_catalog = new Categorie_catalog();

        categorie_catalog.setName(name);
        categorie_catalog.setCreated_At(LocalDateTime.now());
        categorie_catalog.setUpdated_At(LocalDateTime.now());

        createCategorie_catalogUseCase.execute(categorie_catalog);
        categorie_catalog.setCreated_At(LocalDateTime.now());
    }

    public Optional<Categorie_catalog> findCategorie_catalog() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del Categorie_catalog:"));
        Optional<Categorie_catalog> categorie_catalog = findCategorie_catalogUseCase.execute(id);
        showCategorie_catalog(categorie_catalog);
        return categorie_catalog;
    }

    public void updateCategorie_catalog() {
        Optional<Categorie_catalog> categorie_catalogOptional = findCategorie_catalog();
        if (categorie_catalogOptional.isPresent()) {
            Categorie_catalog categorie_catalog = categorie_catalogOptional.get();
            String newName = JOptionPane.showInputDialog(null, "Ingrese el Nombre de la Categorie_catalog",

            categorie_catalog.getName());
            if (newName == null)
            return;
            
            categorie_catalog.setName(newName);
            categorie_catalog.setUpdated_At(LocalDateTime.now());
            updateCategorie_catalogUseCase.execute(categorie_catalog);
            showCategorie_catalog(categorie_catalogOptional);
        }
    }

    public void deleteCategorie_catalog() {
        Optional<Categorie_catalog> categorie_catalogOptional = findCategorie_catalog();
        if (categorie_catalogOptional.isPresent()) {
            Categorie_catalog categorie_catalog = categorie_catalogOptional.get();

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar el Categorie_catalog?\nCode: " + categorie_catalog.getId() + "\nName: "
                            + categorie_catalog.getName(),
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteCategorie_catalogUseCase.execute(categorie_catalog.getId());
                JOptionPane.showMessageDialog(null, "Categorie_catalog deleted:\nCode: " + categorie_catalog.getId()
                        + "\nName: " + categorie_catalog.getName());
            }
        }
    }

    public List<Categorie_catalog> findAllCategorie_catalog() {
        List<Categorie_catalog> categorie_cataloges = findAllCategorie_catalogUseCase.execute();

        String[] columns = { "Id","CreatedAt","UpdatedAt","Name" };


        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Categorie_catalog categorie_catalog : categorie_cataloges) {
            Object[] row = {
                    categorie_catalog.getId(),
                    categorie_catalog.getCreated_At(),
                    categorie_catalog.getUpdated_At(),
                    categorie_catalog.getName()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane sccategorie_cataloglPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(sccategorie_cataloglPane);

        JOptionPane.showMessageDialog(null, panel, "Categorie_catalog List", JOptionPane.PLAIN_MESSAGE);
        return categorie_cataloges;
    }

    public void showCategorie_catalog(Optional<Categorie_catalog> categorie_catalog) {

        String[] columns = { "Id","CreatedAt","UpdatedAt","Name" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        if (categorie_catalog.isPresent()) {
            Categorie_catalog c = categorie_catalog.get();
            Object[] row = {
                    c.getId(),
                    c.getCreated_At(),
                    c.getUpdated_At(),
                    c.getName()
            };
            model.addRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "No Categorie_cataloges found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable table = new JTable(model);
        JScrollPane sccategorie_cataloglPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(sccategorie_cataloglPane);

        JOptionPane.showMessageDialog(null, panel, "Categorie_catalog Details", JOptionPane.PLAIN_MESSAGE);
    }
}
