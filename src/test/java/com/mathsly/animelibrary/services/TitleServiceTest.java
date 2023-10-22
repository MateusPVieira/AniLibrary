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

    Title testTitle;
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


        testTitle = new Title();
        testTitle.setId(1L);
        testTitle.setAuthor("Toryama");
    }

    @Nested
    @DisplayName("GetOne method")
    class GetOneMethodTest {
        @Test
        void ShouldFindTitleByIdWithSuccess() {
            when(repository.findById(testTitle.getId())).thenReturn(Optional.of(testTitle));

            Title titleReturned = service.getOne(testTitle.getId());

            assertEquals(Optional.of(testTitle), Optional.of(titleReturned));
            verify(repository).findById(testTitle.getId());
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
            when(repository.saveAndFlush(testTitle)).thenReturn(testTitle);

            long savedId = service.save(testTitle);

            assertEquals(testTitle.getId(), savedId);
            verify(repository).saveAndFlush(testTitle);
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
            when(repository.findById(testTitle.getId())).thenReturn(Optional.ofNullable(testTitle));

            service.addMediaToTitle(testTitle.getId(), anime);

            assertEquals(testTitle.getAnime(), anime);
            verify(repository).saveAndFlush(testTitle);
        }

        @Test
        void ShouldThrowExceptionWhenTitleDoNotExist() {
            assertThrows(IllegalArgumentException.class, () -> service.addMediaToTitle(noExistingId, anime));
        }


    }
}