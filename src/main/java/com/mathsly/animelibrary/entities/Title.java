package com.mathsly.animelibrary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Title {
    @OneToOne
    private Manga manga;
    @OneToOne
    private Anime anime;
    @OneToOne
    private Novel novel;
    @Id
    private Long id;

    public Title(){};

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
