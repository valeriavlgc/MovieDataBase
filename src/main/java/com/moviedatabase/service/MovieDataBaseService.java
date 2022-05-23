package com.moviedatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviedatabase.controller.exceptions.EntryAlreadyExists;
import com.moviedatabase.controller.exceptions.EntryNotFound;
import com.moviedatabase.dto.FilmDto;
import com.moviedatabase.entity.Film;
import com.moviedatabase.helper.DtoHelper;
import com.moviedatabase.repository.IMovieDataBaseRepository;

import reactor.core.publisher.Mono;

@Service
public class MovieDataBaseService {
	
	@Autowired
	IMovieDataBaseRepository movieRepository;
	
	public Mono<ResponseEntity<FilmDto>> getFilm (String movieId)  {
		
		return WebClient.create()
						.get()
				      	.uri("http://localhost:8080/tmdb/1/" + movieId)
				        .retrieve()
				        .onStatus(status -> status.equals(HttpStatus.INTERNAL_SERVER_ERROR),
	                     clientResponse -> Mono.error(new EntryNotFound("This entry doesn't exist in TMDB.")))
				        .toEntity(FilmDto.class);
	}
	
	public Film addFilm(FilmDto filmDto) throws EntryAlreadyExists {
		
		if (!movieRepository.existsByTmdbId(filmDto.getId())) {	
			return movieRepository.save(DtoHelper.toEntity(filmDto));
		} else {
			throw new EntryAlreadyExists("There's already an entry with that id.");
		} 
	          
    }
	
	public Page<Film> getFilmBy(String criteria, String value, int number, int size) {
	
	Page<Film> sortedPage = null;
	Pageable pageable = PageRequest.of(number, size);	 
		
		 if (criteria.equalsIgnoreCase("title")) {
			 sortedPage = movieRepository.findByTitleContainingIgnoreCase(value, pageable);
		 }  else if (criteria.equalsIgnoreCase("releaseDate")) {
			 sortedPage = movieRepository.findByReleaseDateContaining(value, pageable);
		 }  else if(criteria.equalsIgnoreCase("originalLanguage")) {
			 sortedPage = movieRepository.findByOriginalLanguageContainingIgnoreCase(value, pageable);
		 }  	   

	    return sortedPage;
	}	
	

	public Page<Film> getAllFilms(int number, int size) {
	Pageable pageable = PageRequest.of(number, size);	 
	
		return movieRepository.findAll(pageable);
	}

}
