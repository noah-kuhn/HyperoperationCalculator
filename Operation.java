//NOAH KUHN
//OPERATION CLASS
//NOV 7 2019

import java.math.BigInteger;

/**
 * Operation class
 * Contains three ints: operandOne, operatorRank, operandTwo
 * We also have a class constant, BigInteger INT_MAX, which holds the max value for ints
 * equals() returns true if the two operations' toStrings are the same - one, rank, and two must be equal
 * toString() returns "one[rank]two"
 * STILL NEED TO ADD OPERATE METHOD IF NECESSARY
 */
public class Operation {
    final BigInteger INT_MAX = new BigInteger(Integer.MAX_VALUE + "");
    private BigInteger operandOne;
    private int operatorRank;
    private BigInteger operandTwo;

    public Operation(BigInteger op1, int rank, BigInteger op2) {
        operandOne = op1;
        operatorRank = rank;
        operandTwo = op2;
    }

    public Operation(int op1, int rank, int op2) {
        this(new BigInteger(op1 + ""), rank, new BigInteger(op2 + ""));
    }

    public void setOperandOne(BigInteger op1) {
        operandOne = op1;
    }

    public void setOperatorRank(int rank) {
        operatorRank = rank;
    }

    public void setOperandTwo(BigInteger op2) {
        operandTwo = op2;
    }

    public void setOperandOne(int op1){
        operandOne = new BigInteger(op1 + "");
    }

    public void setOperandTwo(int op2){
        operandTwo = new BigInteger(op2 + "");
    }

    public BigInteger getOperandOne() {
        return operandOne;
    }

    public int getOperatorRank() {
        return operatorRank;
    }

    public BigInteger getOperandTwo() {
        return operandTwo;
    }

    private boolean isUndefined(){
        //we check if either operand is null, our rank is negative, or we're trying to evaluate 0^0
        return operandOne == null || operatorRank < 0 || operandTwo == null || operandOne.equals(BigInteger.ZERO) && operatorRank >= 4 && operandTwo.equals(BigInteger.ZERO);
    }

    public BigInteger operate(){
        //easy cases
        //MAKE SURE IT IS DEFINED
        if(isUndefined()){
            //undefined returns null
            return null;
        }
        //2[n]2 case:
        if(operandOne.equals(BigInteger.ONE.add(BigInteger.ONE)) && operatorRank > 0 && operandTwo.equals(BigInteger.ONE.add(BigInteger.ONE))){
            //2[n]2 = 4 for n > 0
            return new BigInteger("4");
        }
        //operator rank is 0, 1, 2, or 3 case - easy actual operations we regularly think of
        if(operatorRank == 0){
            //n[0]m = n++
            return operandOne.add(BigInteger.ONE);
        }
        if(operatorRank == 1){
            //n[1]m = n+m
            return operandOne.add(operandTwo);
        }
        if(operatorRank == 2){
            //n[2]m = n*m
            return operandOne.multiply(operandTwo);
        }
        if(operatorRank == 3 && operandTwo.compareTo(INT_MAX) < 0){
            //n[3]m = n^m (only easy to do when m is an int)
            return operandOne.pow(operandTwo.intValueExact());
        }
        //repeats operand is 0 or 1 case
        if(operandTwo.equals(BigInteger.ZERO)){
            //n[m]0 = 1 for m > 2
            return BigInteger.ONE;
        }
        if(operandTwo.equals(BigInteger.ONE)){
            //n[m]1 = n for m > 1
            return operandOne;
        }
        //difficult case
        Operation currOp = new Operation(operandOne, operatorRank - 1, operandOne);
        BigInteger repeatsLeft = operandTwo.subtract(BigInteger.ONE); //repeats operandTwo - 1 times
        while(repeatsLeft.compareTo(BigInteger.ONE) > 0){
            repeatsLeft = repeatsLeft.subtract(BigInteger.ONE);
            currOp = new Operation(operandOne, operatorRank - 1, currOp.operate());
        }
        return currOp.operate();
    }

    public boolean equals(Object o) {
        if (o instanceof Operation) {
            Operation other = (Operation) o;
            return other.toString().equals(this.toString());
        }
        return false;
    }

    public String toString() {
        return operandOne + "[" + operatorRank + "]" + operandTwo;
    }
}