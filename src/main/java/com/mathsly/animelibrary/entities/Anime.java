package com.mathsly.animelibrary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Anime{
    private int currentChapter;
    private int lastChapter;
    private boolean isUpToDate;
    private boolean isFinished;
    @Id
    private Long id;

    public Anime(){}

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
