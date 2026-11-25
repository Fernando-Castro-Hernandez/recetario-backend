package mx.edu.tecdesoftware.recetario.domain.repository;

import mx.edu.tecdesoftware.recetario.domain.Category;

import java.util.List;

// Puerto: lo que el dominio necesita de las categorías
public interface CategoryRepository {

    List<Category> getActive();

    boolean exists(int categoryId);
}
