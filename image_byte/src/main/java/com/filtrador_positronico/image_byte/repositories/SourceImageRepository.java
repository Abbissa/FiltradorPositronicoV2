package com.filtrador_positronico.image_byte.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filtrador_positronico.image_byte.models.SourceImage;

@Repository
public interface SourceImageRepository extends JpaRepository<SourceImage, Long> {

    List<SourceImage> findByUserId(Long userId);

}
