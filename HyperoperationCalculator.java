//NOAH KUHN
//HYPEROPERATION CALCULATOR
//NOV 5 2019

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class HyperoperationCalculator {
    public static Scanner input;
    public static final BigInteger INT_MAX = new BigInteger(Integer.MAX_VALUE + "");

    public static void main(String[] args) throws IOException {
        System.out.println("NOAH'S HYPEROPERATION CALCULATOR");
        input = new Scanner(System.in);
        String response = "";
        do {
            int baseOperand = askForBase();
            BigInteger repeatsOperand = askForRepeats();
            int operationRank = askForRank();
            BigInteger result = operate(baseOperand, operationRank, repeatsOperand);
            System.out.print(baseOperand + "[" + operationRank + "]" + repeatsOperand.toString() + " = ");
            //nicePrint(result);
            System.out.println(result.toString());
            System.out.print("[OK] to continue or any other key to exit\n>");
            response = input.next().toUpperCase();
        } while (response.equals("OK"));
    }

    private static void nicePrint(BigInteger result) {
        char[] str = result.toString().toCharArray();
        int i = 0;
        for (char c : str) {
            if (i == 1_000_000) {
                System.out.println();
                i = 0;
            }
            System.out.print(c);
            i++;
        }
        System.out.println();
    }

    private static BigInteger operate(int baseOperand, int operationRank, BigInteger repeatsOperand) {
        BigInteger toReturn = new BigInteger(baseOperand + "");
        if (operationRank == 0) {
            return toReturn.add(BigInteger.ONE);
        } else if (operationRank == 1) {
            return toReturn.add(repeatsOperand);
        } else if (operationRank == 2) {
            return toReturn.multiply(repeatsOperand);
        } else if (operationRank == 3 && repeatsOperand.compareTo(INT_MAX) < 0) {
            return toReturn.pow(Integer.parseInt(repeatsOperand.toString()));
        }
        if (repeatsOperand.equals(BigInteger.ONE)) {
            return toReturn;
        }
        return operate(baseOperand, operationRank - 1, operate(baseOperand, operationRank, repeatsOperand.subtract(BigInteger.ONE)));
    }

    private static int askForRank() {
        boolean intelligible = false;
        System.out.println("OPERATION RANK:");
        String response = "";
        do {
            System.out.print(">");
            response = input.next();
            if (response.matches("[0123456789]+")) {
                intelligible = true;
            }
            if (!intelligible) {
                System.out.println("Invalid response. OPERATION RANK must be a positive integer.\nTry again:");
            }
        } while (!intelligible);
        return Integer.parseInt(response);
    }

    private static BigInteger askForRepeats() {
        boolean intelligible = false;
        System.out.println("REPEATS OPERAND:");
        String response = "";
        do {
            System.out.print(">");
            response = input.next();
            if (response.matches("[0123456789]+")) {
                intelligible = true;
            }
            if (!intelligible) {
                System.out.println("Invalid response. REPEATS OPERAND must be a positive integer.\nTry again:");
            }
        } while (!intelligible);
        return new BigInteger(response);
    }

    private static int askForBase() {
        boolean intelligible = false;
        System.out.println("BASE OPERAND:");
        String response = "";
        do {
            System.out.print(">");
            response = input.next();
            if (response.matches("[0123456789]+")) {
                intelligible = true;
            }
            if (!intelligible) {
                System.out.println("Invalid response. BASE OPERAND must be a positive integer.\nTry again:");
            }
        } while (!intelligible);
        return Integer.parseInt(response);
    }

}
