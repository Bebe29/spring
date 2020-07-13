package com.cimb.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cimb.backend.entity.Character;

public interface CharacterRepo extends JpaRepository<Character,Integer> {

}
