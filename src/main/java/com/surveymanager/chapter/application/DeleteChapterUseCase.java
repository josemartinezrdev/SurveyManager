package com.surveymanager.chapter.application;

import com.surveymanager.chapter.domain.service.ChapterService;

public class DeleteChapterUseCase {
    private final ChapterService chapterService;

    public DeleteChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void execute(int id) {
        chapterService.deleteChapter(id);
    }

}
