package com.mathsly.animelibrary.controllers;

import com.mathsly.animelibrary.domain.dto.AnimeDto;
import com.mathsly.animelibrary.domain.dto.MangaDto;
import com.mathsly.animelibrary.domain.dto.NovelDto;
import com.mathsly.animelibrary.domain.dto.TitleDto;
import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.domain.entities.Manga;
import com.mathsly.animelibrary.domain.entities.Novel;
import com.mathsly.animelibrary.domain.entities.Title;
import com.mathsly.animelibrary.services.TitleService;
import com.mathsly.animelibrary.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/title")
public class imorzoTitleController {
    private final TitleService titleService;
    private final UploadService uploadService;

    @Autowired
    public TitleController(TitleService titleService, UploadService uploadService) {
        this.titleService = titleService;
        this.uploadService = uploadService;
    }

    @GetMapping
    public ResponseEntity<List<TitleDto>> getAllTitles() {
        List<Title> titles = titleService.getAll();
        List<TitleDto> titleDtos = titles.stream().map(TitleDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(titleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleDto> getTitleById(@PathVariable Long id) {
        Title title = titleService.getOne(id);
        return ResponseEntity.ok().body(new TitleDto(title));
    }

    @PostMapping
    public ResponseEntity<Long> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleDto.toEntity();
        Long idTitle = titleService.save(title);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(title.getId()).toUri();
        return ResponseEntity.created(uri).body(idTitle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTitle(@RequestBody TitleDto titleDto, @PathVariable Long id) {
        Title title = titleDto.toEntity();
        titleService.update(title, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/media/anime")
    public ResponseEntity<Void> addAnimeToTitle(@RequestBody AnimeDto animeDto, @PathVariable Long id) {
        Anime anime = animeDto.toEntity();
        titleService.addMediaToTitle(id, anime);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/media/manga")
    public ResponseEntity<Void> addMangaToTitle(@RequestBody MangaDto mangaDto, @PathVariable Long id) {
        Manga manga = mangaDto.toEntity();
        titleService.addMediaToTitle(id, manga);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/media/novel")
    public ResponseEntity<Void> addNovelToTitle(@RequestBody NovelDto novelDto, @PathVariable Long id) {
        Novel novel = novelDto.toEntity();
        titleService.addMediaToTitle(id, novel);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long id) {
        titleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/uploadCover")
    public ResponseEntity<Long> uploadCoverImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            Title title = titleService.getOne(id);
            String path = uploadService.uploadCover(file, title.getName());
            title.setCoverPath(path);
            titleService.update(title, id);
            return ResponseEntity.ok().body(id);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
