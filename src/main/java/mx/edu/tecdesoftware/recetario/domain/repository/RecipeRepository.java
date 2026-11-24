package mx.edu.tecdesoftware.recetario.domain.repository;

import mx.edu.tecdesoftware.recetario.domain.Recipe;

import java.util.List;
import java.util.Optional;

// Puerto: lo que el dominio necesita, sin saber que existe una base de datos
public interface RecipeRepository {

    List<Recipe> getAll();

    Optional<Recipe> getRecipe(int recipeId);

    List<Recipe> getByCategory(int categoryId);
}
