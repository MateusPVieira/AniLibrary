package com.mathsly.animelibrary.domain.dto;

import com.mathsly.animelibrary.domain.entities.Library;

import java.util.List;
import java.util.stream.Collectors;


public class LibraryDto {
    private Long id;
    private List<TitleDto> titles;  // Lista de objetos TitleDto

    public LibraryDto() {
        // Construtor padrão
    }

    public LibraryDto(Library library) {
        this.id = library.getId();
        this.titles = library.getTitles().stream().map(TitleDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TitleDto> getTitles() {
        return titles;
    }

    public void setTitles(List<TitleDto> titles) {
        this.titles = titles;
    }

    public Library toEntity() {
        Library library = new Library();
        library.setId(this.id);
        // Mapear a lista de TitleDto para uma lista de Title
        library.setTitles(this.titles.stream().map(TitleDto::toEntity).collect(Collectors.toList()));
        return library;
    }
}
