package com.surveymanager.chapter.application;

import java.util.List;

import com.surveymanager.chapter.domain.entity.Chapter;
import com.surveymanager.chapter.domain.service.ChapterService;

public class FindAllChapterUseCase {
    private final ChapterService chapterService;


    public FindAllChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    
    public List<Chapter> execute() {
        return chapterService.findAllChapters();
    }

}
