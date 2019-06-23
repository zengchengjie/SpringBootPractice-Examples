package com.zcj.demo.testcollection.javapractice;

public class MathPractice {
    public static void main(String[] args) {
        String str0 = "P0(70,215);P1(89,150);P2(128,110);P3(228,63);P4(248,85);P5(482,150);P6(540,276);P7(556,381);P8(479,448);P9(118,461);P10(78,367);P11(40,336);P12(228,246);P13(270,175);P14(192,132);P15(127,258)";
        System.out.println(calculateCoordinates(2.0, str0));
        String str1 = "P0(70,215);P1(89,150);P2(128,110);P3(228,63);P4(248,85);P5(482,150);P6(540,276);P7(556,381);P8(479,448);P9(118,461);P10(78,367);P11(40,336);P12(228,246);P13(270,175);P14(192,132);P15(127,258)";
        System.out.println(calculateCoordinates(16.0, str1));
        String str2 = "P0(70,215);P1(89,150);P2(128,110);P3(228,63);P4(248,85);P5(482,150);P6(540,276);P7(556,381);P8(479,448);P9(118,461);P10(78,367);P11(40,336);P12(228,246);P13(270,175);P14(192,132);P15(127,258)";
        System.out.println(calculateCoordinates(100.0, str2));
        String str3 = "P0(1,1);P1(1,2);P2(2,2);P3(2,1)";
        System.out.println(calculateCoordinates(4.0,str3));
        String str4 = "P0(1,1);P1(1,3);p2(4,4);P3(4,1)";
        System.out.println(calculateCoordinates(5.0,str4));
    }


    public static String calculateCoordinates(Double k, String coordinates) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] coors = coordinates.split(";");
        Double totalLen = 0.0;
        Double[] xArrays = new Double[100];
        Double[] yArrays = new Double[100];
        for (int i = 0; i < coors.length; i++) {
            xArrays[i] = Double.valueOf(coors[i].substring(coors[i].indexOf("(") + 1, coors[i].indexOf(",")));
            yArrays[i] = Double.valueOf(coors[i].substring(coors[i].indexOf(",") + 1, coors[i].indexOf(")")));
        }
        for (int i = 0; i < coors.length; i++) {
            Double sigleLen;
            if (i==coors.length-1){
                sigleLen = Math.sqrt(Math.pow(xArrays[i] - xArrays[0], 2) + Math.pow(yArrays[i] - yArrays[0], 2));
            }else {
                sigleLen = Math.sqrt(Math.pow(xArrays[i] - xArrays[i + 1], 2) + Math.pow(yArrays[i] - yArrays[i + 1], 2));
            }
            totalLen = totalLen + sigleLen;
        }
        Double perLen = totalLen / k;//每段的长度
        Double temLen = 0.0;
        int s = 0;
        for (int i = 0; i < k-1 ; i++) {
            if (perLen > temLen) {
                temLen = temLen + Math.sqrt(Math.pow(xArrays[s] - xArrays[s + 1], 2) + Math.pow(yArrays[s] - yArrays[s + 1], 2));
                s++;
                i--;
            } else {
                Double y = Math.sin(Math.atan(k)) * (temLen - perLen) + yArrays[s];
                Double x = Math.sin(Math.atan(k)) * (temLen - perLen) + xArrays[s];
                stringBuffer.append("D" + i + "(" + x + "," + y + ")");
                yArrays[s] = y;
                xArrays[s] = x;
                temLen = temLen-perLen;
            }
        }
        return stringBuffer.toString();
    }
}
