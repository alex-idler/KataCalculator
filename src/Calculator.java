import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String input) throws Exception {
        String str = input.replaceAll("\\s","");
        str = str.toUpperCase();
        boolean isRoman = false;
        int first = 0, second = 0, result = 0;
        if (str.matches("\\d+(\\+|\\-|\\/|\\*)\\d+")) {         // если арабские
            String[] arabic = str.split("\\+|\\-|\\/|\\*");     // достаём числа из строки
            first = Integer.parseInt(arabic[0]);
            second = Integer.parseInt(arabic[1]);
        } else if (str.matches("[IVX]+(\\+|\\-|\\/|\\*)[IVX]+")) {  // если римские
            isRoman = true;
            String[] roman = str.split("\\+|\\-|\\/|\\*");
            first = toArabic(roman[0]);
            second = toArabic(roman[1]);
        } else {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (str.contains("+")) {
            result = first + second;
        }

        if (str.contains("*")) {
            result = first * second;
        }

        if (str.contains("-")) {
            if (isRoman && first < second) {
                throw new Exception("В римской системе нет отрицательных чисел.");
            } else {
                result = first - second;
            }
        }

        if (str.contains("/")) {
            if (second == 0) {
                throw new Exception("Деление на ноль недопустимо.");
            } else {
                result = first / second;
            }
        }

        if (isRoman) {
            return toRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    private static int toArabic(String romanNumber) throws Exception {
        String[] romanNumbers = {"","I","II","III","IV","V","VI","VII","VIII","IX","X"};

        for (int i=0; i<romanNumbers.length; i++) {
            if (romanNumbers[i].equals(romanNumber)) {
                return i;
            }
        }
        throw new Exception("Одно из чисел больше 10 либо записано неверно.");
    }

    private static String toRoman(int arabic) {
        String[] romanNumbers = {"","I","II","III","IV","V","VI","VII","VIII","IX","X",
                "XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX",
                "XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX",
                "XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL",
                "XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L",
                "LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX",
                "LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX",
                "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX",
                "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
                "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"};

        return romanNumbers[arabic];
    }
}
