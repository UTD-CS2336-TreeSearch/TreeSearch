package org.CS2336.btree;


public class FlatStruct<E> implements Comparable<FlatStruct> {
    int index;
    E element;
    public FlatStruct(int index, E element) {
        this.index = index;
        this.element = element;
    }

    public int getIndex() {
        return index;
    }

    public E getElement() {
        return element;
    }

    public int compareTo(FlatStruct o) {
        return index - o.index;
    }

    public String toString() {
        return "("+element.toString()+":"+index+")";
    }
}
