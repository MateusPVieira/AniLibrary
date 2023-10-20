package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.entities.Library;
import com.mathsly.animelibrary.repositories.LibrarySQLiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    LibrarySQLiteRepository libraryRepository;
    @Autowired
    public LibraryService(LibrarySQLiteRepository libraryRepository){
        this.libraryRepository = libraryRepository;
    }

    public Library getOne(Long id){
        return libraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Library not found!"));
    }

    public List<Library> getAll(){
        return libraryRepository.findAll();
    }

    public long save(Library library){
        return libraryRepository.saveAndFlush(library).getId();
    }

    public Optional<Long> update(Library library, Long id){
        return libraryRepository.findById(id).map(
                libraryToUpdate ->
                {
                    libraryRepository.flush();
                    libraryToUpdate.setId(id);
                    libraryToUpdate.setTitles(library.getTitles());
                    return save(library);
                });
    }

    public void delete (Long id){
        libraryRepository.deleteById(id);
    }



}
