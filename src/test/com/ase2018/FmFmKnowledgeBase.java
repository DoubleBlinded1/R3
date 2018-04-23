package com.ase2018;

import com.ase2018.fmfm.FmFmThreeTierComposition;
import com.ase2018.fmfm.Ingredient;
import com.ase2018.fmfm.Option;
import com.ase2018.fmfm.Recipe;

import java.util.HashSet;
import java.util.Set;

public class FmFmKnowledgeBase {
    public static void main(String[] args) {


        Set<Ingredient> requirements = new HashSet<>();
        Set<Ingredient> available = new HashSet<>();

        long time, initTime = 0;

        System.out.println("\nCase 1: Exact matching");
        BuildCase1(available, requirements);
        initTime = System.currentTimeMillis();
        Set<Ingredient> comp = FmFmThreeTierComposition.RequirementsDriven(available, requirements);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result = " + comp + "    in " + time + "ms");


        available = new HashSet<>();
        com.ase2018.fmfm.FmFmKnowledgeBase kb = new com.ase2018.fmfm.FmFmKnowledgeBase("");
        System.out.println("\nCase 2a: Simple substitution");
        BuildCase2a(available, requirements, kb);
        initTime = System.currentTimeMillis();
        comp = FmFmThreeTierComposition.Alternatives(available, requirements, kb);
        System.out.println("result = " + comp + "    in " + time + "ms");


        available = new HashSet<>();
        kb = new com.ase2018.fmfm.FmFmKnowledgeBase("");
        System.out.println("\nCase 2b: substitution with conflict");
        BuildCase2b(available, requirements, kb);
        initTime = System.currentTimeMillis();
        comp = FmFmThreeTierComposition.Alternatives(available, requirements, kb);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result = " + comp + "    in " + time + "ms");


        available = new HashSet<>();
        kb = new com.ase2018.fmfm.FmFmKnowledgeBase("");
        System.out.println("\nCase 3a: changing requirements/recipe");
        BuildCase3a(available, requirements, kb);
        initTime = System.currentTimeMillis();
        Set<Option> options = FmFmThreeTierComposition.ResourceDriven(available, requirements, kb);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result options = " + options + "    in " + time + "ms");


        available = new HashSet<>();
        kb = new com.ase2018.fmfm.FmFmKnowledgeBase("");
        System.out.println("\nCase 3b: changing requirements and substitution");
        BuildCase3b(available, requirements, kb);
        initTime = System.currentTimeMillis();
        options = FmFmThreeTierComposition.ResourceDriven(available, requirements, kb);
        time = System.currentTimeMillis() - initTime;
        System.out.println("result options = " + options + "    in " + time + "ms");

    }

    private static void BuildCase1(Set<Ingredient> available, Set<Ingredient> requirements) {
        requirements.add(new Ingredient("Chocolate"));
        requirements.add(new Ingredient("Brown Sugar"));
        requirements.add(new Ingredient("Almond Flour"));


        available.add(new Ingredient("Chocolate"));
        available.add(new Ingredient("Brown Sugar"));
        available.add(new Ingredient("Almond Flour"));
    }

    private static void BuildCase2a(Set<Ingredient> available, Set<Ingredient> requirements, com.ase2018.fmfm.FmFmKnowledgeBase kb) {
        available.add(new Ingredient("Chocolate"));
        available.add(new Ingredient("Brown Sugar"));
        available.add(new Ingredient("Hazelnut Flour"));
        available.add(new Ingredient("Corn Flour"));

        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Hazelnut Flour"), 0.8);
        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Corn Flour"), 0.4);
    }

    private static void BuildCase2b(Set<Ingredient> available, Set<Ingredient> requirements, com.ase2018.fmfm.FmFmKnowledgeBase kb) {
        available.add(new Ingredient("Cocoa"));
        available.add(new Ingredient("Brown Sugar"));
        available.add(new Ingredient("Hazelnut Flour"));
        available.add(new Ingredient("Corn Flour"));


        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Hazelnut Flour"), 0.8);
        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Corn Flour"), 0.4);
        kb.addSubstitute(new Ingredient("Chocolate"), new Ingredient("Cocoa"), 0.6);
        kb.addSubstitute(new Ingredient("Cocoa"), new Ingredient("Hazelnut Flour"), 0);
    }

    private static void BuildCase3a(Set<Ingredient> available, Set<Ingredient> requirements, com.ase2018.fmfm.FmFmKnowledgeBase kb) {
        available.add(new Ingredient("Brown Sugar"));
        available.add(new Ingredient("Hazelnut Flour"));
        available.add(new Ingredient("Corn Flour"));

        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Hazelnut Flour"), 0.8);
        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Corn Flour"), 0.4);
        kb.addSubstitute(new Ingredient("Chocolate"), new Ingredient("Cocoa"), 0.6);
        kb.addSubstitute(new Ingredient("Cocoa"), new Ingredient("Hazelnut Flour"), 0);

        Recipe recipe = new Recipe("Blondie");
        recipe.addIngredient(new Ingredient("Brown Sugar"));
        recipe.addIngredient(new Ingredient("Almond Flour"));

        kb.addRequirements(recipe);

        recipe = new Recipe("Orange Cake");
        recipe.addIngredient(new Ingredient("Brown Sugar"));
        recipe.addIngredient(new Ingredient("Orange"));
        recipe.addIngredient(new Ingredient("Butter"));

        kb.addRequirements(recipe);
    }

    private static void BuildCase3b(Set<Ingredient> available, Set<Ingredient> requirements, com.ase2018.fmfm.FmFmKnowledgeBase kb) {
        available.add(new Ingredient("Brown Sugar"));
        available.add(new Ingredient("Hazelnut Flour"));
        available.add(new Ingredient("Corn Flour"));

        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Hazelnut Flour"), 0.8);
        kb.addSubstitute(new Ingredient("Almond Flour"), new Ingredient("Corn Flour"), 0.4);
        kb.addSubstitute(new Ingredient("Chocolate"), new Ingredient("Cocoa"), 0.6);
        kb.addSubstitute(new Ingredient("Cocoa"), new Ingredient("Hazelnut Flour"), 0);

        Recipe recipe = new Recipe("Blondie");
        recipe.addIngredient(new Ingredient("Brown Sugar"));
        recipe.addIngredient(new Ingredient("Hazelnut Flour"));

        kb.addRequirements(recipe);

        recipe = new Recipe("Orange Cake");
        recipe.addIngredient(new Ingredient("Brown Sugar"));
        recipe.addIngredient(new Ingredient("Orange"));
        recipe.addIngredient(new Ingredient("Butter"));

        kb.addRequirements(recipe);
    }


}
