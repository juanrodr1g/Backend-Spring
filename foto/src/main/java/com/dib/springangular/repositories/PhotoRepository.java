package com.dib.springangular.repositories;

import com.dib.springangular.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PhotoRepository extends MongoRepository<Photo, String> {

}