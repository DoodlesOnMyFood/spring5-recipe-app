package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements  ImageService{
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long id, MultipartFile file) throws IOException {
        if(recipeRepository.findById(id).isEmpty()){
            log.debug("Recipe not found");
        }else{
            Recipe recipe = recipeRepository.findById(id).get();
            recipe.setImage(file.getBytes());
            recipeRepository.save(recipe);
        }
    }
}
