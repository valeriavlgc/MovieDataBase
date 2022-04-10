package com.moviedatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.moviedatabase.enums.Profession;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Person {
     
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long personId;
	@Column(name = "profession")
	@Enumerated(EnumType.STRING) 
	private Profession profession;
    @Column(name = "biography", length = 65555)
    private String biography;
    @Column(name = "birthday")
    private String birthday;
    @Column (name = "gender")
    private Integer gender;
    @Column(name = "tmdbId")
    private Integer tmdbId;
    @Column(name = "name")
    private String name;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Column(name = "popularity")
    private Double popularity;
}
