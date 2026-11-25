package mx.edu.tecdesoftware.recetario.domain.exception;

// Se lanza cuando una receta apunta a una categoría que no existe
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(int categoryId) {
        super("La categoría " + categoryId + " no existe");
    }
}
