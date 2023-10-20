package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.entities.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaSQLiteRepository extends JpaRepository<Manga, Long> {
}
