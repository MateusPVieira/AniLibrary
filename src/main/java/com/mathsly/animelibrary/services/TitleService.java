package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Novel;
import com.mathsly.animelibrary.domain.entities.Title;
import com.mathsly.animelibrary.repositories.TitleSQLiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {
    TitleSQLiteRepository titleRepository;
    @Autowired
    public TitleService(TitleSQLiteRepository titleRepository){
        this.titleRepository = titleRepository;
    }

    public Title getOne(Long id){
        return titleRepository.findById(id).orElseThrow(() -> new RuntimeException("Title not found!"));


    }
    public List<Title> getAll(){ return titleRepository.findAll(); }

    public long save(Title title){
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

}
