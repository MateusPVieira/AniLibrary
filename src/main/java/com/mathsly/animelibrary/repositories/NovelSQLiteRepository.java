package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.entities.Manga;
import com.mathsly.animelibrary.entities.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelSQLiteRepository extends JpaRepository<Novel, Long> {
}
