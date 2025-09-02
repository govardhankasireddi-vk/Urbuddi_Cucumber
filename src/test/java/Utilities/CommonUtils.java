package Utilities;

import java.util.Random;

public class CommonUtils {

    static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final int STRING_LENGTH = 5;

    public static String name() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static String num() {
        Random random = new Random();
        int firstDigit = 6 + random.nextInt(4);
        StringBuilder mobileNumber = new StringBuilder();
        mobileNumber.append(firstDigit);
        for (int i = 0; i < 9; i++) {
            mobileNumber.append(random.nextInt(10)); // 0 to 9
        }

        return mobileNumber.toString();
    }

    public static String resolveRandomValue(String value) {
        if (value == null) return null;
        else if (value.contains("RANDOM")) {
            value = value.replace("RANDOM", name());
            return value;
        } else if (value.equalsIgnoreCase("Random_Num")) {
            return num();
        } else if (value.contains("Random_id")) {
            value = value.replace("Random_id", num().substring(1, 5));
            return value;
        } else {
            return value;
        }
    }
}
