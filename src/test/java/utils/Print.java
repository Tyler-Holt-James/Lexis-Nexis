package utils;

import java.util.List;
import java.util.Map;

public class Print {
    public static void print(String s)
    {
        System.out.println(s);
    }

    public static void print(Integer i) {
        System.out.println(i);
    }

    public static void print(Double d) {
        System.out.println(d);
    }

    public static void print(Float f) {
        System.out.println(f);
    }

    public static void printSize(List list) {
        System.out.println(list.size());
    }

    public static void printAtIndex(List list, int index) {
        if (list == null || list.isEmpty())
//          if (list.isEmpty() || list == null )          this will throw an npe
        {
            System.out.println("Error: List Not Found.");
            return;
        }
        try {
            System.out.println(list.get(index));
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Error: Invalid Index: " + ioobe);
        }
    }

    public static void printValue(Map map, String key) {
        System.out.println(map.get(key));
    }

    public static <T> void printList(List<T> list)
    {
        for(T listItem : list)
        {
            System.out.println(listItem);
        }
    }


    public static void printArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.println("index : " + i + " | " +"value : " + array[i]);
            System.out.println();
        }
    }

    public static void printArrayBackwards(int[] array)
    {
        for (int i = array.length - 1; i >= 0; i--)
        {
            System.out.println("index : " + i + " | " +"value : " + array[i]);
            System.out.println();
        }
    }

    public static void prompt(String s)
    {
        System.out.print(s);
    }

    public static void seperator()
    {
        System.out.println("-------------------------------------------------------------------------");
    }
}