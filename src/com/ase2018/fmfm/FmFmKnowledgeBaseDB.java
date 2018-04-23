package com.ase2018.fmfm;

import com.ase2018.generic.Component;
import com.ase2018.generic.KnowledgeBase;
import com.ase2018.generic.Requirement;

import java.sql.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FmFmKnowledgeBaseDB implements KnowledgeBase {
    private HashMap<String, Set<Substitute>> substituteHashMap = new HashMap<>();
    Set<Recipe> allReq = new HashSet<>();


    Connection connection = null;
    Statement stmt = null;


    @Override
    public Set<Set<Component>> getSubstitute(Set<Component> substituee) {
        return null;
    }

    @Override
    public Set<Requirement> getRecommendation(Requirement originalRequirement, Integer k) {
        return null;
    }

    public Set<Recipe> getRecommendation(Set<Ingredient> originalRequirement, Set<Ingredient> available,int threshold) {
        Set<Recipe> recipes = new HashSet<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,name FROM db_recipe\n" +
                    "WHERE id NOT IN (\n" +
                    "  SELECT DISTINCT recipe_id FROM db_recipeingredient\n" +
                    "  where ingredient_id NOT IN (\n" +
                    "    SELECT id FROM db_ingredient WHERE db_ingredient.name IN (\"Brown Sugar\",\"Hazelnut Flour\")\n" +
                    "  )\n" +
                    ")\n" +
                    "LIMIT " + threshold);
            int previousSubID = 0;
            int currentSubID = 0;
            while (rs.next()) {
                String name1 = rs.getString("name");
                //System.out.println("Original = "+name1+" New = "+name2+" Similarity = "+similarity);
                recipes.add(new Recipe(name1));
            }
//            System.out.println("Substitutions\n" + substituteHashMap);
            rs.close();

            Set<Ingredient> ingredients = null;
            for(Recipe rec: recipes){
                stmt = connection.createStatement();
                rs = stmt.executeQuery("SELECT db_ingredient.name AS ingName from db_ingredient\n" +
                        "INNER JOIN db_recipeingredient r ON db_ingredient.id = r.ingredient_id\n" +
                        "INNER JOIN db_recipe recipe ON r.recipe_id = recipe.id\n" +
                        "WHERE recipe.name = \""+rec.name+"\"");
                ingredients = new HashSet<>();
                String name1;
                while (rs.next()) {
                    name1 = rs.getString("ingName");
                    ingredients.add(new Ingredient(name1));
                }
                rec.ingredients.addAll(ingredients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public Set<Recipe> getRecommendation(String recipeName, Set<Ingredient> available, int threshold) {
//        Set<Recipe> recipes = new HashSet<>();
//        for (Recipe rec : allReq) {
//            if (rec.doable(available)) {
//                recipes.add(rec);
//            }
//        }
//        return recipes;


        Set<Recipe> recipes = new HashSet<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d2.name As New, count AS Common FROM db_recipesimilarity\n" +
                    "        INNER JOIN db_recipe d1 ON d1.id = db_recipesimilarity.recipe_1_id\n" +
                    "        INNER JOIN db_recipe d2 ON d2.id = db_recipesimilarity.recipe_2_id\n" +
                    "        INNER JOIN db_recipeingredient ON d2.id = db_recipeingredient.recipe_id\n" +
                    "        INNER JOIN db_ingredient ing ON db_recipeingredient.ingredient_id = ing.id\n" +
                    "        Where d1.name = " + recipeName + " AND db_recipesimilarity.count > 0 AND ing.name IN (\"sugar\",\"egg\",\"banana\")\n" +
                    "        ORDER BY count DESC\n" +
                    "        LIMIT " + threshold);
            int previousSubID = 0;
            int currentSubID = 0;
            while (rs.next()) {
                String name1 = rs.getString("New");
                currentSubID = rs.getInt("Common");
                //System.out.println("Original = "+name1+" New = "+name2+" Similarity = "+similarity);
                recipes.add(new Recipe(name1));
            }
//            System.out.println("Substitutions\n" + substituteHashMap);
            rs.close();

            Set<Ingredient> ingredients = null;
            for(Recipe r: recipes){
                stmt = connection.createStatement();
                rs = stmt.executeQuery("SELECT db_ingredient.name AS ingName from db_ingredient\n" +
                        "INNER JOIN db_recipeingredient r ON db_ingredient.id = r.ingredient_id\n" +
                        "INNER JOIN db_recipe recipe ON r.recipe_id = recipe.id\n" +
                        "WHERE recipe.name = \""+r.name+"\"");
                ingredients = new HashSet<>();
                String name1;
                while (rs.next()) {
                    name1 = rs.getString("ingName");
                    ingredients.add(new Ingredient(name1));
                }
                r.ingredients.addAll(ingredients);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return recipes;

    }


    public Set<Substitute> getSubstitutes(Ingredient i1) {
        return substituteHashMap.get(i1.getName());
    }


    public FmFmKnowledgeBaseDB(String path) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            initSubstitutes();
            System.out.println("Substitutions initialised");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


    }

    public void getIngredientsOfRecipe(String recipeName) {
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select name FROM db_ingredient WHERE id IN " +
                    "(SELECT ingredient_id FROM  db_recipeingredient " +
                    "WHERE recipe_id = (SELECT id FROM db_recipe where name = \"" +
                    recipeName +
                    "\"));");

            while (rs.next()) {
                String name = rs.getString("name");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void initSubstitutes() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT db_ingredientsubstitution.id as SubstituteID, d1.name as Original, d2.name as New, db_ingredientsubstitution.confidence as Similarity\n" +
                    "From db_ingredientsubstitution\n" +
                    "  INNER JOIN db_ingredient d1 ON d1.id=db_ingredientsubstitution.ingredient_id\n" +
                    "  INNER JOIN db_substitution ON db_ingredientsubstitution.id=db_substitution.original_id\n" +
                    "  INNER JOIN db_ingredient d2 ON d2.id=db_substitution.new_ingredient_id\n" +
                    "ORDER BY SubstituteID");
            int previousSubID = 0;
            int currentSubID = 0;
            Substitute previous = null;
            while (rs.next()) {
                String name1 = rs.getString("Original");
                String name2 = rs.getString("New");
                Double similarity = rs.getDouble("Similarity");
                currentSubID = rs.getInt("SubstituteID");
                //System.out.println("Original = "+name1+" New = "+name2+" Similarity = "+similarity);
                Set<Substitute> subs = substituteHashMap.get(name1);
                if (subs == null) {
                    subs = new HashSet<>();
                    substituteHashMap.put(name1, subs);
                    previous = null;
                }
                if (previousSubID != currentSubID) {
                    previousSubID = currentSubID;
                    previous = new Substitute(new Ingredient(name2), similarity);
                    //TODO: check if the substitute does not already exist
                    subs.add(previous);
                } else {
                    previous.addIngredient(new Ingredient(name2));
                }

                //TODO: Is it a commutative relationship

            }
//            System.out.println("Substitutions\n" + substituteHashMap);
            rs.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }

    }


    public void terminate() {
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
