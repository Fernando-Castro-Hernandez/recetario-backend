package mx.edu.tecdesoftware.recetario.persistence.crud;

import mx.edu.tecdesoftware.recetario.persistence.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {

    // SELECT * FROM categorias WHERE estado = TRUE ORDER BY descripcion ASC
    List<Categoria> findByEstadoTrueOrderByDescripcionAsc();
}
