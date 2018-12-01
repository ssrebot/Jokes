package com.jokes.jokes.Services;

import com.jokes.jokes.Entities.Category;
import com.jokes.jokes.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {

        List<Category> categories = (List<Category>) categoryRepository.findAll();

        return categories;
    }

    public Category getOneCategory(int id) {

        Category category = (Category) categoryRepository.findById(id).get();

        return category;
    }
}
