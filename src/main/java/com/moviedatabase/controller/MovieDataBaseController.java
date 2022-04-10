package com.moviedatabase.controller;

//This controller class operates on the created database to store movies, tv shows and persons.

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviedatabase.controller.exceptions.EntryNotFound;
import com.moviedatabase.controller.exceptions.WrongTypeException;
import com.moviedatabase.dto.FilmDto;
import com.moviedatabase.dto.PageDto;
import com.moviedatabase.dto.ResultPersonDto;
import com.moviedatabase.dto.TvshowDto;
import com.moviedatabase.entity.Film;
import com.moviedatabase.entity.Person;
import com.moviedatabase.entity.Tvshow;
import com.moviedatabase.service.MovieDataBaseService;
import com.moviedatabase.service.PersonDataBaseService;
import com.moviedatabase.service.TvDataBaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/cinemaDb/")
@CrossOrigin(origins = "*")
public class MovieDataBaseController {
	
	@Autowired
	MovieDataBaseService movieService;
	
	@Autowired 
	PersonDataBaseService personService;
	
	@Autowired 
	TvDataBaseService tvService;
		
	WebClient webClient = WebClient.create();
		
	//Search types (film, tvshow, people) by different criteria in my database.
	
	//Criteria: title/releaseDate/originalLanguage
	@GetMapping("/getFilm")
	@ApiOperation("Get films by criteria")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"),
		            @ApiResponse(code = 400, message = "Bad Request")})
	public ResponseEntity<?> getFilmByCriteria(@RequestParam("criteria") String criteria, 
			                                   @RequestParam("value") String value,
			                                   @ApiParam(value = "number", name= "number")
			                                   @RequestParam(required = false) String number,
			                                   @ApiParam(value = "size", name= "size")
			                                   @RequestParam(required = false) String size){
				
		try {
			Page<Film> sortedPage = movieService.getFilmBy(criteria, value, getValidNumber(number),getValidSize(size));
			if(sortedPage.isEmpty()) {
				return new ResponseEntity<>("There's no coincidences", HttpStatus.OK);				
			} else {
			return new ResponseEntity<Page<Film>>(sortedPage, HttpStatus.OK); 
			}
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		
    }
	
	//Criteria: name/firstAirDate/originalLanguage
	@GetMapping("/getTvshow")
	@ApiOperation("Get tv shows by criteria")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad Request")})
	public ResponseEntity<?> getTvshowByCriteria(@RequestParam("criteria") String criteria, 
			                                     @RequestParam("value") String value,
			                                     @ApiParam(value = "number", name= "number")
			                                     @RequestParam(required = false) String number,
			                                     @ApiParam(value = "size", name= "size")
				                                 @RequestParam(required = false) String size
			                                    ){
		
		try {
			Page<Tvshow> sortedPage = tvService.getTvshowBy(criteria, value, getValidNumber(number),getValidSize(size));
			if(sortedPage.isEmpty()) {
				return new ResponseEntity<>("There's no coincidences", HttpStatus.OK);				
			} else {
			return new ResponseEntity<Page<Tvshow>>(sortedPage, HttpStatus.OK); 
			}
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		
    }
	

	//Criteria: name / placeOfBirth
	@GetMapping("/getPerson")
	@ApiOperation("Get people by criteria")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"),
    				@ApiResponse(code = 400, message = "Bad Request")})
	public ResponseEntity<?> getPersonByCriteria(@RequestParam("criteria") String criteria, 
			                                     @RequestParam("value") String value,
			                                     @ApiParam(value = "number", name= "number")
			                                     @RequestParam(required = false) String number,
			                                     @ApiParam(value = "size", name= "size")
				                                 @RequestParam(required = false) String size
			                                    ){
		
		try {
			Page<Person> sortedPage = personService.getPersonBy(criteria, value, getValidNumber(number),getValidSize(size));
			if(sortedPage.isEmpty()) {
				return new ResponseEntity<>("There's no coincidences", HttpStatus.OK);				
			} else {
			return new ResponseEntity<Page<Person>>(sortedPage, HttpStatus.OK); 
			}
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		
    }
	
	
	//General search by query in any type (types: /movies/, /people/, /tvshows/)
	@GetMapping("/search")
	public Mono<List<?>> search(@RequestParam("type") String type, @RequestParam("query") String query) {
			
		return webClient.get()
				      	.uri("http://localhost:8080/tmdb/5/" + type + "/" + query)
				        .retrieve()	
				        .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, response -> Mono.error(new WrongTypeException("You've entered a wrong type. Accepted types are: movies/tvshows/people.")))
				        .bodyToMono(PageDto.class)
				    	.flatMap(response -> Flux.fromIterable(response.getResults()).collectList());
	               
	} 
			
	//Post methods to save different entities.
	
	@PostMapping("/saveFilm")
	public Mono<?> saveFilm(@RequestParam("movieId") String movieId) {    
	
	Mono<ResponseEntity<FilmDto>> filmDto = movieService.getFilm(movieId);	
		  
		return filmDto.map(response -> movieService.addFilm(response.getBody()));
		                
	}

	
	@PostMapping("/savePerson")
	public Mono<?> savePerson(@RequestParam("personId") String personId) {    
	
		Mono<ResponseEntity<ResultPersonDto>> personDto = personService.getPerson(personId);	
		  
		return personDto.map(response -> personService.addPerson(response.getBody()));
			       
    }

	@PostMapping("/saveTvshow") 
	public Mono<?> saveTvshow(@RequestParam("tvshowId") String tvshowId) {
		
		Mono<ResponseEntity<TvshowDto>> tvshowDto = tvService.getTvshow(tvshowId);	
		  
		return tvshowDto.map(response -> tvService.addTvshow(response.getBody()));
	
	}

	
    //Additional endpoints to get all entries of a type. 	
	@GetMapping("/getAllActors")
	public ResponseEntity<?> getAllActors(@ApiParam(value = "number", name= "number")
    									  @RequestParam(required = false) String number,
										  @ApiParam(value = "size", name= "size")
										  @RequestParam(required = false) String size) {
 
		return new ResponseEntity<Page<Person>>(personService.getAllActors(getValidNumber(number),getValidSize(size)), HttpStatus.OK);
	}
	
	@GetMapping("/getAllDirectors")
	public ResponseEntity<?> getAllDirectors(@ApiParam(value = "number", name= "number")
									         @RequestParam(required = false) String number,
									         @ApiParam(value = "size", name= "size")
									         @RequestParam(required = false) String size) {

		return new ResponseEntity<Page<Person>>(personService.getAllDirectors(getValidNumber(number),getValidSize(size)), HttpStatus.OK);
	}
	
	@GetMapping("/getAllTvshows")
	public ResponseEntity<?> getAllTvshows(@ApiParam(value = "number", name= "number")
										   @RequestParam(required = false) String number,
										   @ApiParam(value = "size", name= "size")
										   @RequestParam(required = false) String size) {
		
		return new ResponseEntity<Page<Tvshow>>(tvService.getAllTvshows(getValidNumber(number),getValidSize(size)), HttpStatus.OK);
	}
	
	@GetMapping("/getAllFilms")
	public ResponseEntity<?> getAllFilms(@ApiParam(value = "number", name= "number")
										 @RequestParam(required = false) String number,
										 @ApiParam(value = "size", name= "size")
										 @RequestParam(required = false) String size) {

		return new ResponseEntity<Page<Film>>(movieService.getAllFilms(getValidNumber(number), getValidSize(size)), HttpStatus.OK);
	}
	
	
	
	int getValidNumber(String number) {
	    	
		if (number == null || number.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(number); 
		} 			
	}
 
	
	int getValidSize(String size) {
    	
		if (size == null || size.isEmpty()) {
			return 1;
		} else {
			return Integer.parseInt(size); 
		} 			
	}
 
}
