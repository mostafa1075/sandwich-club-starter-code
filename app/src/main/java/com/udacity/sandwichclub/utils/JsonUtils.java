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

    public static final String SANDWICH_NAME = "name";
    public static final String SANDWICH_MAIN_NAME = "mainName";
    public static final String SANDWICH_AKA = "alsoKnownAs";
    public static final String SANDWICH_ORIGIN = "placeOfOrigin";
    public static final String SANDWICH_DESCRIPTION = "description";
    public static final String SANDWICH_IMAGE = "image";
    public static final String SANDWICH_INGREDIENTS = "ingredients";

    /**
     * Parses the given json into a Sandwich object
     *
     * @param json to be parsed
     * @return an object of Sandwich class
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwichJson = new JSONObject(json);
        JSONObject sandwichNameObject = sandwichJson.getJSONObject(SANDWICH_NAME);
        String mainName = sandwichNameObject.optString(SANDWICH_MAIN_NAME);

        JSONArray akaJsonArray = sandwichNameObject.getJSONArray(SANDWICH_AKA);
        ArrayList<String> alsoKnownAs = getArrayList(akaJsonArray);

        String placeOfOrigin = sandwichJson.optString(SANDWICH_ORIGIN);
        String description = sandwichJson.optString(SANDWICH_DESCRIPTION);
        String image = sandwichJson.optString(SANDWICH_IMAGE);

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
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++)
            arrayList.add(jsonArray.optString(i));
        return arrayList;
    }
}
