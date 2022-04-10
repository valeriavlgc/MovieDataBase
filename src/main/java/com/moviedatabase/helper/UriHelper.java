package com.moviedatabase.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UriHelper {
	
	@Value("${api.key}")
	private String apiKey;
	
	public String getUri(String type, String query) {
	String uri = null;

	
		if (type.equalsIgnoreCase("movies")) {
			uri = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + query +"&";
		} else if (type.equalsIgnoreCase("people")) {
			uri = "http://api.themoviedb.org/3/search/person?api_key=" + apiKey + "&language=en-US&query=" + query +"&";
		} else if (type.equalsIgnoreCase("tvshows")) {
			uri = "https://api.themoviedb.org/3/search/tv?api_key=" + apiKey + "&language=en-US&query=" + query +"&" ;
	    }
		
		return uri;
	}

}
