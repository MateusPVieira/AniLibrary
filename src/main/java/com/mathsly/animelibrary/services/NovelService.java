package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Novel;
import com.mathsly.animelibrary.repositories.NovelSQLiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NovelService {
    NovelSQLiteRepository novelRepository;
    @Autowired
    public NovelService(NovelSQLiteRepository novelRepository){
        this.novelRepository = novelRepository;
    }

    public Novel getOne(Long id){
        return novelRepository.findById(id).orElseThrow(() -> new RuntimeException("Novel not found!"));
    }

    public List<Novel> getAll(){
        return novelRepository.findAll();
    }

    public long save(Novel novel){
        return novelRepository.saveAndFlush(novel).getId();
    }

    public Optional<Long> update(Novel novel, Long id){
        return novelRepository.findById(id).map(
                novelToUpdate ->
                {
                    novelRepository.flush();
                    novelToUpdate.setId(id);
                    novelToUpdate.setCurrentChapter(novel.getCurrentChapter());
                    novelToUpdate.setFinished(novel.isFinished());
                    novelToUpdate.setLastChapter(novel.getLastChapter());
                    return save(novelToUpdate);
                });
    }

    public void delete (Long id){
        novelRepository.deleteById(id);
    }



}
