package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.entities.Anime;
import com.mathsly.animelibrary.entities.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeSQLiteRepository extends JpaRepository<Anime, Long> {
}
