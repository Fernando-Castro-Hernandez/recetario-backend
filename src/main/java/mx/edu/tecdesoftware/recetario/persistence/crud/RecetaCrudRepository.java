package mx.edu.tecdesoftware.recetario.persistence.crud;

import mx.edu.tecdesoftware.recetario.persistence.entity.Receta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecetaCrudRepository extends CrudRepository<Receta, Integer> {

    // SELECT * FROM recetas ORDER BY nombre ASC
    List<Receta> findAllByOrderByNombreAsc();

    // SELECT * FROM recetas WHERE id_categoria = ? ORDER BY nombre ASC
    List<Receta> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);
}
