package mx.edu.tecdesoftware.recetario.domain.service;

import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.domain.exception.CategoryNotFoundException;
import mx.edu.tecdesoftware.recetario.domain.repository.CategoryRepository;
import mx.edu.tecdesoftware.recetario.domain.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public RecipeService(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
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

    public Recipe save(Recipe recipe) {
        recipe.setRecipeId(null); // el id lo genera la base de datos, aunque el cliente mande uno
        recipe.setActive(true);   // toda receta nueva nace activa
        return store(recipe);
    }

    // Optional vacío = la receta no existe (el controlador responde 404)
    public Optional<Recipe> update(int recipeId, Recipe recipe) {
        return getRecipe(recipeId).map(current -> {
            recipe.setRecipeId(recipeId);         // manda el id de la URL, no el del cuerpo
            recipe.setActive(current.isActive()); // el estado no se edita desde la app
            return store(recipe);
        });
    }

    // Igual que en Market: primero revisa que exista, luego borra
    public boolean delete(int recipeId) {
        return getRecipe(recipeId).map(recipe -> {
            recipeRepository.delete(recipeId);
            return true;
        }).orElse(false);
    }

    // Revisar la categoría antes de guardar evita que la FK de la base falle con un error 500
    private Recipe store(Recipe recipe) {
        if (!categoryRepository.exists(recipe.getCategoryId())) {
            throw new CategoryNotFoundException(recipe.getCategoryId());
        }
        return recipeRepository.save(recipe);
    }
}
