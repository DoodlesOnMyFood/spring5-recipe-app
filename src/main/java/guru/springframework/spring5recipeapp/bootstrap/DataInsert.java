package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;

@Slf4j
@Controller
public class DataInsert implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataInsert(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.debug("DataInsert");
        guacamole();
    }

    private void guacamole() throws IOException {
        Recipe guacamole = new Recipe();
        Ingredient avocados = new Ingredient();
        avocados.setDescription("ripe avocados");
        avocados.setAmount(BigDecimal.valueOf(2));
        avocados.setUom(unitOfMeasureRepository.findByDescription("Whole").get());
        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(BigDecimal.valueOf(0.25));
        salt.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        Ingredient lemonJuice = new Ingredient();
        lemonJuice.setDescription("fresh lime juice or lemon juice ");
        lemonJuice.setAmount(BigDecimal.valueOf(1));
        lemonJuice.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        Ingredient onion = new Ingredient();
        onion.setDescription("minced red onion or thinly sliced green onion ");
        onion.setAmount(BigDecimal.valueOf(2));
        onion.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        Ingredient chiles = new Ingredient();
        chiles.setDescription("serrano chiles, stems and seeds removed, minced ");
        chiles.setAmount(BigDecimal.ONE);
        chiles.setUom(unitOfMeasureRepository.findByDescription("Whole").get());
        Ingredient pepper = new Ingredient();
        pepper.setUom(unitOfMeasureRepository.findByDescription("Dash").get());
        pepper.setAmount(BigDecimal.ONE);
        pepper.setDescription("freshly grated black pepper");
        Ingredient tomato = new Ingredient();
        tomato.setDescription("ripe tomato, seeds and pulp removed, chopped ");
        tomato.setAmount(BigDecimal.valueOf(0.5));
        tomato.setUom(unitOfMeasureRepository.findByDescription("Whole").get());
        Ingredient radish = new Ingredient();
        radish.setDescription("Red radishes or jicama, to garnish ");
        radish.setUom(unitOfMeasureRepository.findByDescription("As much as you need").get());
        radish.setAmount(BigDecimal.ONE);
        Ingredient tortillaChips = new Ingredient();
        tortillaChips.setAmount(null);
        tortillaChips.setDescription("Tortilla chips, to serve");
        tortillaChips.setUom(null);

        guacamole.addIngredient(tortillaChips);
        guacamole.addIngredient(radish);
        guacamole.addIngredient(tomato);
        guacamole.addIngredient(pepper);
        guacamole.addIngredient(chiles);
        guacamole.addIngredient(lemonJuice);
        guacamole.addIngredient(salt);
        guacamole.addIngredient(avocados);
        guacamole.addIngredient(onion);

        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setSource("Simply Recipes");
        guacamole.setCookTime(0);
        guacamole.setPrepTime(0);
        guacamole.setServings(4);
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setDifficulty(Difficulty.Easy);
        File image = new File("/home/jisoo/Downloads/guacamole.webp");
        guacamole.setImage(Files.readAllBytes(image.toPath()));
        Notes note = new Notes();
        note.setRecipeNotes("\n" +
                "\n" +
                "    Cut the avocado, remove flesh:\n" +
                "\n" +
                "    Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "    How to make guacamole - scoring avocado\n" +
                "    Mash with a fork:\n" +
                "\n" +
                "    Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.) " +
                "\n" +
                "Add salt, lime juice, and the rest:\n" +
                "\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "Serve:\n" +
                "\n" +
                "Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve. "
        );
        guacamole.setNotes(note);
        recipeRepository.save(guacamole);

        Recipe taco = new Recipe();
        taco.setDescription("Spicy Grilled Chicken Tacos");
        taco.setDifficulty(Difficulty.Medium);
        taco.setPrepTime(20);
        taco.setCookTime(15);
        taco.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        taco.setServings(6);
        taco.setSource("Simply Recipes");
        Ingredient chiliPowder= new Ingredient();
        chiliPowder.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        chiliPowder.setAmount(BigDecimal.valueOf(2));
        chiliPowder.setDescription("ancho chili powder");
        taco.addIngredient(chiliPowder);
        Ingredient oregano = new Ingredient();
        oregano.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        oregano.setAmount(BigDecimal.valueOf(1));
        oregano.setDescription("dried oregano ");
        taco.addIngredient(oregano);
        Ingredient cumin = new Ingredient();
        cumin.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        cumin.setAmount(BigDecimal.valueOf(1));
        cumin.setDescription("dried cumin");
        taco.addIngredient(cumin);
        Ingredient sugar = new Ingredient();
        sugar.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setDescription("sugar");
        taco.addIngredient(sugar);
        Ingredient saltTaco = new Ingredient();
        saltTaco.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        saltTaco.setAmount(BigDecimal.valueOf(0.5));
        saltTaco.setDescription("salt");
        taco.addIngredient(saltTaco);
        Ingredient garlic = new Ingredient();
        garlic.setUom(unitOfMeasureRepository.findByDescription("Clove").get());
        garlic.setAmount(BigDecimal.valueOf(1));
        garlic.setDescription("garlic, finely chopped");
        taco.addIngredient(garlic);
        Ingredient zest = new Ingredient();
        zest.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        zest.setAmount(BigDecimal.valueOf(1));
        zest.setDescription("finely grated orange zest");
        taco.addIngredient(zest);
        Ingredient oliveOil = new Ingredient();
        oliveOil.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        oliveOil.setAmount(BigDecimal.valueOf(2));
        oliveOil.setDescription("olive oil");
        taco.addIngredient(oliveOil);
        Ingredient juice = new Ingredient();
        juice.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        juice.setAmount(BigDecimal.valueOf(3));
        juice.setDescription("fresh-squeezed orange juice");
        taco.addIngredient(juice);
        Ingredient chicken = new Ingredient();
        chicken.setUom(null);
        chicken.setAmount(BigDecimal.valueOf(6));
        chicken.setDescription("skinless, boneless chicken thighs (1 1/4 pounds)");
        taco.addIngredient(chicken);
        Ingredient tortilla = new Ingredient();
        tortilla.setUom(null);
        tortilla.setAmount(BigDecimal.valueOf(8));
        tortilla.setDescription("small corn tortillas ");
        taco.addIngredient(tortilla);
        Ingredient arugula = new Ingredient();
        arugula.setUom(unitOfMeasureRepository.findByDescription("Cup(s)").get());
        arugula.setAmount(BigDecimal.valueOf(3));
        arugula.setDescription("packed baby arugula (3 ounces)");
        taco.addIngredient(arugula);
        Ingredient avocadoTaco = new Ingredient();
        avocadoTaco.setUom(unitOfMeasureRepository.findByDescription("Whole").get());
        avocadoTaco.setAmount(BigDecimal.valueOf(2));
        avocadoTaco.setDescription("medium ripe avocados, sliced ");
        taco.addIngredient(avocadoTaco);
        Ingredient radishTaco = new Ingredient();
        radishTaco.setUom(unitOfMeasureRepository.findByDescription("Whole").get());
        radishTaco.setAmount(BigDecimal.valueOf(4));
        radishTaco.setDescription("radishes, thinly sliced ");
        taco.addIngredient(radishTaco);
        Ingredient onionTaco = new Ingredient();
        onionTaco.setUom(null);
        onionTaco.setAmount(BigDecimal.valueOf(0.25));
        onionTaco.setDescription("red onion, thinly sliced ");
        taco.addIngredient(onionTaco);
        Ingredient cilantro = new Ingredient();
        cilantro.setUom(null);
        cilantro.setAmount(null);
        cilantro.setDescription("Roughly chopped cilantro ");
        taco.addIngredient(cilantro);
        Ingredient cherryTomato = new Ingredient();
        cherryTomato.setUom(null);
        cherryTomato.setAmount(BigDecimal.valueOf(0.5));
        cherryTomato.setDescription("pint cherry tomatoes, halved ");
        taco.addIngredient(cherryTomato);
        Ingredient sourCream = new Ingredient();
        sourCream.setUom(unitOfMeasureRepository.findByDescription("Cup(s)").get());
        sourCream.setAmount(BigDecimal.valueOf(0.5));
        sourCream.setDescription("sour cream thinned with 1/4 cup milk");
        taco.addIngredient(sourCream);
        Ingredient lime = new Ingredient();
        lime.setUom(unitOfMeasureRepository.findByDescription("Whole").get());
        lime.setAmount(BigDecimal.valueOf(1));
        lime.setDescription("lime, cut into wedges ");
        taco.addIngredient(lime);
        File imageTaco = new File("/home/jisoo/Downloads/taco.webp");
        taco.setImage(Files.readAllBytes(image.toPath()));
        Notes tacoNote = new Notes();
        tacoNote.setRecipeNotes("\n" +
                "Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "Make the marinade and coat the chicken:\n" +
                "\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "Grill the chicken:\n" +
                "\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "Warm the tortillas:\n" +
                "\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "Assemble the tacos:\n" +
                "\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges. ");
        taco.setNotes(tacoNote);
        recipeRepository.save(taco);

        Category mexican = new Category();
        mexican.setDescription("Mexican");
        mexican.getRecipes().add(guacamole);
        mexican.getRecipes().add(taco);
        guacamole.getCategories().add(mexican);
        taco.getCategories().add(mexican);
        categoryRepository.save(mexican);
    }
}
