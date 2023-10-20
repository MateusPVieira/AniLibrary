package com.mathsly.animelibrary.domain.dto;


import com.mathsly.animelibrary.domain.entities.Title;

public class TitleDto {
    private Long id;
    private MangaDto manga;
    private AnimeDto anime;
    private NovelDto novel;
    private String author;

    public TitleDto() {
        // Construtor padrão
    }

    public TitleDto(Title title) {
        this.id = title.getId();

        // Mapeie os relacionamentos para os respectivos DTOs
        if (title.getManga() != null) {
            this.manga = new MangaDto(title.getManga());
        }
        if (title.getAnime() != null) {
            this.anime = new AnimeDto(title.getAnime());
        }
        if (title.getNovel() != null) {
            this.novel = new NovelDto(title.getNovel());
        }

        this.author = title.getAuthor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MangaDto getManga() {
        return manga;
    }

    public void setManga(MangaDto manga) {
        this.manga = manga;
    }

    public AnimeDto getAnime() {
        return anime;
    }

    public void setAnime(AnimeDto anime) {
        this.anime = anime;
    }

    public NovelDto getNovel() {
        return novel;
    }

    public void setNovel(NovelDto novel) {
        this.novel = novel;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}



