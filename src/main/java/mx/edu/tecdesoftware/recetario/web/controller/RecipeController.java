package mx.edu.tecdesoftware.recetario.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.domain.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "*") // Flutter web corre en otro origen (otro puerto); solo para desarrollo
@Tag(name = "Recipe", description = "Recetas del recetario con su categoría")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Get all recipes", description = "Return every recipe ordered by name, with its category")
    @ApiResponse(responseCode = "200", description = "Recipes found")
    @GetMapping("")
    public ResponseEntity<List<Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @Operation(summary = "Get recipe by ID")
    @ApiResponse(responseCode = "200", description = "Recipe found")
    @ApiResponse(responseCode = "404", description = "Recipe not found")
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(
            @Parameter(description = "ID of the recipe", example = "1", required = true)
            @PathVariable("id") int recipeId) {
        return recipeService.getRecipe(recipeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get recipes by category", description = "Return the recipes of a category ordered by name")
    @ApiResponse(responseCode = "200", description = "Recipes of the category (may be empty)")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Recipe>> getByCategory(
            @Parameter(description = "ID of the category", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        return ResponseEntity.ok(recipeService.getByCategory(categoryId));
    }
}
