package guru.springframework.spring5recipeapp.converters;


import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null) return null;

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setId(recipe.getId());
        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setImage(recipe.getImage());
        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients().forEach(x -> recipeCommand.getIngredients().add(ingredientConverter.convert(x)));
        }
        if(recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories().forEach(x -> recipeCommand.getCategories().add(categoryConverter.convert(x)));
        }
        return recipeCommand;
    }
}
