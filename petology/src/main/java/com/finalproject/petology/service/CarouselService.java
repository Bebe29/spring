package com.finalproject.petology.service;

import com.finalproject.petology.entity.Carousel;

public interface CarouselService {
	public Iterable<Carousel> getCarouselItem();
	
	public Carousel postCarouselItem(Carousel carousel);
}
