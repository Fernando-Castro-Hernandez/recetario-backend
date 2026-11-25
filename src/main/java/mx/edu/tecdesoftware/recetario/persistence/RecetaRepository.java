package mx.edu.tecdesoftware.recetario.persistence;

import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.domain.repository.RecipeRepository;
import mx.edu.tecdesoftware.recetario.persistence.crud.RecetaCrudRepository;
import mx.edu.tecdesoftware.recetario.persistence.entity.Receta;
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

    // save() hace INSERT si el id es null y UPDATE si ya trae id
    @Override
    public Recipe save(Recipe recipe) {
        Receta guardada = recetaCrudRepository.save(recipeMapper.toReceta(recipe));
        // "categoria" es de solo lectura: lo que devuelve save() la trae vacía.
        // Se vuelve a leer la receta para que JPA traiga la categoría completa
        // (es una consulta nueva porque aquí no hay @Transactional que la guarde en caché).
        return getRecipe(guardada.getIdReceta()).orElseThrow();
    }

    @Override
    public void delete(int recipeId) {
        recetaCrudRepository.deleteById(recipeId);
    }
}
