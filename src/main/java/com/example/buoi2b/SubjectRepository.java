package com.example.buoi2b;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<User, String>{
    
}
