package com.finalproject.petology.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.petology.dao.CarouselRepo;
import com.finalproject.petology.entity.Carousel;
import com.finalproject.petology.service.CarouselService;

@Service
public class CarouselServiceImpl implements CarouselService{
	@Autowired
	private CarouselRepo carouselRepo;
	
	@Override
	@Transactional
	public Iterable<Carousel> getCarouselItem(){;
	return carouselRepo.findAll();
	}
	
	@Override
	@Transactional
	public Carousel postCarouselItem(Carousel carousel){
		carousel.setId(0);
		return carouselRepo.save(carousel);
	}
}
