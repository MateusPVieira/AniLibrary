package com.mathsly.animelibrary.controllers;

import com.mathsly.animelibrary.domain.dto.NovelDto;
import com.mathsly.animelibrary.domain.entities.Novel;
import com.mathsly.animelibrary.services.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/novel")
public class NovelController {

    private NovelService novelService;

    @Autowired
    public NovelController(NovelService novelService){
        this.novelService = novelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NovelDto>> findAll() {
        List<Novel> list = novelService.getAll();
        List<NovelDto> listDto = list.stream().map(x -> new NovelDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<NovelDto> findById(@PathVariable Long id) {
        Novel novel = novelService.getOne(id);
        return ResponseEntity.ok().body(new NovelDto(novel));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> insert(@RequestBody NovelDto novelDto) {
        Novel novel = novelDto.toEntity();
        Long idNovel = novelService.save(novel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novel.getId()).toUri();
        return ResponseEntity.created(uri).body(idNovel);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody NovelDto novelDto, @PathVariable Long id){
        Novel novel = novelDto.toEntity();
        novelService.update(novel, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        novelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
