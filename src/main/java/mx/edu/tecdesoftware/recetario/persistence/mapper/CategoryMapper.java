package mx.edu.tecdesoftware.recetario.persistence.mapper;

import mx.edu.tecdesoftware.recetario.domain.Category;
import mx.edu.tecdesoftware.recetario.persistence.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "descripcion", target = "category")
    @Mapping(source = "estado", target = "active")
    Category toCategory(Categoria categoria);

    List<Category> toCategories(List<Categoria> categorias);
}
