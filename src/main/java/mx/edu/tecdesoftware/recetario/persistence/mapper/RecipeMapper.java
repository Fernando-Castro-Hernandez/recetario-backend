package mx.edu.tecdesoftware.recetario.persistence.mapper;

import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.persistence.entity.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface RecipeMapper {

    @Mapping(source = "idReceta", target = "recipeId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "ingredientes", target = "ingredients")
    @Mapping(source = "instrucciones", target = "instructions")
    @Mapping(source = "tiempoPreparacion", target = "prepTime")
    @Mapping(source = "porciones", target = "servings")
    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "estado", target = "active")
    @Mapping(source = "categoria", target = "category")
    Recipe toRecipe(Receta receta);

    List<Recipe> toRecipes(List<Receta> recetas);
}
