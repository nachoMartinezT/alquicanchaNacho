package com.alquicancha.services;

import com.alquicancha.dtos.ProductDTO;
import com.alquicancha.entities.Photo;
import com.alquicancha.entities.Product;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
public interface IProductService {
    Set<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO register(String name, String description, LocalDate fromDate, LocalDate toDate, String category);
    void delete(Long id);
    ProductDTO update(Product product);
    void uploadProductImage(Long productId, MultipartFile photo) throws IOException;

    Set<Photo> getPhotos(Long productId);

    int getPhotosCount(Long productId);

    void deletePhoto(Long productId, Long photoId);


}
