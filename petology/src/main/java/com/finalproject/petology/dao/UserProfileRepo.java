package com.finalproject.petology.dao;

import com.finalproject.petology.entity.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Integer> {

}