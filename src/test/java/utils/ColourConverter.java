package utils;

import java.util.HashMap;
import java.util.Map;

public class ColourConverter
{
    static Map<String, String> colours = new HashMap<>();

    static
    {
        colours.put("Red",    "#FF0000");
        colours.put("Green",  "#00FF00");
        colours.put("Blue",   "#0000FF");
        colours.put("Yellow", "#FFFF00");
        colours.put("Purple", "#800080");
        colours.put("Aqua",   "#00FFFF");
        colours.put("White",  "#FFFFFF");
        colours.put("Black",  "#000000");
    }

    public static String getHex(String key)
    {
        String hex = colours.get(key);
        if (hex == null)
        {
            throw new  IllegalArgumentException("Colour doesn't exist!");
        }
        return colours.get(key);
    }
}