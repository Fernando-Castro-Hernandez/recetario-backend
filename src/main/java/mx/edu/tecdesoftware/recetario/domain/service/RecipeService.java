package mx.edu.tecdesoftware.recetario.domain.service;

import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.domain.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAll() {
        return recipeRepository.getAll();
    }

    public Optional<Recipe> getRecipe(int recipeId) {
        return recipeRepository.getRecipe(recipeId);
    }

    public List<Recipe> getByCategory(int categoryId) {
        return recipeRepository.getByCategory(categoryId);
    }
}
