package com.moviedatabase.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.moviedatabase.dto.FilmDto;
import com.moviedatabase.entity.Film;

@SpringBootTest
public class UriHelperTest {
	
	@MockBean
	@Autowired
	UriHelper uriHelper;
	
	@Test
    public void test_getUri() {
    String type = "movies";
    String query = "the+avengers";
    String uri  = "https://api.themoviedb.org/3/search/movie?api_key=4b8b5f768a7f24f799ce8d13333b809f&query=the+avengers&";
             
    assertEquals(uri, uriHelper.getUri(type,query));
 
    }

}
