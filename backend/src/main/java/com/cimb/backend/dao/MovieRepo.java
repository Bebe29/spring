package com.cimb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.backend.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
