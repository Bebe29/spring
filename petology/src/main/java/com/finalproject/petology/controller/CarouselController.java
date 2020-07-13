package com.finalproject.petology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.petology.entity.Carousel;
import com.finalproject.petology.service.CarouselService;

@RestController
@RequestMapping("/carousels")
@CrossOrigin(origins = "http://localhost:3000")
public class CarouselController {
	@Autowired
	private CarouselService carouselService;

	@GetMapping
	public Iterable<Carousel> getCarouselItems() {
		return carouselService.getCarouselItem();
	}

	@PostMapping
	public Carousel addCarouselItem(@RequestBody Carousel carousel) {
		return carouselService.postCarouselItem(carousel);
	}
}
