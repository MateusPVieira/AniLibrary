package com.mathsly.animelibrary.controllers;

import com.mathsly.animelibrary.domain.dto.AnimeDto;
import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/anime")
public class AnimeController {

    private AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService){
        this.animeService = animeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AnimeDto>> findAll() {
        List<Anime> list = animeService.getAll();
        List<AnimeDto> listDto = list.stream().map(x -> new AnimeDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AnimeDto> findById(@PathVariable Long id) {
        Anime anime = animeService.getOne(id);
        return ResponseEntity.ok().body(new AnimeDto(anime));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> insert(@RequestBody AnimeDto animeDto) {
        Anime anime = animeDto.toEntity();
        Long idAnime = animeService.save(anime);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(anime.getId()).toUri();
        return ResponseEntity.created(uri).body(idAnime);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody AnimeDto animeDto, @PathVariable Long id){
        Anime anime = animeDto.toEntity();
        animeService.update(anime, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        animeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
