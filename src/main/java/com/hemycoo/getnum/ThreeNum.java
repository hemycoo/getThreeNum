package com.hemycoo.getnum;

public class ThreeNum {
    int a;
    int b;
    int C;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    @Override
    public String toString() {
        return '\n' + "{" + a + "," + b + ", " + C + '}';
    }
}
