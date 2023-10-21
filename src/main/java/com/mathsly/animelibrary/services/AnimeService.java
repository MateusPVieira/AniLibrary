package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.repositories.AnimeSQLiteRepository;
import com.mathsly.animelibrary.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class AnimeService {
    AnimeSQLiteRepository animeRepository;
    @Autowired
    public AnimeService(AnimeSQLiteRepository animeRepository){
        this.animeRepository = animeRepository;
    }

    public Anime getOne(Long id){
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        return animeRepository.findById(id).orElseThrow(() -> new BusinessException(format("Anime with id = %s not found!", id)));
    }

    public List<Anime> getAll(){
        return animeRepository.findAll();
    }

    public long save(Anime anime){
        if (anime == null) {
            throw new IllegalArgumentException("Anime must not be null");
        }
        return animeRepository.saveAndFlush(anime).getId();
    }

    public Optional<Long> update(Anime anime, Long id){
        anime.setId(id);
        return animeRepository.findById(id).map(
                animeToUpdate ->
                {
                    animeRepository.flush();
                    animeToUpdate.setId(id);
                    animeToUpdate.setCurrentChapter(anime.getCurrentChapter());
                    animeToUpdate.setFinished(anime.isFinished());
                    animeToUpdate.setLastChapter(anime.getLastChapter());
                    return save(animeToUpdate);
                });
    }

    public void delete (Long id){
        animeRepository.deleteById(id);
    }



}
