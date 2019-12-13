//NOAH KUHN
//OPERATION CLASS
//DEC 13 2019

import java.math.BigInteger;

/**
 * Operation class
 * Contains three values: BigInteger operandOne, int operatorRank, BigInteger operandTwo
 * We also have a class constant, BigInteger INT_MAX, which holds the max value for ints
 * equals() returns true if the two operations' toStrings are the same - this is an easy way of seeing if
 * one, rank, and two are equal and in the same order
 * toString() returns "one[rank]two"
 * Holds a BigStack - stack of BigIntegers - that will be used for operation using the "Pop, Pop, Op, and Push"
 * method.
 */
public class Operation {
    private final BigInteger INT_MAX = new BigInteger(Integer.MAX_VALUE + "");
    private BigInteger operandOne;
    private int operatorRank;
    private BigInteger operandTwo;
    private BigStack preOps;

    public Operation(BigInteger op1, int rank, BigInteger op2) {
        if(op1 == null || rank < 1 || op2 == null){
            throw new IllegalArgumentException("Operations can only be declared with");
        }
        operandOne = op1;
        operatorRank = rank;
        operandTwo = op2;
        preOps = new BigStack();
        for(BigInteger i = BigInteger.ZERO; i.compareTo(operandTwo) < 0; i = i.add(BigInteger.ONE)){
            preOps.push(operandOne); //operandOne is our BASE OPERAND - pushed operandTwo times
        }
    }

    private boolean isUnsupported(){
        //we check if either operand is null, our rank is nonpositive, our repeats is negative, or we're trying
        //to evaluate 0^0
        boolean nullOperand = operandOne == null || operandTwo == null;
        boolean nonpositiveRank = operatorRank <= 0;
        boolean zeroToZero = nullOperand || (operandOne.equals(BigInteger.ZERO) && operatorRank == 3 && operandTwo.equals(BigInteger.ZERO)) || (operandOne.equals(BigInteger.ZERO) && operatorRank >= 4);
        //fun fact: zero is only allowed to be the base number when the operator rank is less than four. Any number
        //greater than or equal to four implies that 0^0 occurs-- UNLESS the repeats operand is one, in which case
        //your answer is zero, and don't use my hyperoperation calculator for that. 0[n>1]1 = 0.
        boolean negativeRepeats = nullOperand || operandTwo.compareTo(BigInteger.ZERO) < 0;
        return nullOperand || nonpositiveRank || zeroToZero || negativeRepeats;
    }

    public BigInteger operate(){
        //easy cases
        if(isUnsupported()){
            //unsupported operations return null - I'm not a magician, my calculator can only do so much
            return null;
        }
        //2[n]2 case:
        if(operandOne.equals(BigInteger.ONE.add(BigInteger.ONE)) && operatorRank > 0 && operandTwo.equals(BigInteger.ONE.add(BigInteger.ONE))){
            //2[n]2 = 4 for n > 0 -- All operations with base and repeats 2 eventually boil down to 2+2.
            //I don't totally know why this is-- but oh my goodness, it's magical, isn't it? math truly is
            //magical
            return new BigInteger("4");
        }
        //operator rank is 1, 2, or 3 case - easy actual operations we regularly think of
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
        //the following code used to be the body of the oldOperate() method
        while(preOps.size() > 1){
            preOps.popPopOpPush(operatorRank - 1);
        }
        return preOps.pop(); //clears preOps and returns final value (the correct value)
    }

    private BigInteger oldOperate(){
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