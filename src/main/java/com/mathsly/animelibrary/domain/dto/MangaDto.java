package com.mathsly.animelibrary.domain.dto;

import com.mathsly.animelibrary.domain.entities.Manga;

public class MangaDto {
    private int currentChapter;
    private int lastChapter;
    private boolean isUpToDate;
    private boolean isFinished;
    private Long id;

    public MangaDto() {
        // Default constructor
    }

    public MangaDto(Manga manga) {
        this.currentChapter = manga.getCurrentChapter();
        this.lastChapter = manga.getLastChapter();
        this.isUpToDate = manga.isUpToDate();
        this.isFinished = manga.isFinished();
        this.id = manga.getId();
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

    public Manga toEntity() {
        Manga manga = new Manga();
        manga.setCurrentChapter(this.currentChapter);
        manga.setLastChapter(this.lastChapter);
        manga.setUpToDate(this.isUpToDate);
        manga.setFinished(this.isFinished);
        manga.setId(this.id);
        return manga;
    }
}
