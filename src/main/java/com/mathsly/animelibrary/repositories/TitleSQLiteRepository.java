package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.entities.Manga;
import com.mathsly.animelibrary.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleSQLiteRepository extends JpaRepository<Title, Long> {
}
