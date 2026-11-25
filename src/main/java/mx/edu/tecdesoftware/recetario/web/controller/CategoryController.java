package mx.edu.tecdesoftware.recetario.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.recetario.domain.Category;
import mx.edu.tecdesoftware.recetario.domain.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*") // igual que en RecipeController: solo para desarrollo
@Tag(name = "Category", description = "Categorías para el formulario y los filtros de la app")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Get active categories", description = "Return the active categories ordered by name")
    @ApiResponse(responseCode = "200", description = "Categories found")
    @GetMapping("")
    public ResponseEntity<List<Category>> getActive() {
        return ResponseEntity.ok(categoryService.getActive());
    }
}
