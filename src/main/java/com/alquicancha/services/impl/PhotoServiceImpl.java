package com.alquicancha.services.impl;

import com.alquicancha.dtos.PhotoDTO;
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
    public PhotoDTO findById(Long id){
        Photo findPhoto = photoRepository.findById(id).orElse(null);
        PhotoDTO photoDTO = new PhotoDTO(findPhoto);
        return photoDTO;
    }

    @Override
    public PhotoDTO register(String name, String url, String description){
        Photo photo = new Photo(name, url, description);
        photoRepository.save(photo);
        PhotoDTO photoDTO = new PhotoDTO(photo);
        return photoDTO;
    }

    @Override
    public void delete(Long id){
        photoRepository.deleteById(id);
    }

    @Override
    public PhotoDTO update(Photo photo){
        photoRepository.save(photo);
        PhotoDTO photoDTO = new PhotoDTO(photo);
        return photoDTO;
    }
}
