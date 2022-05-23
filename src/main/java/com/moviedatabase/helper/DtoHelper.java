package com.moviedatabase.helper;

import org.springframework.beans.BeanUtils;
import com.moviedatabase.dto.FilmDto;
import com.moviedatabase.dto.ResultPersonDto;
import com.moviedatabase.dto.TvshowDto;
import com.moviedatabase.entity.Film;
import com.moviedatabase.entity.Person;
import com.moviedatabase.entity.Tvshow;
import com.moviedatabase.enums.Profession;

public class DtoHelper {
	
	public static Film toEntity(FilmDto filmDto) {
	Film film = new Film();
	
	BeanUtils.copyProperties(filmDto, film);
	film.setTmdbId(filmDto.getId());
	   
	   return film;
	}
	
	public static Person toPersonEntity(ResultPersonDto resultPersonDto) {
	Person person = new Person();
	
	BeanUtils.copyProperties(resultPersonDto, person);
	person.setTmdbId(resultPersonDto.getId());
	
		if (resultPersonDto.getKnownForDepartment().equalsIgnoreCase("Acting")) {
			person.setProfession(Profession.ACTOR);
		} else if (resultPersonDto.getKnownForDepartment().equalsIgnoreCase("Directing")) {
			person.setProfession(Profession.DIRECTOR);
		}
	   
		return person;
	}
	
	public static Tvshow toTvshowEntity(TvshowDto tvshowDto) {
	Tvshow tvshow = new Tvshow();
	
	BeanUtils.copyProperties(tvshowDto, tvshow);
    tvshow.setTmdbId(tvshowDto.getId());
    
		return tvshow;
	}
	
}
