package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.repositories.AnimeSQLiteRepository;
import com.mathsly.animelibrary.utils.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimeServiceTest {

    @InjectMocks
    AnimeService service;

    @Mock
    AnimeSQLiteRepository repository;

    Anime anime;

    Long noExistingId;

    @BeforeEach
    public void setUp(){
        anime = new Anime();
        anime.setId(1L);
        anime.setCurrentChapter(200);
        anime.setLastChapter(201);
        anime.setFinished(false);
        anime.setUpToDate(false);

        noExistingId = 2L;
    }

    @Nested
    @DisplayName("GetOne method")
    class GetOneMethodTest {
        @Test
        void ShouldFindAnimeByIdWithSuccess() {
            when(repository.findById(anime.getId())).thenReturn(Optional.of(anime));

            Anime animeReturned = service.getOne(anime.getId());

            assertEquals(Optional.of(anime), Optional.of(animeReturned));
            verify(repository).findById(anime.getId());
            verifyNoMoreInteractions(repository);
        }

        @Test
        void ShouldNotCallRepositoryIfIdParameterIsNull() {
            final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> service.getOne(null));

            assertThat(e, notNullValue());
            assertThat(e.getMessage(), is("The given id must not be null"));
            verifyNoInteractions(repository);
        }

        @Test
        void ShouldReturnBusinessExceptionIfFailToFindAnime() {
            final BusinessException e = assertThrows(BusinessException.class, () -> service.getOne(noExistingId));

            assertThat(e.getMessage(), is(format("Anime with id = %s not found!", noExistingId)));
        }
    }


    @Nested
    @DisplayName("Save method")
    class SaveMethodTest {
        @Test
        void ShouldSaveAnime() {
            when(repository.saveAndFlush(anime)).thenReturn(anime);

            long savedId = service.save(anime);

            assertEquals(anime.getId(), savedId);
            verify(repository).saveAndFlush(anime);
        }

        @Test
        void ShouldThrowExceptionWhenAnimeIsNull() {
            assertThrows(IllegalArgumentException.class, () -> service.save(null));
            verifyNoInteractions(repository);
        }

        @Test
        void ShouldReturnBusinessExceptionIfFailToFindAnime() {
            final BusinessException e = assertThrows(BusinessException.class, () -> service.getOne(noExistingId));

            assertThat(e.getMessage(), is(format("Anime with id = %s not found!", noExistingId)));
        }
    }
}