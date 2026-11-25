package mx.edu.tecdesoftware.recetario.web.exception;

import mx.edu.tecdesoftware.recetario.domain.exception.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

// Atrapa las excepciones de todos los controladores y las convierte en respuestas JSON
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String INVALID_DATA = "Datos inválidos";

    // @Valid encontró errores en el cuerpo: se devuelve un mensaje por campo
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.putIfAbsent(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new ApiError(400, INVALID_DATA, errors));
    }

    // La categoría viene en el cuerpo: es un dato inválido del cliente (400), no una URL inexistente (404)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFoundException ex) {
        return ResponseEntity.badRequest().body(new ApiError(400, INVALID_DATA, Map.of("categoryId", ex.getMessage())));
    }

    // JSON mal escrito, o un null en un campo numérico como prepTime
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadableBody(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ApiError(400, "El cuerpo de la petición no es un JSON válido", Map.of()));
    }
}
