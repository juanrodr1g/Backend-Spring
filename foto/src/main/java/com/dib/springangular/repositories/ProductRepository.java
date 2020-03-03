package com.dib.springangular.repositories;

import com.dib.springangular.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<User, String> {

    @Override
    void delete(User deleted);
}