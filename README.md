# My Movie Data Base

The objective of this API is to perform different actions in a created database with three tables, that store films, tv shows and people (actors and directors). The information is obtainted from an external API, TMDB [The Movie DataBase] (https://developers.themoviedb.org/3/getting-started/introduction).

FRONT?

This project is developed using SpringBoot and gradle as the dependency manager.
The database starts with an import of 8 mock entries to test all methods without the need of saving them before. 

## Security

This API is secured with JWT security. 

## General especifications

Port 





## Controllers

There are three controllers:

###### MovieDataBase controller

Three methods to filter data in my created database based on different criteria with a given value. 
```
GET film (criteria: title/releaseDate/originalLanguage)
GET tvshow (criteria: name/firstAirDate/originalLanguage)
GET person (criteria: name/placeOfBirth)
```
A method to search on TMDB API (trough tmdb controller) with a given type (movies/tvshows/people) and query.
```
GET search 
```
Three methods to save entities in repository.
```
POST saveFilm
POST saveTvshow
POST savePerson
```
Four additional endpoints to get a list from my database of all different entities.
```
GET AllActors
GET AllDirectors
GET AllTvshows
GET Allmovies
```
###### TMDB controller 

4 endpoints to obtain data from the external API (tmdb API).
```
GET movie
GET person
GET tvshow
GET data (search with a query)
```

###### Authetication controller

One POST endpoint to login and generate a token if credentials are correct. 
There's two users one with admin rights, the other with user rights.
```
Username : user 
Password : user

Username : admin
Usernmae : admin
```

## Status Codes

| Status Code | 200 | 400 | 401 | 500 |
| :---: | :---: | :---: | :---: | :---: | 
| Description | `OK` | `BAD REQUEST` | `UNAUTHORIZED` | `INTERNAL SERVER ERROR` | 

## Errors

There three designed errors are the following: 
```

```

