package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Anime;
import com.mathsly.animelibrary.domain.entities.Title;
import com.mathsly.animelibrary.repositories.TitleSQLiteRepository;
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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TitleServiceTest {

    @InjectMocks
    TitleService service;

    @Mock
    TitleSQLiteRepository repository;

    Title title;
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


        title = new Title();
        title.setId(1L);
        title.setAuthor("Toryama");
    }

    @Nested
    @DisplayName("GetOne method")
    class GetOneMethodTest {
        @Test
        void ShouldFindTitleByIdWithSuccess() {
            when(repository.findById(title.getId())).thenReturn(Optional.of(title));

            Title titleReturned = service.getOne(title.getId());

            assertEquals(Optional.of(title), Optional.of(titleReturned));
            verify(repository).findById(title.getId());
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
        void ShouldReturnBusinessExceptionIfFailToFindTitle() {
            final BusinessException e = assertThrows(BusinessException.class, () -> service.getOne(noExistingId));

            assertThat(e.getMessage(), is(format("Title with id = %s not found!", noExistingId)));
        }
    }


    @Nested
    @DisplayName("Save method")
    class SaveMethodTest {
        @Test
        void ShouldSaveTitle() {
            when(repository.saveAndFlush(title)).thenReturn(title);

            long savedId = service.save(title);

            assertEquals(title.getId(), savedId);
            verify(repository).saveAndFlush(title);
        }

        @Test
        void ShouldThrowExceptionWhenTitleIsNull() {
            assertThrows(IllegalArgumentException.class, () -> service.save(null));
            verifyNoInteractions(repository);
        }

    }


    @Nested
    @DisplayName("addMedia method")
    class AddMediaMethodTest {
        @Test
        void ShouldAddAnimeInTitle() {
            when(repository.findById(title.getId())).thenReturn(Optional.ofNullable(title));

            service.addMediaToTitle(title.getId(), anime);

            assertEquals(title.getAnime(), anime);
            verify(repository).saveAndFlush(title);
        }

        @Test
        void ShouldThrowExceptionWhenTitleDoNotExist() {
            when(repository.findById(noExistingId)).thenReturn(Optional.ofNullable(title));
            assertThrows(IllegalArgumentException.class, () -> service.addMediaToTitle(noExistingId, anime));
        }

    }
}