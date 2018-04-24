package com.ase2018;

import com.ase2018.fmfm.FmFmThreeTierComposition;
import com.ase2018.fmfm.Ingredient;
import com.ase2018.fmfm.Option;

import java.util.HashSet;
import java.util.Set;

public class FmFmKnowledgeBaseDB_R1 {

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
        requirements.add(new Ingredient("Boston lettuce"));
        requirements.add(new Ingredient("bell pepper"));
        requirements.add(new Ingredient("carrots"));
        requirements.add(new Ingredient("mint leaves"));
        requirements.add(new Ingredient("sprigs cilantro"));


        available.add(new Ingredient("Boston lettuce"));
        available.add(new Ingredient("bell pepper"));
        available.add(new Ingredient("carrots"));
        available.add(new Ingredient("mint leaves"));
        available.add(new Ingredient("sprigs cilantro"));

    }

    private static void BuildCase2a(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("Boston lettuce"));
        available.add(new Ingredient("bell pepper"));
        available.add(new Ingredient("carrots"));
        available.add(new Ingredient("mint leaves"));
        available.add(new Ingredient("sprigs cilantro"));
    }

    private static void BuildCase2b(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("Boston lettuce"));
        available.add(new Ingredient("bell pepper"));
        available.add(new Ingredient("carrots"));
        available.add(new Ingredient("mint leaves"));
        available.add(new Ingredient("sprigs cilantro"));
    }

    private static void BuildCase3a(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("Boston lettuce"));
        available.add(new Ingredient("bell pepper"));
        available.add(new Ingredient("carrots"));
        available.add(new Ingredient("mint leaves"));
        available.add(new Ingredient("sprigs cilantro"));


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
        available.add(new Ingredient("Boston lettuce"));
        available.add(new Ingredient("bell pepper"));
        available.add(new Ingredient("carrots"));
        available.add(new Ingredient("mint leaves"));
        available.add(new Ingredient("sprigs cilantro"));

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
