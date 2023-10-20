package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.domain.entities.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelSQLiteRepository extends JpaRepository<Novel, Long> {
}
