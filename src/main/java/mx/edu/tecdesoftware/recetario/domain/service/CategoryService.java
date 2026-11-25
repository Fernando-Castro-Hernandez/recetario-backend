package mx.edu.tecdesoftware.recetario.domain.service;

import mx.edu.tecdesoftware.recetario.domain.Category;
import mx.edu.tecdesoftware.recetario.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getActive() {
        return categoryRepository.getActive();
    }
}
