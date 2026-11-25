package mx.edu.tecdesoftware.recetario.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// Las anotaciones de validación se revisan cuando el controlador recibe la receta con @Valid
public class Recipe {

    private Integer recipeId; // Integer y no int: null significa "receta nueva", el id lo genera la base

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre admite máximo 120 caracteres")
    private String name;

    @Size(max = 300, message = "La descripción admite máximo 300 caracteres")
    private String description;

    @NotBlank(message = "Los ingredientes son obligatorios")
    private String ingredients;

    @NotBlank(message = "Las instrucciones son obligatorias")
    private String instructions;

    @Positive(message = "El tiempo de preparación debe ser mayor a 0")
    private int prepTime; // minutos

    @Positive(message = "Las porciones deben ser mayores a 0")
    private int servings;

    @Size(max = 500, message = "La URL de la imagen admite máximo 500 caracteres")
    private String imageUrl;

    @Positive(message = "La categoría es obligatoria") // si no la mandan, llega en 0
    private int categoryId;

    private boolean active;
    private Category category;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
