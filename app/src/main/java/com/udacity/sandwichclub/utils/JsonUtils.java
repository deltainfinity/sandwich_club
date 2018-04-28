package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(Context context, String json) {
        JSONObject sandwichJSONObject, namesJSONObject;
        JSONArray jsonAlsoKnownAs, jsonIngredients;
        String mainName, placeOfOrigin, description, image;
        ArrayList<String> aka = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();

        try {
            sandwichJSONObject = new JSONObject(json);
            namesJSONObject = sandwichJSONObject.getJSONObject(context
                    .getString(R.string.json_name_array));
            mainName = namesJSONObject.getString(context.getString(R.string.json_main_name));
            jsonAlsoKnownAs = namesJSONObject.getJSONArray(context
                    .getString(R.string.json_also_known_as));
            for(int i = 0;i<jsonAlsoKnownAs.length();i++)
            {
                aka.add(jsonAlsoKnownAs.getString(i));
            }
            placeOfOrigin = sandwichJSONObject.getString(context
                    .getString(R.string.json_place_of_origin));
            description = sandwichJSONObject.getString(context
                    .getString(R.string.json_description));
            image = sandwichJSONObject.getString(context.getString(R.string.json_image));
            jsonIngredients = sandwichJSONObject.getJSONArray(context
                    .getString(R.string.json_ingredients));
            for(int i = 0; i < jsonIngredients.length(); i++)
            {
                ingredients.add(jsonIngredients.getString(i));
            }
        }catch (JSONException e){
            Log.i(context.getString(R.string.json_exception_tag), e.getMessage());
            return null;
        }

        return new Sandwich(mainName, aka, placeOfOrigin, description, image, ingredients);
    }
}
