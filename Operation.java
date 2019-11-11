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
    private int operandOne;
    private int operatorRank;
    private BigInteger operandTwo;

    public Operation(int op1, int rank, BigInteger op2) {
        operandOne = op1;
        operatorRank = rank;
        operandTwo = op2;
    }

    public Operation(int op1, int rank, int op2) {
        this(op1, rank, new BigInteger(op2 + ""));
    }

    public Operation() {
        this(0, 0, 0);
    }

    public void setOperandOne(int op1) {
        operandOne = op1;
    }

    public void setOperatorRank(int rank) {
        operatorRank = rank;
    }

    public void setOperandTwo(BigInteger op2) {
        operandTwo = op2;
    }

    public int getOperandOne() {
        return operandOne;
    }

    public int getOperatorRank() {
        return operatorRank;
    }

    public BigInteger getOperandTwo() {
        return operandTwo;
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