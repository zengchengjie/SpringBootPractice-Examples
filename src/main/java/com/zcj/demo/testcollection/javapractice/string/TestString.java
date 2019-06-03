package com.zcj.demo.testcollection.javapractice.string;

/**
 * @Auther: zengchengjie
 * @Date: 2019/5/10 14:44
 * @Description:
 */
public class TestString {
    public static void main(String[] args) {
        String str = "123456";
        System.out.println("截取后："+str.substring(0,2));
        System.out.println("原字符串："+str);
        testCharAt();
        testOr();
        testCharEquals();
        testStringToCharArray();
        System.out.println(checkDifferent1("hello"));
        System.out.println(checkDifferent2("hello"));
    }

    /**
     * charAt()函数：获取指定字符串的某一个位置的字符
     */
    public static void testCharAt() {
        String str = "adcdefg";
        char result = str.charAt(3);
        System.out.println("测试charAt:"+result);
    }

    /**
     * 使用亦或来比较值相等
     */
    public static void testOr(){
        String str1 ="a";
        String str2 ="a";
        String str3 ="b";
        System.out.println("测试亦或^");
        System.out.println(str1.charAt(0)^str2.charAt(0));
        System.out.println(str1.charAt(0)^str3.charAt(0));
    }

    /**
     * char值的比较也可以使用==来解决
     */
    public static void testCharEquals(){
        char char1 = 'a';
        char char2 = 'a';
        char char3 = 'b';
        System.out.println("char值的比较：");
        System.out.println(char1==char2);
        System.out.println(char1==char3);
    }

    /**
     * 多数字符串算法题中要讲字符串转化成字符数组
     */
    public static void testStringToCharArray(){
        String str = "hello";
        char [] chars = str.toCharArray();
        System.out.println("打印所有字符：");
        for (char charStr: chars) {
            System.out.println(charStr);
        }
    }

    /**
     * 比较字符串是否有重复字符
     * @param iniString
     * @return
     */
    public static boolean checkDifferent1(String iniString) {
        if(iniString.length()>256)return false;
        char  [] charStr = iniString.toCharArray();
        for(int i=0;i<charStr.length;i++){
            for(int j=0;j<charStr.length;j++){
                if (i!=j){
                    if(charStr[i]==charStr[j]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkDifferent2(String iniString) {
        if(iniString.length()>256)return false;
        for(int i=0;i<iniString.length()-1;i++){
            for (int j=i+1;j<iniString.length();j++)
                    if(iniString.charAt(i)==iniString.charAt(j)){
                        return false;
            }
        }
        return true;
    }
}
