package com.ase2018;

import com.ase2018.fmfm.FmFmThreeTierComposition;
import com.ase2018.fmfm.Ingredient;
import com.ase2018.fmfm.Option;

import java.util.HashSet;
import java.util.Set;

public class FmFmKnowledgeBaseDB_C_R1 {

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


        String path = "resources/1000recipes_with_substitutions_with_similarity.sqlite3";

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
        requirements.add(new Ingredient("cake flour"));
        requirements.add(new Ingredient("baking soda"));
        requirements.add(new Ingredient("baking powder"));
        requirements.add(new Ingredient("plus pinch of salt"));
        requirements.add(new Ingredient("buttermilk"));
        requirements.add(new Ingredient("(3 sticks) unsalted butter"));
        requirements.add(new Ingredient("sugar"));
        requirements.add(new Ingredient("eggs"));
        requirements.add(new Ingredient("packages cream cheese"));
        requirements.add(new Ingredient("powdered sugar"));
        requirements.add(new Ingredient("bananas"));

        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("baking soda"));
        available.add(new Ingredient("baking powder"));
        available.add(new Ingredient("plus pinch of salt"));
        available.add(new Ingredient("buttermilk"));
        available.add(new Ingredient("(3 sticks) unsalted butter"));
        available.add(new Ingredient("sugar"));
        available.add(new Ingredient("eggs"));
        available.add(new Ingredient("packages cream cheese"));
        available.add(new Ingredient("powdered sugar"));
        available.add(new Ingredient("bananas"));

    }

    private static void BuildCase2a(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("baking soda"));
        available.add(new Ingredient("baking powder"));
        available.add(new Ingredient("plus pinch of salt"));
        available.add(new Ingredient("plain yoghurt"));
        available.add(new Ingredient("(3 sticks) unsalted butter"));
        available.add(new Ingredient("sugar"));
        available.add(new Ingredient("eggs"));
        available.add(new Ingredient("packages cream cheese"));
        available.add(new Ingredient("powdered sugar"));
        available.add(new Ingredient("bananas"));
    }

    private static void BuildCase2b(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("baking soda"));
        available.add(new Ingredient("baking powder"));
        available.add(new Ingredient("plus pinch of salt"));
        available.add(new Ingredient("buttermilk"));
        available.add(new Ingredient("(3 sticks) unsalted butter"));
        available.add(new Ingredient("sugar"));
        available.add(new Ingredient("eggs"));
        available.add(new Ingredient("packages cream cheese"));
        available.add(new Ingredient("powdered sugar"));
        available.add(new Ingredient("bananas"));

    }

    private static void BuildCase3a(Set<Ingredient> available, Set<Ingredient> requirements) {
        available.add(new Ingredient("cake flour"));
        available.add(new Ingredient("baking soda"));
        available.add(new Ingredient("baking powder"));
        available.add(new Ingredient("plus pinch of salt"));
        available.add(new Ingredient("buttermilk"));
        available.add(new Ingredient("(3 sticks) unsalted butter"));
        available.add(new Ingredient("sugar"));
        available.add(new Ingredient("eggs"));
        available.add(new Ingredient("packages cream cheese"));
        available.add(new Ingredient("powdered sugar"));
        available.add(new Ingredient("apple"));
        available.add(new Ingredient("orange"));


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
        available.add(new Ingredient("baking soda"));
        available.add(new Ingredient("baking powder"));
        available.add(new Ingredient("plus pinch of salt"));
        available.add(new Ingredient("buttermilk"));
        available.add(new Ingredient("(3 sticks) unsalted butter"));
        available.add(new Ingredient("sugar"));
        available.add(new Ingredient("eggs"));
        available.add(new Ingredient("packages cream cheese"));
        available.add(new Ingredient("powdered sugar"));
        available.add(new Ingredient("bananas"));

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
