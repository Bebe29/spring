package com.finalproject.petology.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.petology.entity.Carousel;

public interface CarouselRepo extends JpaRepository <Carousel, Integer> {

}
