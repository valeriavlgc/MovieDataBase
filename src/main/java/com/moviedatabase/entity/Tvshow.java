package com.moviedatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;
//import org.hibernate.annotations.TypeDefs;
//
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//otra manera para no importar esta libreria externa?

//@TypeDefs({
//	
//	@TypeDef(name = "json", typeClass = JsonBinaryType.class)
//})


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Tvshow {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long tvShowId;
    @Column(name = "first_air_date")
    private String firstAirDate;
//    @Column(name = "genre_ids")
//    private List<Integer> genreIds = null;
    @Column(name = "tmdbId")
    private Integer tmdbId;
    @Column(name = "name")
    private String name;
//    @Column(name = "origin_country", columnDefinition = "json")
//    @Type(type = "json")
//    private List<String> originCountry = null;
    @Column(name = "original_language")
    private String originalLanguage;
    @Column(name = "overview", length = 65555)
    private String overview;
    @Column(name = "popularity")
    private Double popularity;
	
}
