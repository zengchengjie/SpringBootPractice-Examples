package com.zcj.demo.testcollection.javapractice.map;

/**
 * @Auther: zengchengjie
 * @Date: 2019/5/9 09:35
 * @Description: 自定义一个map
 */
public class MyMap<K,V> {
    private Node<K,V>[] nodes;
    private int size;

    private static class Node<K,V>{
        K key;
        V value;
        Node(K key,V value){
            this.key= key;
            this.value = value;
        }
    }

    //如果数组中找到了该key则覆盖，否则新增一个key value
    public void put(K key,V value){
        if (nodes==null){
            nodes = new Node[10];
        }
        int index = indexofkey(key);
        if (index!=-1){//如果数组中找到了，则覆盖node
            nodes[index].value = value;
        }else {
            nodes[size] = new Node<>(key,value);
            size++;
        }
    }


    //查找key所在的位置，未找到则返回-1
    private int indexofkey(K key){
        for (int index = 0;index<size;index++){
            if (key.equals(this.nodes[index].key)){
                return index;
            }
        }
        return -1;
    }

    //根据key获取对应的value
    public V get(K key){
        int index = indexofkey(key);
        if (index!=1){
            return nodes[index].value;
        }
        return null;
    }

    //获取map的长度
    public int getSize(){
        return size;
    }
}
