package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.domain.entities.Manga;
import com.mathsly.animelibrary.repositories.MangaSQLiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MangaService {
    MangaSQLiteRepository mangaRepository;
    @Autowired
    public MangaService(MangaSQLiteRepository mangaRepository){
        this.mangaRepository = mangaRepository;
    }

    public Manga getOne(Long id){
        return mangaRepository.findById(id).orElseThrow(() -> new RuntimeException("Manga not found!"));
    }

    public List<Manga> getAll(){
        return mangaRepository.findAll();
    }

    public long save(Manga manga){
        return mangaRepository.saveAndFlush(manga).getId();
    }

    public Optional<Long> update(Manga manga, Long id){
        manga.setId(id);
        return mangaRepository.findById(id).map(
                mangaToUpdate ->
                {
                    mangaRepository.flush();
                    mangaToUpdate.setId(id);
                    mangaToUpdate.setCurrentChapter(manga.getCurrentChapter());
                    mangaToUpdate.setFinished(manga.isFinished());
                    mangaToUpdate.setLastChapter(manga.getLastChapter());
                    mangaToUpdate.setUpToDate(verifyIfItIsUpToDate(manga));
                    return save(mangaToUpdate);
                });
    }

    public void delete (Long id){
        mangaRepository.deleteById(id);
    }

    public boolean verifyIfItIsUpToDate(Manga manga) {
        return manga.getCurrentChapter() == manga.getLastChapter();
    }

}
