package com.mathsly.animelibrary.repositories;

import com.mathsly.animelibrary.entities.Manga;
import com.mathsly.animelibrary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSQLiteRepository extends JpaRepository<User, Long> {
}
