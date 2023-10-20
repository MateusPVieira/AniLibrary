package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.domain.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleSQLiteRepository extends JpaRepository<Title, Long> {
}
