package mx.edu.tecdesoftware.recetario.persistence;

import mx.edu.tecdesoftware.recetario.domain.Category;
import mx.edu.tecdesoftware.recetario.domain.repository.CategoryRepository;
import mx.edu.tecdesoftware.recetario.persistence.crud.CategoriaCrudRepository;
import mx.edu.tecdesoftware.recetario.persistence.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// Adaptador: implementa el puerto de categorías usando Spring Data + MapStruct
@Repository
public class CategoriaRepository implements CategoryRepository {

    private final CategoriaCrudRepository categoriaCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoriaRepository(CategoriaCrudRepository categoriaCrudRepository, CategoryMapper categoryMapper) {
        this.categoriaCrudRepository = categoriaCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getActive() {
        return categoryMapper.toCategories(categoriaCrudRepository.findByEstadoTrueOrderByDescripcionAsc());
    }

    @Override
    public boolean exists(int categoryId) {
        return categoriaCrudRepository.existsById(categoryId);
    }
}
