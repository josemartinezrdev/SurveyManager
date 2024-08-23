package com.surveymanager.chapter.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.chapter.domain.service.ChapterService;

public class ChapterRepository implements ChapterService {

    private Connection connection;

    public ChapterRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createChapter(Chapter chapter) {
        try {
            String query = "INSERT INTO chapter (created_at, updated_at, chapter_number, chapter_title, survey_id) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, chapter.getCreated_at());
            ps.setTimestamp(2, chapter.getUpdated_at());
            ps.setString(3, chapter.getChapter_number());
            ps.setString(4, chapter.getChapter_title());
            ps.setInt(5, chapter.getSurvey_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateChapter(Chapter chapter) {
        try {
            String query = """
                    UPDATE chapter SET updated_at = ?, chapter_number = ?, chapter_title = ?, survey_id = ?
                    WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, chapter.getUpdated_at());
            ps.setString(2, chapter.getChapter_number());
            ps.setString(3, chapter.getChapter_title());
            ps.setInt(4, chapter.getSurvey_id());
            ps.setInt(5, chapter.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteChapter(int id) {
        try {
            String query = "DELETE FROM chapter WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Chapter> findAllChapters() {
        List<Chapter> chapters = new ArrayList<>();
        try {
            String query = """
                    SELECT id, created_at, updated_at, chapter_number, chapter_title, survey_id
                    FROM chapter ORDER BY id ASC
                    """;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Chapter chapter = new Chapter(
                        rs.getInt("id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("chapter_number"),
                        rs.getString("chapter_title"),
                        rs.getInt("survey_id"));
                chapters.add(chapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapters;
    }

    @Override
    public Chapter findByIdChapter(int id) {
        Chapter chapter = new Chapter();
        try {
            String query = """
                    SELECT id, created_at, updated_at, chapter_number, chapter_title, survey_id
                    FROM chapter WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                chapter.setId(rs.getInt("id"));
                chapter.setCreated_at(rs.getTimestamp("created_at"));
                chapter.setUpdated_at(rs.getTimestamp("updated_at"));
                chapter.setChapter_number(rs.getString("chapter_number"));
                chapter.setChapter_title(rs.getString("chapter_title"));
                chapter.setSurvey_id(rs.getInt("survey_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapter;
    }
}
