package com.alquicancha.dtos;

import com.alquicancha.entities.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Set<PhotoDTO> photos = new HashSet<>();


    public ProductDTO(){

    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.fromDate = product.getFromDate();
        this.toDate = product.getToDate();
        this.photos = product.getPhotos().stream().map(PhotoDTO::new).collect(Collectors.toSet());
    }
}
