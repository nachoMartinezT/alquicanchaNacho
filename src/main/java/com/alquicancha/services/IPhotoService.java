package com.alquicancha.services;

import com.alquicancha.entities.Photo;

public interface IPhotoService {
    Photo findById(Long id);
    Photo register(String name, String url, String description);
    void delete(Long id);
    Photo update(Photo photo);

}
