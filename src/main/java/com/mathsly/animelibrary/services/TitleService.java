package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.domain.entities.Manga;
import com.mathsly.animelibrary.domain.entities.Novel;
import com.mathsly.animelibrary.domain.entities.Title;
import com.mathsly.animelibrary.repositories.TitleSQLiteRepository;
import com.mathsly.animelibrary.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class TitleService {
    TitleSQLiteRepository titleRepository;
    @Autowired
    public TitleService(TitleSQLiteRepository titleRepository){
        this.titleRepository = titleRepository;
    }

    public Title getOne(Long id){
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        return titleRepository.findById(id).orElseThrow(() -> new BusinessException(format("Title with id = %s not found!", id)));


    }
    public List<Title> getAll(){ return titleRepository.findAll(); }

    public long save(Title title){
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null");
        }

        return titleRepository.saveAndFlush(title).getId();
    }


    public Optional<Long> update(Title title, Long id){
        title.setId(id);
        return titleRepository.findById(id).map(
                titleToUpdate ->
                {
                    titleRepository.flush();
                    titleToUpdate.setId(id);
                    titleToUpdate.setAnime(title.getAnime());
                    titleToUpdate.setManga(title.getManga());
                    titleToUpdate.setNovel(title.getNovel());
                    titleToUpdate.setAuthor(title.getAuthor());

                    return save(titleToUpdate);
                });
    }

    public void delete (Long id){
        titleRepository.deleteById(id);
    }


    public <T> void addMediaToTitle(Long id, T media) {
        Optional<Title> title = titleRepository.findById(id);
        if (title.isPresent()) {
            if (media instanceof Anime) {
                title.get().setAnime((Anime) media);

            } else if (media instanceof Manga) {
                title.get().setManga((Manga) media);

            } else if (media instanceof Novel) {
                title.get().setNovel((Novel) media);
            }

            titleRepository.saveAndFlush(title.get());
        }

        throw new IllegalArgumentException("Title must not be null");
    }

}
