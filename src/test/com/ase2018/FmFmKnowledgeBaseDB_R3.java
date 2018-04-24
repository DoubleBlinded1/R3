package com.ase2018;

import com.ase2018.fmfm.FmFmThreeTierComposition;
import com.ase2018.fmfm.Ingredient;
import com.ase2018.fmfm.Option;

import java.util.HashSet;
import java.util.Set;

public class FmFmKnowledgeBaseDB_R3 {

    public static void main(String[] args) {


        Set<Ingredient> requirements = new HashSet<>();
        Set<Ingredient> available = new HashSet<>();



        //recipe 1153 =

        long time, initTime = 0;

        System.out.println("\nCase 1: Exact matching");
        BuildCase1(available, requirements);
        initTime = System.currentTimeMillis();
        Set<Ingredient> comp = FmFmThreeTierComposition.RequirementsDriven(available, requirements);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result = " + comp + "    in " + time + "ms");


        String path = "resources/100recipes_with_substitutions_with_similarity_with_mods.sqlite3";
//        String path = "resources/500recipes_with_substitutions_with_similarity_with_mods.sqlite3";
//        String path = "resources/1000recipes_with_substitutions_with_similarity_with_mods.sqlite3";

        available = new HashSet<>();
        com.ase2018.fmfm.FmFmKnowledgeBaseDB kb = new com.ase2018.fmfm.FmFmKnowledgeBaseDB(path);
        System.out.println("\nCase 2a: Simple substitution");
        BuildCase2a(available, requirements);
        initTime = System.currentTimeMillis();
        comp = FmFmThreeTierComposition.Alternatives(available, requirements, kb);
         time = System.currentTimeMillis() - initTime;
        System.out.println("result = " + comp + "    in " + time + "ms");


        available = new HashSet<>();
        System.out.println("\nCase 2b: substitution with conflict");
        BuildCase2b(available, requirements);
        initTime = System.currentTimeMillis();
        comp = FmFmThreeTierComposition.Alternatives(available, requirements, kb);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result = " + comp + "    in " + time + "ms");

        available = new HashSet<>();
        System.out.println("\nCase 2c: substitution");
        BuildCase2c(available, requirements);
        initTime = System.currentTimeMillis();
        comp = FmFmThreeTierComposition.Alternatives(available, requirements, kb);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result = " + comp + "    in " + time + "ms");


        available = new HashSet<>();
        System.out.println("\nCase 3a: changing requirements/recipe");
        BuildCase3a(available, requirements);
        initTime = System.currentTimeMillis();
        Set<Option> options = FmFmThreeTierComposition.ResourceDriven(available, requirements, kb);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result options = " + options + "    in " + time + "ms");


        available = new HashSet<>();
        System.out.println("\nCase 3b: changing requirements and substitution");
        BuildCase3b(available, requirements);
        initTime = System.currentTimeMillis();
        options = FmFmThreeTierComposition.ResourceDriven(available, requirements, kb);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result options = " + options + "    in " + time + "ms");

    }

    private static void BuildCase1(Set<Ingredient> available, Set<Ingredient> requirements) {
        requirements.add(new Ingredient("cake flour"));
        requirements.add(new Ingredient("ground cumin"));
        requirements.add(new Ingredient("ground coriander"));
        requirements.add(new Ingredient("paprika"));
        requirements.add(new Ingredient("cayenne pepper"));
        requirements.add(new Ingredient("ground cinnamon"));
        requirements.add(new Ingredient("olive oil"));
        requirements.add(new Ingredient("kosher salt"));
        requirements.add(new Ingredient("freshly ground black pepper"));
        requirements.add(new Ingredient("boneless chicken"));
        requirements.add(new Ingredient("pitas"));
        requirements.add(new Ingredient("low-fat plain yogurt"));
        requirements.add(new Ingredient("grated garlic"));
        requirements.add(new Ingredient("finely grated lemon zest"));
        requirements.add(new Ingredient("fresh lemon juice"));
        requirements.add(new Ingredient("coarsely chopped dill"));
        requirements.add(new Ingredient("coarsely chopped mint"));
        requirements.add(new Ingredient("English hothouse cucumber"));
        requirements.add(new Ingredient("shredded romaine lettuce"));
        requirements.add(new Ingredient("small cherry or grape tomatoes"));
        requirements.add(new Ingredient("thinly sliced red onion"));


        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("ground cumin"));
        available.add(new Ingredient("ground coriander"));
        available.add(new Ingredient("paprika"));
        available.add(new Ingredient("cayenne pepper"));
        available.add(new Ingredient("ground cinnamon"));
        available.add(new Ingredient("olive oil"));
        available.add(new Ingredient("kosher salt"));
        available.add(new Ingredient("freshly ground black pepper"));
        available.add(new Ingredient("boneless chicken"));
        available.add(new Ingredient("pitas"));
        available.add(new Ingredient("low-fat plain yogurt"));
        available.add(new Ingredient("grated garlic"));
        available.add(new Ingredient("finely grated lemon zest"));
        available.add(new Ingredient("fresh lemon juice"));
        available.add(new Ingredient("coarsely chopped dill"));
        available.add(new Ingredient("coarsely chopped mint"));
        available.add(new Ingredient("English hothouse cucumber"));
        available.add(new Ingredient("shredded romaine lettuce"));
        available.add(new Ingredient("small cherry or grape tomatoes"));
        available.add(new Ingredient("thinly sliced red onion"));
    }

    private static void BuildCase2a(Set<Ingredient> available, Set<Ingredient> requirements) {
                available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("sifted all purpose flour"));
        available.add(new Ingredient("ground cumin"));
        available.add(new Ingredient("herbes de Provence"));
//        available.add(new Ingredient("ground coriander"));
        available.add(new Ingredient("thyme"));
//        available.add(new Ingredient("paprika"));
        available.add(new Ingredient("chilli"));
        available.add(new Ingredient("cayenne pepper"));
        available.add(new Ingredient("ground allspice"));
        available.add(new Ingredient("ground cinnamon"));
        available.add(new Ingredient("ground nutmeg"));
        available.add(new Ingredient("olive oil"));
        available.add(new Ingredient("virgin coconut oil"));
        available.add(new Ingredient("kosher salt"));
//        available.add(new Ingredient("freshly ground black pepper"));
        available.add(new Ingredient("hot red-pepper flakes"));
        available.add(new Ingredient("boneless chicken"));
        available.add(new Ingredient("pitas"));
        available.add(new Ingredient("low-fat plain yogurt"));
        available.add(new Ingredient("buttermilk"));
        available.add(new Ingredient("grated garlic"));
        available.add(new Ingredient("garlic powder"));
        available.add(new Ingredient("finely grated lemon zest"));
        available.add(new Ingredient("pure lemon extract"));
        available.add(new Ingredient("fresh lemon juice"));
        available.add(new Ingredient("apple cider vinegar"));//
        available.add(new Ingredient("coarsely chopped dill"));
//        available.add(new Ingredient("coarsely chopped mint"));
        available.add(new Ingredient("peppermint extract"));//
//        available.add(new Ingredient("English hothouse cucumber"));
        available.add(new Ingredient("diced cucumber"));
//        available.add(new Ingredient("shredded romaine lettuce"));
        available.add(new Ingredient("mixed lettuces"));
        available.add(new Ingredient("small cherry or grape tomatoes"));
        available.add(new Ingredient("can tomato sauce"));
        available.add(new Ingredient("thinly sliced red onion"));
        available.add(new Ingredient("chopped leeks"));
    }

    private static void BuildCase2b(Set<Ingredient> available, Set<Ingredient> requirements) {
//                available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("sifted all purpose flour"));
        available.add(new Ingredient("ground cumin"));
        available.add(new Ingredient("herbes de Provence"));
        available.add(new Ingredient("ground coriander"));
        available.add(new Ingredient("thyme"));
        available.add(new Ingredient("paprika"));
        available.add(new Ingredient("chilli"));
        available.add(new Ingredient("cayenne pepper"));
        available.add(new Ingredient("ground allspice"));
//        available.add(new Ingredient("ground cinnamon"));
        available.add(new Ingredient("ground nutmeg"));
//        available.add(new Ingredient("olive oil"));
        available.add(new Ingredient("virgin coconut oil"));
        available.add(new Ingredient("kosher salt"));
//        available.add(new Ingredient("freshly ground black pepper"));
        available.add(new Ingredient("hot red-pepper flakes"));
        available.add(new Ingredient("boneless chicken"));
        available.add(new Ingredient("pitas"));
//        available.add(new Ingredient("low-fat plain yogurt"));
        available.add(new Ingredient("buttermilk"));
//        available.add(new Ingredient("grated garlic"));
        available.add(new Ingredient("garlic powder"));
//        available.add(new Ingredient("finely grated lemon zest"));
        available.add(new Ingredient("pure lemon extract"));
//        available.add(new Ingredient("fresh lemon juice"));
        available.add(new Ingredient("apple cider vinegar"));//
        available.add(new Ingredient("coarsely chopped dill"));
//        available.add(new Ingredient("coarsely chopped mint"));
        available.add(new Ingredient("peppermint extract"));//
        available.add(new Ingredient("English hothouse cucumber"));
//        available.add(new Ingredient("shredded romaine lettuce"));
        available.add(new Ingredient("mixed lettuces"));
        available.add(new Ingredient("small cherry or grape tomatoes"));
        available.add(new Ingredient("thinly sliced red onion"));
    }

    private static void BuildCase2c(Set<Ingredient> available, Set<Ingredient> requirements) {
//        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("sifted all purpose flour"));
//        available.add(new Ingredient("ground cumin"));
        available.add(new Ingredient("herbes de Provence"));
//        available.add(new Ingredient("ground coriander"));
        available.add(new Ingredient("thyme"));
//        available.add(new Ingredient("paprika"));
        available.add(new Ingredient("chilli"));
//        available.add(new Ingredient("cayenne pepper"));
        available.add(new Ingredient("ground allspice"));
//        available.add(new Ingredient("ground cinnamon"));
        available.add(new Ingredient("ground nutmeg"));
//        available.add(new Ingredient("olive oil"));
        available.add(new Ingredient("virgin coconut oil"));
        available.add(new Ingredient("kosher salt"));
//        available.add(new Ingredient("freshly ground black pepper"));
        available.add(new Ingredient("hot red-pepper flakes"));
        available.add(new Ingredient("boneless chicken"));
        available.add(new Ingredient("pitas"));
//        available.add(new Ingredient("low-fat plain yogurt"));
        available.add(new Ingredient("buttermilk"));
//        available.add(new Ingredient("grated garlic"));
        available.add(new Ingredient("garlic powder"));
//        available.add(new Ingredient("finely grated lemon zest"));
        available.add(new Ingredient("pure lemon extract"));
//        available.add(new Ingredient("fresh lemon juice"));
        available.add(new Ingredient("apple cider vinegar"));//
        available.add(new Ingredient("coarsely chopped dill"));
//        available.add(new Ingredient("coarsely chopped mint"));
        available.add(new Ingredient("peppermint extract"));//
//        available.add(new Ingredient("English hothouse cucumber"));
        available.add(new Ingredient("diced cucumber"));
//        available.add(new Ingredient("shredded romaine lettuce"));
        available.add(new Ingredient("mixed lettuces"));
//        available.add(new Ingredient("small cherry or grape tomatoes"));
        available.add(new Ingredient("can tomato sauce"));
//        available.add(new Ingredient("thinly sliced red onion"));
        available.add(new Ingredient("chopped leeks"));

    }

    private static void BuildCase3a(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("ground cumin"));
        available.add(new Ingredient("ground coriander"));
        available.add(new Ingredient("paprika"));
        available.add(new Ingredient("cayenne pepper"));
        available.add(new Ingredient("ground cinnamon"));
        available.add(new Ingredient("olive oil"));
        available.add(new Ingredient("kosher salt"));
        available.add(new Ingredient("freshly ground black pepper"));
        available.add(new Ingredient("boneless chicken"));
        available.add(new Ingredient("pitas"));
        available.add(new Ingredient("low-fat plain yogurt"));
        available.add(new Ingredient("grated garlic"));
        available.add(new Ingredient("finely grated lemon zest"));
        available.add(new Ingredient("fresh lemon juice"));
        available.add(new Ingredient("coarsely chopped dill"));
        available.add(new Ingredient("coarsely chopped mint"));
        available.add(new Ingredient("English hothouse cucumber"));
//        available.add(new Ingredient("shredded romaine lettuce"));
//        available.add(new Ingredient("small cherry or grape tomatoes"));
//        available.add(new Ingredient("thinly sliced red onion"));


//        Recipe recipe = new Recipe("Blondie");
//        recipe.addIngredient(new Ingredient("Brown Sugar"));
//        recipe.addIngredient(new Ingredient("Almond Flour"));
//
//        kb.addRequirements(recipe);
//
//        recipe = new Recipe("Orange Cake");
//        recipe.addIngredient(new Ingredient("Brown Sugar"));
//        recipe.addIngredient(new Ingredient("Orange"));
//        recipe.addIngredient(new Ingredient("Butter"));
//
//        kb.addRequirements(recipe);
    }

    private static void BuildCase3b(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("ground cumin"));
        available.add(new Ingredient("ground coriander"));
        available.add(new Ingredient("paprika"));
        available.add(new Ingredient("cayenne pepper"));
        available.add(new Ingredient("ground cinnamon"));
        available.add(new Ingredient("olive oil"));
        available.add(new Ingredient("kosher salt"));
        available.add(new Ingredient("freshly ground black pepper"));
        available.add(new Ingredient("boneless chicken"));
        available.add(new Ingredient("pitas"));
        available.add(new Ingredient("low-fat plain yogurt"));
        available.add(new Ingredient("grated garlic"));
        available.add(new Ingredient("finely grated lemon zest"));
        available.add(new Ingredient("fresh lemon juice"));
        available.add(new Ingredient("coarsely chopped dill"));
        available.add(new Ingredient("coarsely chopped mint"));
        available.add(new Ingredient("English hothouse cucumber"));
        available.add(new Ingredient("shredded romaine lettuce"));
        available.add(new Ingredient("small cherry or grape tomatoes"));
        available.add(new Ingredient("thinly sliced red onion"));

//        kb.addSubstitute(new Ingredient("Almond Flour"),new Ingredient("Hazelnut Flour"),0.8);
//        kb.addSubstitute(new Ingredient("Almond Flour"),new Ingredient("Corn Flour"),0.4);
//        kb.addSubstitute(new Ingredient("Chocolate"),new Ingredient("Cocoa"),0.6);
//        kb.addSubstitute(new Ingredient("Cocoa"),new Ingredient("Hazelnut Flour"),0);
//
//        Recipe recipe = new Recipe("Blondie");
//        recipe.addIngredient(new Ingredient("Brown Sugar"));
//        recipe.addIngredient(new Ingredient("Hazelnut Flour"));
//
//        kb.addRequirements(recipe);
//
//        recipe = new Recipe("Orange Cake");
//        recipe.addIngredient(new Ingredient("Brown Sugar"));
//        recipe.addIngredient(new Ingredient("Orange"));
//        recipe.addIngredient(new Ingredient("Butter"));
//
//        kb.addRequirements(recipe);
    }


}
