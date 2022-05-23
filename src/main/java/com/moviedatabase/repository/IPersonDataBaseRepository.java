package com.moviedatabase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviedatabase.entity.Person;
import com.moviedatabase.enums.Profession;

@Repository
public interface IPersonDataBaseRepository extends JpaRepository<Person, Long> {

	Boolean existsByTmdbId(int tmdb_id);
	Page<Person> findByProfession(Profession profession, Pageable pageable);
	Page<Person> findByNameContainingIgnoreCase(String value, Pageable pageable);
	Page<Person> findByPlaceOfBirthContainingIgnoreCase(String value, Pageable pageable);
 	
}
