package mx.edu.tecdesoftware.recetario.web.controller;

import mx.edu.tecdesoftware.recetario.domain.Category;
import mx.edu.tecdesoftware.recetario.domain.Recipe;
import mx.edu.tecdesoftware.recetario.domain.exception.CategoryNotFoundException;
import mx.edu.tecdesoftware.recetario.domain.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest levanta solo la capa web (este controlador y el @RestControllerAdvice):
// no hay base de datos, por eso el servicio se reemplaza con un mock de Mockito.
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    private static final String VALID_RECIPE = """
            {
              "name": "Molletes",
              "ingredients": "2 bolillos, frijoles y queso",
              "instructions": "Unta, gratina y sirve.",
              "prepTime": 15,
              "servings": 2,
              "categoryId": 1
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecipeService recipeService;

    @Test
    void postValidRecipeReturns201WithCategory() throws Exception {
        when(recipeService.save(any(Recipe.class))).thenReturn(savedRecipe());

        mockMvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(VALID_RECIPE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.recipeId").value(14))
                .andExpect(jsonPath("$.category.category").value("Desayunos"));
    }

    @Test
    void postWithBlankNameAndZeroPrepTimeReturns400WithFieldErrors() throws Exception {
        String invalid = VALID_RECIPE
                .replace("\"name\": \"Molletes\"", "\"name\": \"  \"")
                .replace("\"prepTime\": 15", "\"prepTime\": 0");

        mockMvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(invalid))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors.name").value("El nombre es obligatorio"))
                .andExpect(jsonPath("$.errors.prepTime").value("El tiempo de preparación debe ser mayor a 0"));

        // Si la validación falla, el controlador ni siquiera llama al servicio
        verify(recipeService, never()).save(any(Recipe.class));
    }

    @Test
    void postWithUnknownCategoryReturns400() throws Exception {
        when(recipeService.save(any(Recipe.class))).thenThrow(new CategoryNotFoundException(99));

        mockMvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).content(VALID_RECIPE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.categoryId").value("La categoría 99 no existe"));
    }

    @Test
    void putUnknownRecipeReturns404() throws Exception {
        when(recipeService.update(eq(999), any(Recipe.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/recipes/999").contentType(MediaType.APPLICATION_JSON).content(VALID_RECIPE))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUnknownRecipeReturns404() throws Exception {
        when(recipeService.delete(999)).thenReturn(false);

        mockMvc.perform(delete("/recipes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteExistingRecipeReturns200() throws Exception {
        when(recipeService.delete(14)).thenReturn(true);

        mockMvc.perform(delete("/recipes/14"))
                .andExpect(status().isOk());
    }

    // Lo que devolvería el servicio después de guardar: con id y categoría completa
    private Recipe savedRecipe() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategory("Desayunos");
        category.setActive(true);

        Recipe recipe = new Recipe();
        recipe.setRecipeId(14);
        recipe.setName("Molletes");
        recipe.setIngredients("2 bolillos, frijoles y queso");
        recipe.setInstructions("Unta, gratina y sirve.");
        recipe.setPrepTime(15);
        recipe.setServings(2);
        recipe.setCategoryId(1);
        recipe.setActive(true);
        recipe.setCategory(category);
        return recipe;
    }
}
