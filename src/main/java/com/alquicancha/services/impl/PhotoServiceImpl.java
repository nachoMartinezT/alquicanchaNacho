package com.alquicancha.services.impl;

import com.alquicancha.entities.Photo;
import com.alquicancha.repositories.IPhotoRepository;
import com.alquicancha.services.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements IPhotoService {
    @Autowired
    private IPhotoRepository photoRepository;

    @Override
    public Photo findById(Long id){
        return photoRepository.findById(id).orElse(null);
    }

    @Override
    public Photo register(String name, String url, String description){
        Photo photo = new Photo();
        photo.setName(name);
        photo.setUrl(url);
        photo.setDescription(description);
        return photoRepository.save(photo);
    }

    @Override
    public void delete(Long id){
        photoRepository.deleteById(id);
    }

    @Override
    public Photo update(Photo photo){
        photo.setName(photo.getName());
        photo.setUrl(photo.getUrl());
        photo.setDescription(photo.getDescription());

        return photoRepository.save(photo);
    }
}
