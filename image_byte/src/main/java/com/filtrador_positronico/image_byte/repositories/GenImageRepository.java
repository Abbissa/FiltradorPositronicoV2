package com.filtrador_positronico.image_byte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filtrador_positronico.image_byte.models.GenImage;

@Repository
public interface GenImageRepository extends JpaRepository<GenImage, Long> {

}
