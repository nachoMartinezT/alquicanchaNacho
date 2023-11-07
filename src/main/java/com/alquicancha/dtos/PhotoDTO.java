package com.alquicancha.dtos;

import com.alquicancha.entities.Photo;
import lombok.Data;

@Data
public class PhotoDTO {
    private Long id;
    private String name;
    private String url;
    private String description;

    public PhotoDTO() {
    }
    public PhotoDTO(Photo photo) {
        this.id = photo.getId();
        this.name = photo.getName();
        this.url = photo.getUrl();
        this.description = photo.getDescription();
    }
}
