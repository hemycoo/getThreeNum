package com.hemycoo.getnum;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        qiongJu();
//        isEqualsTest();
//        suanFa();
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
//                    System.out.println("a = " + threeNum.getA() + ", b = " + threeNum.getB() + ", c = " + threeNum.getC());
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
            if (i >= newSize -1 ) {
                break;
            }
            ThreeNum currentThreeNum = threeNumList.get(i);

            // 标志位用来判断当前3元集合与剩下的所有3元集合是否有相同的两个及以上的相同元素 0为无 1为有 一直为0 则加入最后的集合
            int flag = 0;

            for (int j = i + 1; j < newSize; j++) {
                newSize = threeNumList.size();
                ThreeNum nextThreeNum = threeNumList.get(j);
                boolean result = isEquals(currentThreeNum, nextThreeNum);

                if (result) {
                    if (trueThreeNumList.contains(currentThreeNum)) {
                        continue;
                    }
                    if (flag == 1) {
                        break;
                    }
//                    System.out.println("符合要求的三元数组为 " + currentThreeNum);
                } else {
                    flag = 1;
                    threeNumList.remove(j);

                    if (falseThreeNumList.contains(currentThreeNum)) {
                        continue;
                    }
                    falseThreeNumList.add(currentThreeNum);
                }
            }

                trueThreeNumList.add(currentThreeNum);
            //比较完毕 flag一直为0 加入最后的集合
//            if (flag == 0) {
//            }
        }
//        System.out.println("符合要求的三元数组总数为 " + trueThreeNumList.size());

        //此处去除第一与第三位数相等的情况
        for (int i = 0; i < trueThreeNumList.size(); i++) {
            for (int j = i + 1; j < trueThreeNumList.size(); ) {
                //若不相等，则删除不符合要求的
                if (!isEquals(trueThreeNumList.get(i), trueThreeNumList.get(j))) {
                    trueThreeNumList.remove(j);
                    continue;
                }
                j++;
            }
        }
        System.out.println("结果为 " + trueThreeNumList);
        System.out.println("数量为 " + trueThreeNumList.size());

        return trueThreeNumList;
    }

    /**
     * 1.1 穷举法-去重 测试 不同行列 都为通过的列
     * 测试元素 1,7,13 1,8,12 1,9,15 1,10,14 2,6,13 2,8,11 2,9,16  2,10,18 3,6,12  3,7,11  3,9,17 3,14,16 4,6,15 4,7,16 4,8,17 4,10,11 5,6,14 5,7,18 5,9,11  5,8,16
     */
    @Test
    public void quChongTest() {
        List<ThreeNum> threeNumList = new ArrayList<>();

        ThreeNum threeNumA = new ThreeNum();
        threeNumA.setA(1);
        threeNumA.setB(7);
        threeNumA.setC(13);
        threeNumList.add(threeNumA);

        ThreeNum threeNumB = new ThreeNum();
        threeNumB.setA(1);
        threeNumB.setB(8);
        threeNumB.setC(12);
        threeNumList.add(threeNumB);

        ThreeNum threeNumC = new ThreeNum();
        threeNumC.setA(1);
        threeNumC.setB(9);
        threeNumC.setC(15);
        threeNumList.add(threeNumC);

        ThreeNum threeNumD = new ThreeNum();
        threeNumD.setA(1);
        threeNumD.setB(10);
        threeNumD.setC(14);
        threeNumList.add(threeNumD);

        ThreeNum threeNumE = new ThreeNum();
        threeNumE.setA(2);
        threeNumE.setB(6);
        threeNumE.setC(13);
        threeNumList.add(threeNumE);

        ThreeNum threeNumF = new ThreeNum();
        threeNumF.setA(2);
        threeNumF.setB(8);
        threeNumF.setC(11);
        threeNumList.add(threeNumF);

        ThreeNum threeNumG = new ThreeNum();
        threeNumG.setA(2);
        threeNumG.setB(9);
        threeNumG.setC(16);
        threeNumList.add(threeNumG);

        ThreeNum threeNumH = new ThreeNum();
        threeNumH.setA(2);
        threeNumH.setB(10);
        threeNumH.setC(18);
        threeNumList.add(threeNumH);

        ThreeNum threeNumI0 = new ThreeNum();
        threeNumI0.setA(3);
        threeNumI0.setB(6);
        threeNumI0.setC(12);
        threeNumList.add(threeNumI0);

        ThreeNum threeNumI = new ThreeNum();
        threeNumI.setA(3);
        threeNumI.setB(7);
        threeNumI.setC(11);
        threeNumList.add(threeNumI);

        ThreeNum threeNumJ = new ThreeNum();
        threeNumJ.setA(3);
        threeNumJ.setB(9);
        threeNumJ.setC(17);
        threeNumList.add(threeNumJ);

        ThreeNum threeNumJ1 = new ThreeNum();
        threeNumJ1.setA(3);
        threeNumJ1.setB(14);
        threeNumJ1.setC(16);
        threeNumList.add(threeNumJ1);

        ThreeNum threeNumK = new ThreeNum();
        threeNumK.setA(4);
        threeNumK.setB(6);
        threeNumK.setC(15);
        threeNumList.add(threeNumK);

        ThreeNum threeNumL = new ThreeNum();
        threeNumL.setA(4);
        threeNumL.setB(7);
        threeNumL.setC(16);
        threeNumList.add(threeNumL);

        ThreeNum threeNumM = new ThreeNum();
        threeNumM.setA(4);
        threeNumM.setB(8);
        threeNumM.setC(17);
        threeNumList.add(threeNumM);

        ThreeNum threeNumM1 = new ThreeNum();
        threeNumM1.setA(4);
        threeNumM1.setB(10);
        threeNumM1.setC(11);
        threeNumList.add(threeNumM1);

        ThreeNum threeNumN = new ThreeNum();
        threeNumN.setA(5);
        threeNumN.setB(6);
        threeNumN.setC(14);
        threeNumList.add(threeNumN);

        ThreeNum threeNumo = new ThreeNum();
        threeNumo.setA(5);
        threeNumo.setB(7);
        threeNumo.setC(18);
        threeNumList.add(threeNumo);

        ThreeNum threeNumP = new ThreeNum();
        threeNumP.setA(5);
        threeNumP.setB(9);
        threeNumP.setC(11);
        threeNumList.add(threeNumP);

        ThreeNum threeNumQ = new ThreeNum();
        threeNumQ.setA(5);
        threeNumQ.setB(8);
        threeNumQ.setC(16);
        threeNumList.add(threeNumQ);

        List<ThreeNum> resultThreeNumList = quChong(threeNumList);
        System.out.println(resultThreeNumList.size());

    }

    /**
     * 1.1.2.1 穷举法-去重 测试 不同行列 有问题的案例
     * 测试元素 1,7,13 1，7，15 1，7，19 1,8,12 1,9,15 1,10,14 2,6,13 2,8,11 2,9,16  2,10,18 3,6,12  3,7,11  3,9,17 3,14,16
     */
    @Test
    public void quChongTest2() {
        List<ThreeNum> threeNumList = new ArrayList<>();

        ThreeNum threeNumA = new ThreeNum();
        threeNumA.setA(1);
        threeNumA.setB(7);
        threeNumA.setC(13);
        threeNumList.add(threeNumA);

        ThreeNum threeNumA1 = new ThreeNum();
        threeNumA1.setA(1);
        threeNumA1.setB(7);
        threeNumA1.setC(15);
        threeNumList.add(threeNumA1);

        ThreeNum threeNumA2 = new ThreeNum();
        threeNumA2.setA(1);
        threeNumA2.setB(7);
        threeNumA2.setC(19);
        threeNumList.add(threeNumA2);

        ThreeNum threeNumB = new ThreeNum();
        threeNumB.setA(1);
        threeNumB.setB(8);
        threeNumB.setC(12);
        threeNumList.add(threeNumB);

        ThreeNum threeNumC = new ThreeNum();
        threeNumC.setA(1);
        threeNumC.setB(9);
        threeNumC.setC(15);
        threeNumList.add(threeNumC);

        ThreeNum threeNumD = new ThreeNum();
        threeNumD.setA(1);
        threeNumD.setB(10);
        threeNumD.setC(14);
        threeNumList.add(threeNumD);

        ThreeNum threeNumE = new ThreeNum();
        threeNumE.setA(2);
        threeNumE.setB(6);
        threeNumE.setC(13);
        threeNumList.add(threeNumE);

        ThreeNum threeNumF = new ThreeNum();
        threeNumF.setA(2);
        threeNumF.setB(8);
        threeNumF.setC(11);
        threeNumList.add(threeNumF);

        ThreeNum threeNumG = new ThreeNum();
        threeNumG.setA(2);
        threeNumG.setB(9);
        threeNumG.setC(16);
        threeNumList.add(threeNumG);

        ThreeNum threeNumH = new ThreeNum();
        threeNumH.setA(2);
        threeNumH.setB(10);
        threeNumH.setC(18);
        threeNumList.add(threeNumH);

        ThreeNum threeNumI0 = new ThreeNum();
        threeNumI0.setA(3);
        threeNumI0.setB(6);
        threeNumI0.setC(12);
        threeNumList.add(threeNumI0);

        ThreeNum threeNumI = new ThreeNum();
        threeNumI.setA(3);
        threeNumI.setB(7);
        threeNumI.setC(11);
        threeNumList.add(threeNumI);

        ThreeNum threeNumJ = new ThreeNum();
        threeNumJ.setA(3);
        threeNumJ.setB(9);
        threeNumJ.setC(17);
        threeNumList.add(threeNumJ);

        ThreeNum threeNumJ1 = new ThreeNum();
        threeNumJ1.setA(3);
        threeNumJ1.setB(14);
        threeNumJ1.setC(16);
        threeNumList.add(threeNumJ1);

        List<ThreeNum> resultThreeNumList = quChong(threeNumList);
        System.out.println(resultThreeNumList.size());

    }

    /**
     * 1.1.2 去除同集合中相同行和列的3元集合 测试通过
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
     * 1.1.2 TEST 去除同集合中相同行和列的3元集合 边界测试
     * 3个数中有两个或3个在相同的行，或者相同的列 则去除
     * 设置的值为
     * 相同行 1，2，3    1，2，8  6，11，12  6，12，13   19，20，5   8，9，10
     * 相同列 7，11，16  3，8，13 5，10，15  10，15，20  2，7，19
     * 相同行 相同列 11，12，13
     * 正常行列 5 ，8， 16
     */
    @Test
    public void quChuSameRowAndLineElementTest() {
        List<ThreeNum> threeNumList = new ArrayList<>();
        ThreeNum threeNumA = new ThreeNum();
        threeNumA.setA(1);
        threeNumA.setB(2);
        threeNumA.setC(3);
        threeNumList.add(threeNumA);

        ThreeNum threeNumB = new ThreeNum();
        threeNumB.setA(1);
        threeNumB.setB(2);
        threeNumB.setC(8);
        threeNumList.add(threeNumB);

        ThreeNum threeNumC = new ThreeNum();
        threeNumC.setA(6);
        threeNumC.setB(11);
        threeNumC.setC(12);
        threeNumList.add(threeNumC);

        ThreeNum threeNumD = new ThreeNum();
        threeNumD.setA(6);
        threeNumD.setB(12);
        threeNumD.setC(13);
        threeNumList.add(threeNumD);

        ThreeNum threeNumE = new ThreeNum();
        threeNumE.setA(5);
        threeNumE.setB(19);
        threeNumE.setC(20);
        threeNumList.add(threeNumE);

        ThreeNum threeNumF = new ThreeNum();
        threeNumF.setA(8);
        threeNumF.setB(9);
        threeNumF.setC(10);
        threeNumList.add(threeNumF);

        ThreeNum threeNumG = new ThreeNum();
        threeNumG.setA(7);
        threeNumG.setB(11);
        threeNumG.setC(16);
        threeNumList.add(threeNumG);

        ThreeNum threeNumH = new ThreeNum();
        threeNumH.setA(3);
        threeNumH.setB(8);
        threeNumH.setC(13);
        threeNumList.add(threeNumH);

        ThreeNum threeNumI = new ThreeNum();
        threeNumI.setA(5);
        threeNumI.setB(10);
        threeNumI.setC(15);
        threeNumList.add(threeNumI);

        ThreeNum threeNumJ = new ThreeNum();
        threeNumJ.setA(10);
        threeNumJ.setB(15);
        threeNumJ.setC(20);
        threeNumList.add(threeNumJ);

        ThreeNum threeNumK = new ThreeNum();
        threeNumK.setA(2);
        threeNumK.setB(7);
        threeNumK.setC(19);
        threeNumList.add(threeNumK);

        ThreeNum threeNumL = new ThreeNum();
        threeNumL.setA(11);
        threeNumL.setB(12);
        threeNumL.setC(13);
        threeNumList.add(threeNumL);

        ThreeNum threeNumM = new ThreeNum();
        threeNumM.setA(5);
        threeNumM.setB(8);
        threeNumM.setC(16);
        threeNumList.add(threeNumM);

        List<ThreeNum> threeNums = quChuSameRowAndLineElement(threeNumList);
        if (threeNums.size() == 1 && threeNums.get(0).getA() == 5 && threeNums.get(0).getB() == 8 && threeNums.get(0).getC() == 16) {
            System.out.println("yes");
        } else {
            System.out.println("false");
        }
    }

    /**
     * 1.1.3 输入列总数，数值 获取行号 待优化为不同的矩阵适用 目前4*5 测试通过
     */
    public static int getRow(int line, int data) {
        int rowNum = (data / line) + 1;
        if (data % line == 0) {
            rowNum = data / line;
        }
        return rowNum;
    }

    /**
     * 1.1.4 获取列总数，数值 获取列号 待优化为不同的矩阵适用 目前4*5 测试通过
     */
    public static int getLine(int line, int data) {
        int lineNum = data % line;
        if (lineNum == 0) {
            lineNum = line;
        }
        return lineNum;
    }

    /**
     * 1.2 穷举法-去重-比较两个对象中的两个属性是否相等 符合要求返回true 不符合返回false 测试通过
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
                flag_a_2 = 1;
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
     * 前两位相同 1，7，13与1，7，19        4，10，13与4，10，17
     * 第一位与第三位相同 2，8，14与2.16.14  3,9,17与3，13，17
     * 第2，3位相同 4，7，16与14，7，16     5，9，11与20，9，11
     * 正确的 5，8，16 与 5，7，18
     */
    @Test
    public void isEqualsTest() {
        ThreeNum a1 = new ThreeNum();
        a1.setA(1);
        a1.setB(7);
        a1.setC(13);
        ThreeNum b1 = new ThreeNum();
        b1.setA(1);
        b1.setB(7);
        b1.setC(19);
        boolean result1 = isEquals(a1, b1);
        System.out.println("1，7，13与1，7，19 期望为false");
        if (result1) {
            System.out.println("1，7，13与1，7，19 true");
        } else {
            System.out.println("1，7，13与1，7，19 false");

        }

        ThreeNum a2 = new ThreeNum();
        a2.setA(4);
        a2.setB(10);
        a2.setC(13);
        ThreeNum b2 = new ThreeNum();
        b2.setA(4);
        b2.setB(10);
        b2.setC(17);
        boolean result2 = isEquals(a2, b2);
        System.out.println("4，10，13与4，10，17 期望为false");
        if (result2) {
            System.out.println("4，10，13与4，10，17 true");
        } else {
            System.out.println("4，10，13与4，10，17 false");

        }

        ThreeNum a3 = new ThreeNum();
        a3.setA(2);
        a3.setB(8);
        a3.setC(14);
        ThreeNum b3 = new ThreeNum();
        b3.setA(2);
        b3.setB(16);
        b3.setC(14);
        boolean result3 = isEquals(a3, b3);
        System.out.println("2，8，14与2.16.14 期望为false");
        if (result3) {
            System.out.println("2，8，14与2.16.14 true");
        } else {
            System.out.println("2，8，14与2.16.14 false");

        }

        ThreeNum a4 = new ThreeNum();
        a4.setA(3);
        a4.setB(9);
        a4.setC(17);
        ThreeNum b4 = new ThreeNum();
        b4.setA(3);
        b4.setB(13);
        b4.setC(17);
        boolean result4 = isEquals(a4, b4);
        System.out.println("3,9,17与3，13，17 期望为false");
        if (result4) {
            System.out.println("3,9,17与3，13，17 true");
        } else {
            System.out.println("3,9,17与3，13，17 false");

        }

        ThreeNum a5 = new ThreeNum();
        a5.setA(4);
        a5.setB(7);
        a5.setC(16);
        ThreeNum b5 = new ThreeNum();
        b5.setA(14);
        b5.setB(7);
        b5.setC(16);
        boolean result5 = isEquals(a5, b5);
        System.out.println("4，7，16与14，7，16 期望为false");
        if (result5) {
            System.out.println("4，7，16与14，7，16 true");
        } else {
            System.out.println("4，7，16与14，7，16 false");

        }

        ThreeNum a6 = new ThreeNum();
        a6.setA(5);
        a6.setB(9);
        a6.setC(11);
        ThreeNum b6 = new ThreeNum();
        b6.setA(20);
        b6.setB(9);
        b6.setC(11);
        boolean result6 = isEquals(a6, b6);
        System.out.println("5，9，11与20，9，11 期望为false");
        if (result6) {
            System.out.println("5，9，11与20，9，11 true");
        } else {
            System.out.println("5，9，11与20，9，11 false");

        }

        ThreeNum a7 = new ThreeNum();
        a7.setA(5);
        a7.setB(8);
        a7.setC(16);
        ThreeNum b7 = new ThreeNum();
        b7.setA(5);
        b7.setB(7);
        b7.setC(18);
        boolean result7 = isEquals(a7, b7);
        System.out.println("5，8，16 与 5，7，18 期望为true");
        if (result7) {
            System.out.println("5，8，16 与 5，7，18 true");
        } else {
            System.out.println("5，8，16 与 5，7，18 false");

        }
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
