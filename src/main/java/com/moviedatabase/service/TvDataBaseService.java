package com.moviedatabase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviedatabase.controller.exceptions.EntryAlreadyExists;
import com.moviedatabase.controller.exceptions.EntryNotFound;
import com.moviedatabase.dto.FilmDto;
import com.moviedatabase.dto.TvshowDto;
import com.moviedatabase.entity.Person;
import com.moviedatabase.entity.Tvshow;
import com.moviedatabase.helper.DtoHelper;
import com.moviedatabase.repository.ITvRepository;

import reactor.core.publisher.Mono;

@Service
public class TvDataBaseService {
	
	@Autowired
	ITvRepository tvRepository;
	
   public Mono<ResponseEntity<TvshowDto>> getTvshow (String tvshowId)  {
		
		return WebClient.create()
						.get()
				      	.uri("http://localhost:8080/tmdb/3/" + tvshowId)
				        .retrieve()
				        .onStatus(status -> status.equals(HttpStatus.INTERNAL_SERVER_ERROR),
	                     clientResponse -> Mono.error(new EntryNotFound("This entry doesn't exist in TMDB.")))
				        .toEntity(TvshowDto.class);
	}
	
	public Tvshow addTvshow(TvshowDto tvshowDto) throws EntryAlreadyExists {
		
		if (!tvRepository.existsByTmdbId(tvshowDto.getId())) {	
			 return tvRepository.save(DtoHelper.toTvshowEntity(tvshowDto));
		} else 	{
			throw new EntryAlreadyExists("There's already an entry with that id.");
		}
		
	}
	
	
	public Page<Tvshow> getTvshowBy(String criteria, String value, int number, int size) {
		
	Page<Tvshow> sortedPage = null;
	Pageable pageable = PageRequest.of(number, size);	 
		
		 if (criteria.equalsIgnoreCase("name")) {
			 sortedPage = tvRepository.findByNameContainingIgnoreCase(value, pageable);
		 }  else if (criteria.equalsIgnoreCase("firstAirDate")) {
			 sortedPage = tvRepository.findByFirstAirDateContaining(value, pageable);
		 }  else if(criteria.equalsIgnoreCase("originalLanguage")) {
			 sortedPage = tvRepository.findByOriginalLanguageContainingIgnoreCase(value, pageable);
		 }  
		 
	    return sortedPage;
	}	
	
	
	public Page<Tvshow> getAllTvshows(int number, int size) {
    Pageable pageable = PageRequest.of(number, size);	
    
		return tvRepository.findAll(pageable);
	}
 }
