package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.domain.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarySQLiteRepository extends JpaRepository<Library, Long> {
}
