package com.surveymanager.chapter.application;

import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.chapter.domain.service.ChapterService;

public class CreateChapterUseCase {
    private final ChapterService chapterService;

    public CreateChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void execute(Chapter chapter) {
        chapterService.createChapter(chapter);
    }
}
