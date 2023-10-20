package com.mathsly.animelibrary.domain.dto;


import com.mathsly.animelibrary.domain.entities.Anime;

public class AnimeDto {
    private int currentChapter;
    private int lastChapter;
    private boolean isUpToDate;
    private boolean isFinished;
    private Long id;

    public AnimeDto() {
        // Default constructor
    }

    public AnimeDto(Anime anime) {
        this.currentChapter = anime.getCurrentChapter();
        this.lastChapter = anime.getLastChapter();
        this.isUpToDate = anime.isUpToDate();
        this.isFinished = anime.isFinished();
        this.id = anime.getId();
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

    public Anime toEntity() {
        Anime anime = new Anime();
        anime.setCurrentChapter(this.currentChapter);
        anime.setLastChapter(this.lastChapter);
        anime.setUpToDate(this.isUpToDate);
        anime.setFinished(this.isFinished);
        anime.setId(this.id);
        return anime;
    }
}
