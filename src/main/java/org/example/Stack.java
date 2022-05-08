package org.example;

import java.util.Vector;

public class Stack {
    final int MAX;
    int top = -1;
    final Vector<String> a;


    boolean isEmpty() {
        return this.top < 0;
    }

    public Vector<String> getVector() {
        return a;
    }

    Stack(int number) {
        this.MAX = number;
        this.a = new Vector<>(number);
        for(int i=0; i<number;i++){
                this.a.add("");
        }
    }

    void push(String x) {
        if (this.top >= this.MAX - 1) {
            System.out.println("Stack Overflow");
        } else {
            this.top++;
            this.a.setElementAt(x,this.top);
        }
    }
    void setElementAt(int deep){

        this.a.setElementAt("checks",deep);
        if (this.top<deep) this.top = deep;

    }
    String pop() {
        if (this.top < 0) {
            System.out.println("Stack Underflow");
            return "";
        } else {
            return this.a.set(this.top--,"");
        }
    }

    String peek() {
        if (this.top < 0) {
            System.out.println("Stack Underflow");
            return "";
        } else {
            return this.a.get(this.top);
        }
    }

    void print() {
        for (int i = this.top; i > -1; --i) {
            System.out.print(" " + this.a.get(i));
        }

    }
}
