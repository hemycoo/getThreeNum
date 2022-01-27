package com.hemycoo.getnum;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        qiongJu();
//        isEqualsTest();
        suanFa();
    }

    /**
     * 1 穷举法
     */
    public static void qiongJu() {
        //
        int[] sumNum = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        List<ThreeNum> threeNumList = new ArrayList<>();
        int sum = 0;
        for (int i : sumNum) {
            for (int j : sumNum) {
                for (int k : sumNum) {
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
        List<ThreeNum> threeNumQuChong = quChuSameRowAndLineElement(threeNumList);
        quChong(threeNumQuChong);
        System.out.println("总数为 " + sum);
    }

    /**
     * 1.1 穷举法-去重
     */
    public static List<ThreeNum> quChong(List<ThreeNum> threeNumList) {
        List<ThreeNum> falseThreeNumList = new ArrayList<>();
        List<ThreeNum> trueThreeNumList = new ArrayList<>();



        int size = threeNumList.size();
        int newSize = 999999999;
        for (int i = 0; i < newSize; i++) {
            ThreeNum currentThreeNum = threeNumList.get(i);
            for (int j = i + 1; j < newSize; ) {
                ThreeNum nextThreeNum = threeNumList.get(j);
                boolean result = isEquals(currentThreeNum, nextThreeNum);
                // fixme 此处明日需改进 比较一轮完毕都不重复才能算一个
                if (result) {
                    if (trueThreeNumList.contains(currentThreeNum)) {
                        continue;
                    }
                    trueThreeNumList.add(currentThreeNum);
                    System.out.println("符合要求的三元数组为 " + currentThreeNum);

                    //若当前已经到这里，先跳出当前for循环
                    break;

                } else {
                    threeNumList.remove(j);
                    newSize = threeNumList.size();
                    if (falseThreeNumList.contains(currentThreeNum)) {
                        continue;
                    }
                    falseThreeNumList.add(currentThreeNum);
                }
            }
        }
        System.out.println("符合要求的三元数组总数为 " + trueThreeNumList.size());
        return trueThreeNumList;
    }

    /**
     * 1.1.2 去除同集合中相同行和列的3元集合
     * 3个数中有两个或3个在相同的行，或者相同的列 则去除
     */
    public static List<ThreeNum> quChuSameRowAndLineElement(List<ThreeNum> threeNumList) {
        List<ThreeNum> resultList = new ArrayList<>();
        for (ThreeNum threeNum : threeNumList) {
            int aRow = getRow(5, threeNum.getA());
            int aLine = getLine(5, threeNum.getA());
            int bRow = getRow(5, threeNum.getB());
            int bLine = getLine(5, threeNum.getB());
            int cRow = getRow(5, threeNum.getC());
            int cLine = getLine(5, threeNum.getC());

            if (aRow == bRow || aRow == cRow || bRow == cRow || aLine == bLine || aLine == cLine || bLine == cLine) {
                continue;
            }
            resultList.add(threeNum);
        }
        System.out.println("去重后的集合大小" + resultList.size());
        return resultList;
    }

    /**
     * 1.1.3 输入列总数，数值 获取行号 待优化为不同的矩阵适用 目前4*5
     */
    public static int getRow(int line, int data) {
        int rowNum = data / (line + 1) + 1;
        if (data % line == 0) {
            rowNum = data / line;
        }
        return rowNum;
    }

    /**
     * 1.1.4 获取列总数，数值 获取列号 待优化为不同的矩阵适用 目前4*5
     */
    public static int getLine(int line, int data) {
        int lineNum = data % line;
        if (lineNum == 0) {
            lineNum = line;
        }
        return lineNum;
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
    public static void suanFa() {
        int[] firstRow = {1, 2, 3, 4, 5};
        int[] secondRow = {6, 7, 8, 9, 10};
        int[] thirdRow = {11, 12, 13, 14, 15};
        int[] fourthRow = {16, 17, 18, 19, 20};
        int[] fifthRow = {21, 22, 23, 24, 25};
        int[] sumNUm = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        for (int i : sumNUm) {
            System.out.println(i);
            int oneRow = i / 6 + 1;
            System.out.println(i + " 所在行为 " + oneRow);
            int row = getRow(5, i);
            System.out.println(i + " 所在********行为 " + row);
            int oneLine = i % 5;
            if (oneLine == 0) {
                oneLine = 5;
            }
            System.out.println(i + " 所在列为 " + oneLine);
            System.out.println(i + " 所在********列为 " + getLine(5, i));
            if (oneRow == 1) {

            }
        }
    }
}
