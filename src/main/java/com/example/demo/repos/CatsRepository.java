package com.example.demo.repos;

import com.example.demo.model.Cats;
import org.springframework.data.repository.CrudRepository;

public interface CatsRepository extends CrudRepository<Cats, Long> {
}
