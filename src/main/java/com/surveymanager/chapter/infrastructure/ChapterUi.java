package com.surveymanager.chapter.infrastructure;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.surveymanager.chapter.application.CreateChapterUseCase;
import com.surveymanager.chapter.application.DeleteChapterUseCase;
import com.surveymanager.chapter.application.FindAllChapterUseCase;
import com.surveymanager.chapter.application.FindByIdChapterUseCase;
import com.surveymanager.chapter.application.UpdateChapterUseCase;
import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.chapter.domain.service.ChapterService;

public class ChapterUi {
    private ChapterService chapterService;

    private CreateChapterUseCase createChapterUseCase;
    private UpdateChapterUseCase updateChapterUseCase;
    private DeleteChapterUseCase deleteChapterUseCase;
    private FindAllChapterUseCase findAllChapterUseCase;
    private FindByIdChapterUseCase findByIdChapterUseCase;

    public ChapterUi() {
        this.chapterService = new ChapterRepository();
        this.createChapterUseCase = new CreateChapterUseCase(chapterService);
        this.updateChapterUseCase = new UpdateChapterUseCase(chapterService);
        this.deleteChapterUseCase = new DeleteChapterUseCase(chapterService);
        this.findAllChapterUseCase = new FindAllChapterUseCase(chapterService);
        this.findByIdChapterUseCase = new FindByIdChapterUseCase(chapterService);
    }

    public void menuChapter() {
        int opt = 0;
        String opts = "1. Create Chapter\n2. Update Chapter\n3. Delete Chapter\n4. Search Chapters\n5. Search Chapter\n6. Quit";
        do {
            try {
                opt = Integer.parseInt(JOptionPane.showInputDialog(null, opts));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el valor ingresado");
                continue;
            }
            switch (opt) {
                case 1:
                    createChapter();
                    break;
                case 2:
                    updateChapter();
                    break;
                case 3:
                    deleteChapter();
                    break;
                case 4:
                    findAllChapters();
                    break;
                case 5:
                    findByIdChapter();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error en la opcion ingresada");
                    break;
            }
        } while (opt != 6);
    }

    public void createChapter() {
        Chapter chapter = new Chapter();
        chapter.updateDate();
        chapter.createdDate();
        chapter.setChapter_number(JOptionPane.showInputDialog(null, "Ingrese el numero del capitulo"));
        chapter.setChapter_title(JOptionPane.showInputDialog(null, "Ingrese el titulo del capitulo"));
        chapter.setSurvey_id(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese id de la encuesta del capitulo")));
        createChapterUseCase.execute(chapter);
    }

    public void showByIdChapter(Chapter chapter) {
        String[] columns = { "ID", "Created", "Updated", "Desc", "Name", "ID Survey" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        Object[] row = {
                chapter.getId(),
                chapter.getCreated_at(),
                chapter.getUpdated_at(),
                chapter.getChapter_number(),
                chapter.getChapter_title(),
                chapter.getSurvey_id()
        };
        model.addRow(row);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Chapter By Id", JOptionPane.PLAIN_MESSAGE);

    }

    public Chapter findByIdChapter() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del capitulo"));
        Chapter chapter = findByIdChapterUseCase.execute(id);
        showByIdChapter(chapter);
        return chapter;
    }

    public void updateChapter() {
        Chapter chapter = findByIdChapter();
        chapter.updateDate();
        chapter.setChapter_number(JOptionPane.showInputDialog(null, "Ingrese el numero del capitulo"));
        chapter.setChapter_title(JOptionPane.showInputDialog(null, "Ingrese el titulo del capitulo"));
        chapter.setSurvey_id(
                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la encuesta del capitulo")));
        updateChapterUseCase.execute(chapter);
    }

    public void deleteChapter() {
        Chapter chapter = findByIdChapter();
        deleteChapterUseCase.execute(chapter.getId());
    }

    public void showAllChapters(List<Chapter> chapters) {
        String[] columns = { "ID", "Created", "Updated", "Desc", "Name", "ID Survey" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        chapters.forEach(chapter -> {
            Object[] row = {
                    chapter.getId(),
                    chapter.getCreated_at(),
                    chapter.getUpdated_at(),
                    chapter.getChapter_number(),
                    chapter.getChapter_title(),
                    chapter.getSurvey_id()
            };
            model.addRow(row);
        });
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        JOptionPane.showMessageDialog(null, panel, "Chapters List", JOptionPane.PLAIN_MESSAGE);
    }

    public void findAllChapters() {
        List<Chapter> chapters = findAllChapterUseCase.execute();
        showAllChapters(chapters);
    }
}
