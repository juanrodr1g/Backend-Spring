package com.dib.springangular.services;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dib.springangular.models.Photo;
import com.dib.springangular.models.PhotoAux;
import com.dib.springangular.repositories.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }

    public String addPhoto(PhotoAux file) throws IOException {
    	Photo photo = new Photo(file.getTitle());
    	photo.setId(file.getId());
    	photo.setId_user(file.getId_user());;
    	photo.setImage(file.getImage());
    	photo = photoRepo.insert(photo);
        return photo.getId();
    }
}