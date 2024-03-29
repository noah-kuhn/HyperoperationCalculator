//NOAH KUHN
//OPERATIONSTACK CLASS (implements Deque)
//DEC 13 2019

import java.math.BigInteger;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A stack of BigIntegers, held in a LinkedList of BigIntegers
 * A lot of UnsupportedOperationExceptions here--
 * We only want to use stack operations (peek, push, pop)
 * I did include a few extras just in case (size, addFirst, removeFirst, getFirst, iterator, isEmpty, clear)
 * We're gonna do a move I call the "Pop, Pop, Op, and Push", in which we pop two elements, operate using
 * them, and push the answer. This is key for hyperoperations.
 * CAN STORE NULL. If poppopoppushing a null value, it will return null, so if any step of the operation
 * process is bad and wrong, the last value will be null-- so operate will once again return null.
 */
public class BigStack implements Deque {
    private LinkedList<BigInteger> container;

    public BigStack(){
        container = new LinkedList<>();
    }

    public void addFirst(Object o) {
        if(o instanceof BigInteger){
            BigInteger toAdd = (BigInteger) o;
            container.addFirst(toAdd);
        }
    }

    public BigInteger removeFirst() {
        BigInteger temp = container.getFirst();
        container.removeFirst();
        return temp;
    }

    public BigInteger getFirst() {
        return container.getFirst();
    }

    public BigInteger peek() {
        return getFirst();
    }

    public void push(Object o) {
        addFirst(o);
    }

    public BigInteger pop() {
        return removeFirst();
    }

    public void clear() {
        container.clear();
    }

    public int size() {
        return container.size();
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public Iterator<BigInteger> iterator() {
        return container.iterator();
    }

    //are you ready for this??? (you're not)
    public void popPopOpPush(int rank) {
        BigInteger rep = pop();
        BigInteger base = pop();
        Operation op = new Operation(base, rank, rep);
        push(op.operate());
    }

    //unsupported operations
    public void addLast(Object o) {
        throw new UnsupportedOperationException("addLast is an unsupported operation for OperationStack");
    }

    public boolean offerFirst(Object o) {
        throw new UnsupportedOperationException("offerFirst is an unsupported operation for OperationStack");
    }

    public boolean offerLast(Object o) {
        throw new UnsupportedOperationException("offerLast is an unsupported operation for OperationStack");
    }

    public Object removeLast() {
        throw new UnsupportedOperationException("removeLast is an unsupported operation for OperationStack");
    }

    public Object pollFirst() {
        throw new UnsupportedOperationException("pollFirst is an unsupported operation for OperationStack");
    }

    public Object pollLast() {
        throw new UnsupportedOperationException("pollLast is an unsupported operation for OperationStack");
    }

    public Object getLast() {
        throw new UnsupportedOperationException("getLast is an unsupported operation for OperationStack");
    }

    public Object peekFirst() {
        throw new UnsupportedOperationException("peekFirst is an unsupported operation for OperationStack");
    }

    public Object peekLast() {
        throw new UnsupportedOperationException("peekLast is an unsupported operation for OperationStack");
    }

    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException("removeFirstOccurrence is an unsupported operation for OperationStack");
    }

    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException("removeLastOccurrence is an unsupported operation for OperationStack");
    }

    public boolean add(Object o) {
        throw new UnsupportedOperationException("add is an unsupported operation for OperationStack");
    }

    public boolean offer(Object o) {
        throw new UnsupportedOperationException("offer is an unsupported operation for OperationStack");
    }

    public Object remove() {
        throw new UnsupportedOperationException("remove is an unsupported operation for OperationStack");
    }

    public Object poll() {
        throw new UnsupportedOperationException("poll is an unsupported operation for OperationStack");
    }

    public Object element() {
        throw new UnsupportedOperationException("element is an unsupported operation for OperationStack");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("remove is an unsupported operation for OperationStack");
    }

    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("addAll is an unsupported operation for OperationStack");
    }

    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("retainAll is an unsupported operation for OperationStack");
    }

    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("removeAll is an unsupported operation for OperationStack");
    }

    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("containsAll is an unsupported operation for OperationStack");
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("contains is an unsupported operation for OperationStack");
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException("toArray is an unsupported operation for OperationStack");
    }

    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("toArray is an unsupported operation for OperationStack");
    }

    public Iterator descendingIterator() {
        throw new UnsupportedOperationException("descendingIterator is an unsupported operation for OperationStack");
    }

}
