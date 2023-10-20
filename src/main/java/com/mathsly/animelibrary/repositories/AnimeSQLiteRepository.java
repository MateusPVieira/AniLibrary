package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.domain.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeSQLiteRepository extends JpaRepository<Anime, Long> {
}
