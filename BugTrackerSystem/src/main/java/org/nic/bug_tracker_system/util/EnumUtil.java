package org.nic.bug_tracker_system.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumUtil {

    // Generic method to get formatted enum values (works for any enum type)
    public static <E extends Enum<E>> List<String> getFormattedEnums(E[] enumValues) {
        return Stream.of(enumValues)
                     .map(EnumUtil::formatEnum)
                     .collect(Collectors.toList());
    }

    // Formats individual enum values (e.g., BUG_BY_END_USERS -> Bug By End Users)
    public static String formatEnum(Enum<?> enumVal) {
        String formatted = enumVal.toString().toLowerCase().replace("_", " ");
        return capitalizeFirstLetter(formatted);
    }

    // Capitalizes the first letter of each word
    public static String capitalizeFirstLetter(String input) {
        return Stream.of(input.split(" "))
                     .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))  // Capitalize first letter
                     .collect(Collectors.joining(" "));
    }
}
