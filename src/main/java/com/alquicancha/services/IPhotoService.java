package com.alquicancha.services;

import com.alquicancha.dtos.PhotoDTO;
import com.alquicancha.entities.Photo;

public interface IPhotoService {
    PhotoDTO findById(Long id);
    PhotoDTO register(String name, String url, String description);
    void delete(Long id);
    PhotoDTO editPhoto(Long productId,Long photoId, Photo photo);

}
