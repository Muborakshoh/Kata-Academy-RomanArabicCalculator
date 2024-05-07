class Main {
    public static String calc(String input) throws IllegalArgumentException {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("throws Exception");
        }

        String operand1 = parts[0];
        String operand2 = parts[2];
        char operation = parts[1].charAt(0);

        boolean isRoman = operand1.matches("[IVXLCDM]+") && operand2.matches("[IVXLCDM]+");
        boolean isArabic = operand1.matches("\\d+") && operand2.matches("\\d+");

        if (!isRoman && !isArabic) {
            throw new IllegalArgumentException("throws Exception");
        }

        int num1 = isRoman ? romanToInt(operand1) : Integer.parseInt(operand1);
        int num2 = isRoman ? romanToInt(operand2) : Integer.parseInt(operand2);

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("throws Exception");
        }

        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) throw new IllegalArgumentException("throws Exception");
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("throws Exception");
        }

        if (isRoman) {
            if (result < 1) throw new IllegalArgumentException("throws Exception");
            return intToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static int romanToInt(String s) {
        int[] values = {1000, 500, 100, 50, 10, 5, 1};
        char[] numerals = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int result = 0, index = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < numerals.length; j++) {
                if (s.charAt(i) == numerals[j]) {
                    if (i + 1 < s.length() && j > 0 && s.charAt(i + 1) == numerals[j - 1]) {
                        result += values[j - 1] - values[j];
                        i++;
                    } else {
                        result += values[j];
                    }
                    break;
                }
            }
        }
        return result;
    }

    private static String intToRoman(int number) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                roman.append(numerals[i]);
                number -= values[i];
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(calc("III + II"));
            System.out.println(calc("10 - 2"));
            System.out.println(calc("X * II"));
            System.out.println(calc("V / II"));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
