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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="movies")
public class Movie {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String title;
		private int year;
		private String synopsis;
		
		@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
		@JoinTable(name = "categories_movies", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
		private List<Category> categories;
		
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "movie", cascade = CascadeType.ALL)
		@JsonIgnore
		private List<Character> characters;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getSynopsis() {
			return synopsis;
		}
		public void setSynopsis(String synopsis) {
			this.synopsis = synopsis;
		}
		public List<Category> getCategories() {
			return categories;
		}
		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
		public List<Character> getCharacters() {
			return characters;
		}
		public void setCharacters(List<Character> characters) {
			this.characters = characters;
		}

		
}
