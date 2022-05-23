package com.moviedatabase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviedatabase.entity.Tvshow;

@Repository
public interface ITvRepository extends JpaRepository<Tvshow, Long> {
	
	Boolean existsByTmdbId(int tmdb_id);
	
	Page<Tvshow> findByNameContainingIgnoreCase(String value, Pageable pageable);
 	Page<Tvshow> findByFirstAirDateContaining(String value, Pageable pageable);
	Page<Tvshow> findByOriginalLanguageContainingIgnoreCase(String value, Pageable pageable);
}
