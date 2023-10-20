package com.mathsly.animelibrary.controllers;

import com.mathsly.animelibrary.domain.dto.MangaDto;
import com.mathsly.animelibrary.domain.entities.Manga;
import com.mathsly.animelibrary.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/manga")
public class MangaController {

    private MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService){
        this.mangaService = mangaService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MangaDto>> findAll() {
        List<Manga> list = mangaService.getAll();
        List<MangaDto> listDto = list.stream().map(x -> new MangaDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MangaDto> findById(@PathVariable Long id) {
        Manga manga = mangaService.getOne(id);
        return ResponseEntity.ok().body(new MangaDto(manga));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> insert(@RequestBody MangaDto mangaDto) {
        Manga manga = mangaDto.toEntity();
        Long idManga = mangaService.save(manga);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(manga.getId()).toUri();
        return ResponseEntity.created(uri).body(idManga);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody MangaDto mangaDto, @PathVariable Long id){
        Manga manga = mangaDto.toEntity();
        mangaService.update(manga, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        mangaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
