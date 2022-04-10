package com.moviedatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Film {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long filmId;
	@Column(name = "tmdbId")
	private int tmdbId;
	@Column(name = "title")
	private String title;
	@Column(name = "overview", length = 65555)
	private String overview;
	@Column(name = "release_date")
	private String releaseDate;
	@Column(name = "original_language")
	private String originalLanguage;
	
	
}
