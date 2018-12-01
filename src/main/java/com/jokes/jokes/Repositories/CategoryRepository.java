package com.jokes.jokes.Repositories;
import com.jokes.jokes.Entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer > {
}
