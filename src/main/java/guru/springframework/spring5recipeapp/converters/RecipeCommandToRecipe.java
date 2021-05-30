package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final NotesCommandToNotes notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, IngredientCommandToIngredient ingredientConverter, CategoryCommandToCategory categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null) return null;
        Recipe recipe = new Recipe();
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setId(recipeCommand.getId());
        recipe.setSource(recipeCommand.getSource());
        recipe.setServings(recipeCommand.getServings());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setImage(recipeCommand.getImage());
        if(recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories().forEach(x-> recipe.getCategories().add(categoryConverter.convert(x)));
        }
        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients().forEach(x -> recipe.getIngredients().add(ingredientConverter.convert(x)));
        }
        return recipe;
    }
}
