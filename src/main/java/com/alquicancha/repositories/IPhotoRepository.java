package com.alquicancha.repositories;

import com.alquicancha.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoRepository extends JpaRepository<Photo, Long> {
}
