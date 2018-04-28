package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichJSONObject, namesJSONObject;
        JSONArray jsonAlsoKnownAs, jsonIngredients;
        String mainName, placeOfOrigin, description, image;
        ArrayList<String> aka = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();

        try {
            sandwichJSONObject = new JSONObject(json);
            namesJSONObject = sandwichJSONObject.getJSONObject("name");
            mainName = namesJSONObject.getString("mainName");
            jsonAlsoKnownAs = namesJSONObject.getJSONArray("alsoKnownAs");
            for(int i = 0;i<jsonAlsoKnownAs.length();i++)
            {
                aka.add(jsonAlsoKnownAs.getString(i));
            }
            placeOfOrigin = sandwichJSONObject.getString("placeOfOrigin");
            description = sandwichJSONObject.getString("description");
            image = sandwichJSONObject.getString("image");
            jsonIngredients = sandwichJSONObject.getJSONArray("ingredients");
            for(int i = 0; i < jsonIngredients.length(); i++)
            {
                ingredients.add(jsonIngredients.getString(i));
            }
        }catch (JSONException e){
            Log.i("JSONException",e.getMessage());
            return null;
        }

        return new Sandwich(mainName, aka, placeOfOrigin, description, image, ingredients);
    }
}
