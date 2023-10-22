package com.mathsly.animelibrary.controllers;

import com.mathsly.animelibrary.domain.dto.AnimeDto;
import com.mathsly.animelibrary.domain.dto.TitleDto;
import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.domain.entities.Title;
import com.mathsly.animelibrary.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/title")
public class TitleController {

    private final TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService){
        this.titleService = titleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TitleDto>> findAll() {
        List<Title> list = titleService.getAll();
        List<TitleDto> listDto = list.stream().map(x -> new TitleDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TitleDto> findById(@PathVariable Long id) {
        Title title = titleService.getOne(id);
        return ResponseEntity.ok().body(new TitleDto(title));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> insert(@RequestBody TitleDto titleDto) {
        Title title = titleDto.toEntity();
        Long idTitle = titleService.save(title);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(title.getId()).toUri();
        return ResponseEntity.created(uri).body(idTitle);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody TitleDto titleDto, @PathVariable Long id){
        Title title = titleDto.toEntity();
        titleService.update(title, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody AnimeDto animeDto, @PathVariable Long id){
        Anime anime = animeDto.toEntity();
        titleService.addMediaToTitle(id, anime);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        titleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
