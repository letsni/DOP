
import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < DIGITS.length(); i++) {
            char c = DIGITS.charAt(i);
            System.out.println(c + " = " + i);
        }
        System.out.println(convertBase("1010", 2, 10));
        System.out.println(convertBase("10", 10, 2));
        System.out.println(convertBase("zzz", 62, 10));
        System.out.println(convertBase("10000", 10, 64));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\nВведите число для конвертации (или '0' для выхода):");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("0")) {
                    break;
                }

                System.out.println("Введите исходную систему счисления (2-64):");
                int fromBase = Integer.parseInt(scanner.nextLine());

                System.out.println("Введите целевую систему счисления (2-64):");
                int toBase = Integer.parseInt(scanner.nextLine());

                String result = convertBase(input, fromBase, toBase);
                System.out.printf("%s (%d) = %s (%d)%n", input, fromBase, result, toBase);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректное число");
            }
        }
        scanner.close();
        System.out.println("Программа завершена.");
    }

    private static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";
    public static String convertBase(String number, int fromBase, int toBase) {
        if (fromBase < 2 || fromBase > 64 || toBase < 2 || toBase > 64) {
            throw new IllegalArgumentException("Введите систему от 2 до 64");
        }
        //в десятичную
        int decimalValue = toDecimal(number, fromBase);

        //в ту что надо систему
        return fromDecimal(decimalValue, toBase);
    }

    private static int toDecimal(String number, int base) {
        int result = 0;
        //перебираем символы в числе и проверяем в какой они позиции в списке, позиция=значение
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            int digit = DIGITS.indexOf(c);
            if (digit == -1 || digit >= base) {
                throw new IllegalArgumentException("Неверный символ '" + c + "' для системы счисления " + base);
            }
            result = result * base + digit;
        }
        return result;
    }

    private static String fromDecimal(int number, int base) {
        if (number == 0) return "0";
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int remainder = number % base;
            result.insert(0, DIGITS.charAt(remainder));
            number /= base;
        }
        return result.toString();
    }

}