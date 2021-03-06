 package com.moviedatabase.controller;

import com.moviedatabase.dto.FilmDto;
import com.moviedatabase.dto.PageDto;
import com.moviedatabase.dto.ResultPersonDto;
import com.moviedatabase.dto.TvshowDto;
import com.moviedatabase.helper.UriHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//This controller class makes call to the external API from the movie database.

@RestController
@RequestMapping(value = "/tmdb/")
public class TmdbController {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired 
	UriHelper uriHelper;
	
	@GetMapping("/1/{movieId}")
	public Mono<ResponseEntity<FilmDto>> getFilm(@PathVariable("movieId") String movieId) {
	
    return WebClient.create()
		            .get()
		            .uri("https://api.themoviedb.org/3/movie/" + movieId +"?api_key=" + apiKey + "&language=en-US")
		            .retrieve()
		            .toEntity(FilmDto.class);
 	}	
	
	@GetMapping("/2/{personId}")
	public Mono<ResponseEntity<ResultPersonDto>> getPerson(@PathVariable("personId") String personId) {
	    
		return WebClient.create()
	            .get()
	            .uri("https://api.themoviedb.org/3/person/" + personId +"?api_key=" + apiKey + "&language=en-US")
	            .retrieve()
	            .toEntity(ResultPersonDto.class);	            	
	}
   
	@GetMapping("/3/{tvshowId}")
	public Mono<ResponseEntity<TvshowDto>> getTvshow(@PathVariable("tvshowId") String tvshowId) {
		
		return WebClient.create()
	            .get()
	            .uri("https://api.themoviedb.org/3/tv/" + tvshowId + "?api_key=" + apiKey + "&language=en-US")
	            .retrieve()
	            .toEntity(TvshowDto.class);	            
		
	}
	
	
	//Method to perform searches on tmdb of any type with a given general query. 
	@GetMapping("/5/{type}/{query}")
    Mono<PageDto> getData(@PathVariable("type") String type, @PathVariable("query") String query) {
	String uri = uriHelper.getUri(type, query);
	
			return WebClient.create()
			        .get()
		            .uri(uri)
		            .retrieve()
		            .bodyToMono(PageDto.class);
	
			
   }
	
}
