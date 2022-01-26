package com.hemycoo.getnum;

import jdk.nashorn.internal.ir.Flags;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        qiongJu();
//        isEqualsTest();
    }

    /**
     * 1 穷举法
     */
    public static void qiongJu() {
        //
        //int[] sumNUm = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] sumNUm = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        List<ThreeNum> threeNumList = new ArrayList<>();
        int sum = 0;
        for (int i : sumNUm) {
            for (int j : sumNUm) {
                for (int k : sumNUm) {
                    ThreeNum threeNum = new ThreeNum();
                    threeNum.setA(i);
                    threeNum.setB(j);
                    threeNum.setC(k);
                    threeNumList.add(threeNum);
                    System.out.println("a = " + threeNum.getA() + ", b = " + threeNum.getB() + ", c = " + threeNum.getC());
                    sum++;
                }
            }
        }
        quChong(threeNumList);
        System.out.println("总数为 " + sum);
    }

    /**
     * 1.1 穷举法-去重
     */
    public static List<ThreeNum> quChong(List<ThreeNum> threeNumList) {
        List<ThreeNum> falseThreeNumList = new ArrayList<>();
        List<ThreeNum> trueThreeNumList = new ArrayList<>();
        int size = threeNumList.size();
        for (int i = 0; i < size; i++) {
            ThreeNum currentThreeNum = threeNumList.get(i);
            for (int j = i + 1; j < size; j++) {
                ThreeNum nextThreeNum = threeNumList.get(j);
                boolean result = isEquals(currentThreeNum, nextThreeNum);
                // fixme 此处明日需改进 比较一轮完毕都不重复才能算一个
                if (result) {
                    trueThreeNumList.add(currentThreeNum);
                    System.out.println("符合要求的三元数组为 " + currentThreeNum);
                } else {
                    falseThreeNumList.add(currentThreeNum);
                }
            }
        }
        System.out.println("符合要求的三元数组总数为 " + trueThreeNumList.size());
        return trueThreeNumList;
    }

    /**
     * 1.2 穷举法-去重-比较两个对象中的两个属性是否相等 符合要求返回true 不符合返回false
     * a.A不等于b中的任何一个元素，同时a.B不等于b中的任何一个元素---符合要求
     * a.A不等于b中的任何一个元素，同时a.C不等于b中的任何一个元素---符合要求
     * a.B不等于b中的任何一个元素，同时a.C不等于b中的任何一个元素---符合要求
     * <p>
     * 在a.A不等于b中任意元素情况下，a.B,a.C中至少一个也不等于
     * 在a.A等于b中任意一个元素情况下，a.B,a.C都不等于
     */
    public static boolean isEquals(ThreeNum a, ThreeNum b) {
        // 1代表a.A与b.A相等 0不等
        int flag_a_1 = 0;
        int flag_a_2 = 0;
        int flag_a_3 = 0;
        //表明a与b中任意元素都不等 若flag_a_4 = 0;
        int flag_a_4 = 1;

        if (a.getA() != b.getA()) {
            if (a.getA() != b.getB()) {
                if (a.getA() != b.getC()) {
                    flag_a_4 = 0;
                } else {
                    flag_a_3 = 1;
                }
            } else {
                flag_a_3 = 1;
            }
        } else {
            flag_a_1 = 1;
        }

        //a与b中任意元素不等
        if (flag_a_4 == 0) {
            boolean a_B_status = a.getB() != b.getA() && a.getB() != b.getB() && a.getB() != b.getC();
            boolean a_C_status = a.getC() != b.getA() && a.getC() != b.getB() && a.getC() != b.getC();
            if (!a_B_status) {
                if (!a_C_status) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            //a与b中有元素相等
            //与b第一个元素相等，a的 b,c元素与b的b，c元素 有一个相等就返回false
            if (flag_a_1 == 1) {
                boolean a_B_status = a.getB() != b.getB() && a.getB() != b.getC();
                boolean a_C_status = a.getC() != b.getB() && a.getC() != b.getC();
                if (!a_B_status) {
                    return false;
                }
                if (!a_C_status) {
                    return false;
                }
            }

            if (flag_a_2 == 1) {
                boolean a_B_status = a.getB() != b.getA() && a.getB() != b.getC();
                boolean a_C_status = a.getC() != b.getA() && a.getC() != b.getC();
                if (!a_B_status) {
                    return false;
                }
                if (!a_C_status) {
                    return false;
                }
            }

            if (flag_a_3 == 1) {
                boolean a_B_status = a.getB() != b.getA() && a.getB() != b.getB();
                boolean a_C_status = a.getC() != b.getA() && a.getC() != b.getB();
                if (!a_B_status) {
                    return false;
                }
                if (!a_C_status) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 1.3 穷举法-去重-比较两个对象中的两个属性是否相等 测试
     */
    public static void isEqualsTest() {
        ThreeNum a = new ThreeNum();
        a.setA(5);
        a.setB(3);
        a.setC(2);
        ThreeNum b = new ThreeNum();
        b.setA(1);
        b.setB(2);
        b.setC(3);
        boolean result = isEquals(a, b);
        System.out.println(result);
    }

    /**
     * 2 算法
     */
    public void suanFa() {
        int[] firstRow = {1, 2, 3, 4, 5};
        int[] secondRow = {6, 7, 8, 9, 10};
        int[] thirdRow = {11, 12, 13, 14, 15};
        int[] fourthRow = {16, 17, 18, 19, 20};
        int[] fifthRow = {21, 22, 23, 24, 25};
        int[] sumNUm = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        for (int i : sumNUm) {
            System.out.println(i);
            int oneRow = i / 6 + 1;
            System.out.println("第一个数所在行为 " + oneRow);
            int oneLine = i % 5;
            if (oneLine == 0) {
                oneLine = 5;
            }
            System.out.println("第一个数列在行为 " + oneLine);
            if (oneRow == 1) {

            }
        }
    }
}
