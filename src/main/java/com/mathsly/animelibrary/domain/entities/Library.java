package com.mathsly.animelibrary.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Library {
    @OneToMany
    private List<Title> titles;
    @Id
    private Long id;

    public Library(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }
}
