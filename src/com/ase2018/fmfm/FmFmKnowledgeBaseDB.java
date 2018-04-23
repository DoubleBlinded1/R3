package com.ase2018.fmfm;

import com.ase2018.generic.Component;
import com.ase2018.generic.KnowledgeBase;
import com.ase2018.generic.Requirement;

import java.sql.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FmFmKnowledgeBaseDB implements KnowledgeBase {
    private static HashMap<String, Set<Substitute>> substituteHashMap = new HashMap<>();


    @Override
    public Set<Set<Component>> getSubstitute(Set<Component> substituee) {
        return null;
    }

    @Override
    public Set<Requirement> getRecommendation(Requirement originalRequirement, Integer k) {
        return null;
    }


    public Set<Substitute> getSubstitutes(Ingredient i1) {
        return substituteHashMap.get(i1.getName());
    }


    public static void main(String args[]) {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
//            connection = DriverManager.getConnection("jdbc:sqlite:resources/simple_with_subs_db.sqlite3");
            connection = DriverManager.getConnection("jdbc:sqlite:resources/1000recipes_with_substitutions_with_similarity.sqlite3");

//            c = DriverManager.getConnection("jdbc:sqlite:resources/full_db.sqlite3");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");


            String recipeName = "Gluten Free Chocolate Brownie";
//            String recipeName = new String("Salmon with Chili-Mango Salsa ");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select name FROM db_ingredient WHERE id IN " +
                    "(SELECT ingredient_id FROM  db_recipeingredient " +
                    "WHERE recipe_id = (SELECT id FROM db_recipe where name = \"" +
                    recipeName +
                    "\"));");

            while (rs.next()) {
                String name = rs.getString("name");


                System.out.println("name = " + name);
            }

            initSubstitutes(connection);
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


    }

    private static void initSubstitutes(Connection connection) {
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
                if (previousSubID != currentSubID){
                    previousSubID = currentSubID;
                    previous = new Substitute(new Ingredient(name2), similarity);
                    //TODO: check if the substitute does not already exist
                    subs.add(previous);
                } else {
                    previous.addIngredient(new Ingredient(name2));
                }

                //TODO: Is it a commutative relationship

            }
            System.out.println("Substitutions\n"+substituteHashMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
