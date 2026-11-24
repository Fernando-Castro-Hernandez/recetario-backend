package mx.edu.tecdesoftware.recetario.persistence;

import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.domain.repository.RecipeRepository;
import mx.edu.tecdesoftware.recetario.persistence.crud.RecetaCrudRepository;
import mx.edu.tecdesoftware.recetario.persistence.mapper.RecipeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Adaptador: implementa el puerto del dominio usando Spring Data + MapStruct
@Repository
public class RecetaRepository implements RecipeRepository {

    private final RecetaCrudRepository recetaCrudRepository;
    private final RecipeMapper recipeMapper;

    // Inyección por constructor: Spring entrega las dependencias al crear la clase
    public RecetaRepository(RecetaCrudRepository recetaCrudRepository, RecipeMapper recipeMapper) {
        this.recetaCrudRepository = recetaCrudRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public List<Recipe> getAll() {
        return recipeMapper.toRecipes(recetaCrudRepository.findAllByOrderByNombreAsc());
    }

    @Override
    public Optional<Recipe> getRecipe(int recipeId) {
        return recetaCrudRepository.findById(recipeId).map(recipeMapper::toRecipe);
    }

    @Override
    public List<Recipe> getByCategory(int categoryId) {
        return recipeMapper.toRecipes(recetaCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId));
    }
}
