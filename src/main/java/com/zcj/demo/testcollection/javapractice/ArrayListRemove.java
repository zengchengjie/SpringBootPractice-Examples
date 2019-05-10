package com.zcj.demo.testcollection.javapractice;

/**
 * @Auther: zengchengjie
 * @Date: 2019/5/6 10:41
 * @Description:
 */

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListRemove {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        remove(list);

        for (String s : list) {
            System.out.println("element : " + s);
        }
    }

    //错误写法1：ist在迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常
// 解决方法：这里会做迭代器内部修改次数检查，因为上面的remove(Object)方法修改了modCount的值，所以才会报出并发修改异常。
// 要避免这种情况的出现则在使用迭代器迭代时（显示或for-each的隐式）不要使用ArrayList的remove，改为用Iterator的remove即可。
//    public static void remove(ArrayList<String> list) {
//        for (String a:list){
//            if (a.equals("b")){
//                list.remove(a);
//            }
//
//        }
//    }
//错误写法2：还有一个b未删除，仅删除一个b，原因为数组删除时，后一个元素向前移动了；
//解决方法：倒序删除，倒序删除时数组指针不移动
//    public static void remove(ArrayList<String> list) {
//        for (int i=0 ;i<list.size();i++){
//            String s = list.get(i);
//            if (s.equals("b")){
//                list.remove(s);
//            }
//        }
//    }
//以下是正确解法
    public static void remove(ArrayList<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("b"))
                iterator.remove();
        }
    }
}