package com.mathsly.animelibrary.domain.dto;

import com.mathsly.animelibrary.domain.entities.Novel;

public class NovelDto {
    private int currentChapter;
    private int lastChapter;
    private boolean isUpToDate;
    private boolean isFinished;
    private Long id;

    public NovelDto() {
        // Default constructor
    }

    public NovelDto(Novel novel) {
        this.currentChapter = novel.getCurrentChapter();
        this.lastChapter = novel.getLastChapter();
        this.isUpToDate = novel.isUpToDate();
        this.isFinished = novel.isFinished();
        this.id = novel.getId();
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }

    public int getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(int lastChapter) {
        this.lastChapter = lastChapter;
    }

    public boolean isUpToDate() {
        return isUpToDate;
    }

    public void setUpToDate(boolean upToDate) {
        isUpToDate = upToDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Novel toEntity() {
        Novel novel = new Novel();
        novel.setCurrentChapter(this.currentChapter);
        novel.setLastChapter(this.lastChapter);
        novel.setUpToDate(this.isUpToDate);
        novel.setFinished(this.isFinished);
        novel.setId(this.id);
        return novel;
    }
}

