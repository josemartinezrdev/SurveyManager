package com.surveymanager.chapter.domain.service;

import java.util.List;

import com.surveymanager.chapter.domain.entity.Chapter;

public interface ChapterService {
    public void createChapter(Chapter chapter);

    public void updateChapter(Chapter chapter);

    public void deleteChapter(int id);

    public List<Chapter> findAllChapters();

    public Chapter findByIdChapter(int id);

}
