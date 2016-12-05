package gersee.db.app;

import java.lang.reflect.Field;

/**
 * Created by Gersee on 21.11.16.
 */
public class GenericHelper {

    /**
     * Gives a toString with key-value pairs for attributes back
     * @param instance Instance we want to get the String values
     * @return String with class name and key-values for attributes
     */
     public static String getToString(Object instance) {
        String descriptionString = "";
        Class<?> c = instance.getClass();
        descriptionString += "Class: " + c.getSimpleName() + " with attributes: ";

        int i = 0;
        for (Field attribute : c.getDeclaredFields()) {
            attribute.setAccessible(true);
            if (i > 0) {
                descriptionString += "; ";
            }
            descriptionString += attribute.getName() + "=";
            try {
                descriptionString += attribute.get(instance).toString();
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
                descriptionString += "ExceptionByAttributeAccess";
            }
            i++;
        }

        return descriptionString;
     }

}
