package com.moviedatabase.service;

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
import com.moviedatabase.dto.ResultPersonDto;
import com.moviedatabase.entity.Person;
import com.moviedatabase.enums.Profession;
import com.moviedatabase.helper.DtoHelper;
import com.moviedatabase.repository.IPersonDataBaseRepository;

import reactor.core.publisher.Mono;

@Service
public class PersonDataBaseService {

	@Autowired 
	IPersonDataBaseRepository personRepository;
		
    public Mono<ResponseEntity<ResultPersonDto>> getPerson(String personId)  {
		
		return WebClient.create()
						.get()
				      	.uri("http://localhost:8080/tmdb/2/" + personId)
				        .retrieve()
				        .onStatus(status -> status.equals(HttpStatus.INTERNAL_SERVER_ERROR),
	                     clientResponse -> Mono.error(new EntryNotFound("This entry doesn't exist in TMDB.")))
				        .toEntity(ResultPersonDto.class);
	}
    
    
	public Person addPerson(ResultPersonDto resultPersonDto) throws EntryAlreadyExists {

		if (!personRepository.existsByTmdbId(resultPersonDto.getId())) {	
			return personRepository.save(DtoHelper.toPersonEntity(resultPersonDto));
		} else {
			throw new EntryAlreadyExists("There's already an entry with that id.");
		}
        
    }
	
	
	public Page<Person> getPersonBy(String criteria, String value, int number, int size) {
		
	Page<Person> sortedPage = null;
	Pageable pageable = PageRequest.of(number, size);	 
		
		 if (criteria.equalsIgnoreCase("name")) {
			 sortedPage = personRepository.findByNameContainingIgnoreCase(value, pageable);
		 }  else if (criteria.equalsIgnoreCase("placeOfBirth")) {
			 sortedPage = personRepository.findByPlaceOfBirthContainingIgnoreCase(value, pageable);
		 }	   

	    return sortedPage;
	}	
	
	
	
	public Page<Person> getAllDirectors (int number, int size)  {
		Pageable pageable = PageRequest.of(number, size);	 
		
		return personRepository.findByProfession(Profession.DIRECTOR, pageable);
	}
	
	public Page<Person> getAllActors (int number, int size) {
        Pageable pageable = PageRequest.of(number, size);	 
		
		return personRepository.findByProfession(Profession.ACTOR, pageable);
	}
	
}
