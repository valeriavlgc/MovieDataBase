
package com.moviedatabase.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "adult",
    "also_known_as",
    "biography",
    "birthday",
    "deathday",
    "gender",
    "homepage",
    "id",
    "imdb_id",
    "known_for_department",
    "name",
    "place_of_birth",
    "popularity",
    "profile_path"
})
@Generated("jsonschema2pojo")
public class ResultPersonDto {

    @JsonProperty("adult")
    private Boolean adult;
    @JsonProperty("also_known_as")
    private List<Object> alsoKnownAs = null;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("deathday")
    private Object deathday;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("homepage")
    private Object homepage;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    @JsonProperty("name")
    private String name;
    @JsonProperty("place_of_birth")
    private String placeOfBirth;
    @JsonProperty("popularity")
    private Double popularity;
    @JsonProperty("profile_path")
    private String profilePath;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("adult")
    public Boolean getAdult() {
        return adult;
    }

    @JsonProperty("adult")
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    @JsonProperty("also_known_as")
    public List<Object> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    @JsonProperty("also_known_as")
    public void setAlsoKnownAs(List<Object> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    @JsonProperty("biography")
    public String getBiography() {
        return biography;
    }

    @JsonProperty("biography")
    public void setBiography(String biography) {
        this.biography = biography;
    }

    @JsonProperty("birthday")
    public String getBirthday() {
        return birthday;
    }

    @JsonProperty("birthday")
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @JsonProperty("deathday")
    public Object getDeathday() {
        return deathday;
    }

    @JsonProperty("deathday")
    public void setDeathday(Object deathday) {
        this.deathday = deathday;
    }

    @JsonProperty("gender")
    public Integer getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @JsonProperty("homepage")
    public Object getHomepage() {
        return homepage;
    }

    @JsonProperty("homepage")
    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("imdb_id")
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty("imdb_id")
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @JsonProperty("known_for_department")
    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    @JsonProperty("known_for_department")
    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("place_of_birth")
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    @JsonProperty("place_of_birth")
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @JsonProperty("popularity")
    public Double getPopularity() {
        return popularity;
    }

    @JsonProperty("popularity")
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    @JsonProperty("profile_path")
    public String getProfilePath() {
        return profilePath;
    }

    @JsonProperty("profile_path")
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
