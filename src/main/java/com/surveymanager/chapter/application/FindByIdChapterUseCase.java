package com.surveymanager.chapter.application;

import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.chapter.domain.service.ChapterService;

public class FindByIdChapterUseCase {
    private final ChapterService chapterService;

    public FindByIdChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public Chapter execute(int id) {
        return chapterService.findByIdChapter(id);
    }

}
