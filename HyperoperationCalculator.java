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
            nicePrint(result);
            System.out.print("[OK] to continue or any other key to exit\n>");
            response = input.next().toUpperCase();
        } while (response.equals("OK"));
    }

    private static void nicePrint(BigInteger result) {
        for (int i = 0; i < result.toString().length(); i++) {
            if (i != 0 && i % 1_000_000 == 0) {
                System.out.println();
            }
            System.out.print(result.toString().charAt(i));
        }
        System.out.println();
    }

    private static BigInteger operate(int baseOperand, int operationRank, BigInteger repeatsOperand) {
        OperationStack opStack = new OperationStack();
        if(operationRank == 0){
            return new BigInteger((baseOperand + 1) + "");
        }
        for (int i = 1; i <= operationRank; i++) {
            Operation op = new Operation(baseOperand, i, repeatsOperand);
            opStack.push(op);
        }
        return conductOperation(opStack.pop());
/*        if (operationRank == 0) {
            return toReturn.add(BigInteger.ONE);
        } else if (operationRank == 1) {
            return toReturn.add(toRepeat);
        } else if (operationRank == 2) {
            return toReturn.multiply(toRepeat);
        } else if (operationRank == 3 && repeatsOperand.compareTo(INT_MAX) < 0) {
            return toReturn.pow(Integer.parseInt(repeatsOperand.toString()));
        }
        if (repeatsOperand.equals(new BigInteger("1"))) {
            return toReturn;
        }
        return operate(baseOperand, operationRank - 1, operate(baseOperand, operationRank, repeatsOperand.subtract(BigInteger.ONE)));
*/
    }

    private static BigInteger conductOperation(Operation poppedOp) {
        if(poppedOp.getOperatorRank() == 1){
            return new BigInteger((poppedOp.getOperandOne()) + "").add(poppedOp.getOperandTwo());
        }
        BigInteger currOp2 = new BigInteger(poppedOp.getOperandOne() + "");
        for(BigInteger i = BigInteger.ONE; i.compareTo(poppedOp.getOperandTwo()) < 0; i = i.add(BigInteger.ONE)){
            Operation subOp = new Operation(poppedOp.getOperandOne(), poppedOp.getOperatorRank()-1, currOp2);
            currOp2 = conductOperation(subOp);
        }
        return currOp2;
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
