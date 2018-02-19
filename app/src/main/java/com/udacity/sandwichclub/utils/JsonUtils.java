package com.udacity.sandwichclub.utils;

import android.content.res.Resources;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/** Utilites related to JSON parsing. */
public class JsonUtils {
    /**
     * Parses the given json into a Sandwich object
     *
     * @param json to be parsed
     * @return an object of Sandwich class
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {

        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_AKA = "alsoKnownAs";
        final String SANDWICH_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        JSONObject sandwichJson = new JSONObject(json);
        JSONObject sandwichNameObject = sandwichJson.getJSONObject(SANDWICH_NAME);
        String mainName = sandwichNameObject.getString(SANDWICH_MAIN_NAME);

        JSONArray akaJsonArray = sandwichNameObject.getJSONArray(SANDWICH_AKA);
        ArrayList<String> alsoKnownAs = getArrayList(akaJsonArray);

        String placeOfOrigin = sandwichJson.getString(SANDWICH_ORIGIN);
        String description = sandwichJson.getString(SANDWICH_DESCRIPTION);
        String image = sandwichJson.getString(SANDWICH_IMAGE);

        JSONArray ingredientsJsonArray = sandwichJson.getJSONArray(SANDWICH_INGREDIENTS);
        ArrayList<String> ingredients = getArrayList(ingredientsJsonArray);

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
    /**
     * This method Converts a JSONArray to an ArrayList of Strings
     *
     * @param jsonArray The JSON array to convert
     * @return the converted ArrayList
     * @throws JSONException If JSON data cannot be properly parsed
     */
    private static ArrayList<String> getArrayList(JSONArray jsonArray) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++)
            arrayList.add(jsonArray.getString(i));
        return arrayList;
    }
}
