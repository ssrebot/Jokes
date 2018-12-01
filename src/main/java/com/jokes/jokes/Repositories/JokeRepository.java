package com.jokes.jokes.Repositories;

import com.jokes.jokes.Entities.Joke;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends CrudRepository<Joke, Integer > {
}
