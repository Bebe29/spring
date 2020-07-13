package com.cimb.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.backend.dao.MovieRepo;
import com.cimb.backend.entity.Movie;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {
	@Autowired
	private MovieRepo movierepo;
	
	@GetMapping
	public Iterable<Movie> getMovies() {
		return movierepo.findAll();
	}
}
