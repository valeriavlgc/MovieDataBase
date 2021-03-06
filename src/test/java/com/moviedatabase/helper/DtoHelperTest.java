package com.moviedatabase.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.moviedatabase.dto.FilmDto;
import com.moviedatabase.entity.Film;
import com.moviedatabase.entity.Tvshow;

//Cambiar anotaciones.

@SpringBootTest
public class DtoHelperTest {  
    
    @Test
    public void test_toEntity() {
    FilmDto filmDto = new FilmDto(550, "The ring", "Very scary horror movie", "2003-01-10", "en");
    Film film = new Film();
    
    film = DtoHelper.toEntity(filmDto);
   
    //assertEquals(film, helperDto.toEntity(filmDto));
    assertEquals(0, film.getFilmId());
    assertEquals(550, film.getTmdbId());  
    assertEquals("en", film.getOriginalLanguage());
    assertEquals("2003-01-10", film.getReleaseDate());
    assertEquals("The ring", film.getTitle());
    
    }
    
    @Test
    public void test_toPersonEntity() {
    	
    }
    
    @Test 
    public void test_toTvshowEntity() {
    //TvshowDto tvshowDto = new TvshowDto("2018-10-01", 34, "Very bad sitcom", "en", "blabla", 4.32);
    Tvshow tvshow = new Tvshow();
    
    //tvshow = helperDto.toTvshowEntity(tvshowDto);
   
    assertEquals("2018-10-01", tvshow.getFirstAirDate());
    assertEquals(34, tvshow.getTmdbId());  
    assertEquals("Very bad sitcom", tvshow.getName());
    assertEquals("en", tvshow.getOriginalLanguage());
    assertEquals("blabla", tvshow.getOverview());	
    assertEquals(4.32, tvshow.getPopularity());	
   
    }
       

}
