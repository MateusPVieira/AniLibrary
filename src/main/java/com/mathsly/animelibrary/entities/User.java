package com.mathsly.animelibrary.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User {
    @OneToOne
    private Library library;
    @Id
    private Long id;

    public User(){};

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
