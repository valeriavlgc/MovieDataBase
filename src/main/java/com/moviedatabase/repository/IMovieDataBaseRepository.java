package com.moviedatabase.repository;

import org.springframework.stereotype.Repository;

import com.moviedatabase.entity.Film;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IMovieDataBaseRepository extends JpaRepository<Film, Long>  {
	
	Boolean existsByTmdbId(int tmdb_id);
	Page<Film> findByTitleContainingIgnoreCase(String value, Pageable pageable);
 	Page<Film> findByReleaseDateContaining(String value, Pageable pageable);
	Page<Film> findByOriginalLanguageContainingIgnoreCase(String value, Pageable pageable);
}
