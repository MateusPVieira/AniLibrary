package com.mathsly.animelibrary.controllers;

import com.mathsly.animelibrary.domain.dto.LibraryDto;
import com.mathsly.animelibrary.domain.entities.Library;
import com.mathsly.animelibrary.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/library")
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LibraryDto>> findAll() {
        List<Library> list = libraryService.getAll();
        List<LibraryDto> listDto = list.stream().map(x -> new LibraryDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LibraryDto> findById(@PathVariable Long id) {
        Library library = libraryService.getOne(id);
        return ResponseEntity.ok().body(new LibraryDto(library));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> insert(@RequestBody LibraryDto libraryDto) {
        Library library = libraryDto.toEntity();
        Long idLibrary = libraryService.save(library);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(library.getId()).toUri();
        return ResponseEntity.created(uri).body(idLibrary);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody LibraryDto libraryDto, @PathVariable Long id){
        Library library = libraryDto.toEntity();
        libraryService.update(library, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        libraryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
