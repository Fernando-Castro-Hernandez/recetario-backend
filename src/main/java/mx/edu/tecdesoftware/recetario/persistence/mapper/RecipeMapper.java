package mx.edu.tecdesoftware.recetario.persistence.mapper;

import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.persistence.entity.Receta;
import org.mapstruct.InheritInverseConfiguration;
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
    @Mapping(source = "imagenUrl", target = "imageUrl")
    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "estado", target = "active")
    @Mapping(source = "categoria", target = "category")
    Recipe toRecipe(Receta receta);

    List<Recipe> toRecipes(List<Receta> recetas);

    // Lo mismo al revés (categoryId -> idCategoria). La asociación "categoria" es de solo
    // lectura: la FK se escribe con idCategoria, así que aquí se ignora.
    @InheritInverseConfiguration
    @Mapping(target = "categoria", ignore = true)
    Receta toReceta(Recipe recipe);
}
