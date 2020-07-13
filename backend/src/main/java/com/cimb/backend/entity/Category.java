package com.cimb.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="categories_movies", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name="movie_id"))
	@JsonIgnore
	private List<Movie> movies;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Movie> getMovies() {
//		return movies;
//	}
//
//	public void setMovies(List<Movie> movies) {
//		this.movies = movies;
//	}
	
	
}
