package com.surveymanager.chapter.application;

import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.chapter.domain.service.ChapterService;

public class UpdateChapterUseCase {

    private final ChapterService chapterService;

    public UpdateChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void execute(Chapter chapter) {
        chapterService.updateChapter(chapter);
    }
}
