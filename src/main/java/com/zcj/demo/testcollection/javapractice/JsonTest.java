package com.zcj.demo.testcollection.javapractice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: 10062376
 * @Date: 2019/5/30 11:52
 * @Description:
 */
public class JsonTest {

    public static void main(String[] args) {
        String json = "{\"code\":200,\"data\":{\"endRow\":0,\"firstPage\":0,\"programList\":{\"newTemplateNum\":0,\"programTotalNum\":0,\"programUnpublishNum\":0,\"programs\":[],\"templateTotalNum\":0}},\"message\":\"SUCCESS\"}";

        JSONObject jsonObject = JSONObject.parseObject(json);
////        Object object = action1(jsonObject,"test");
////        String str = object.toString();
////        System.out.println(str);
//
//        //需要装载的json对象
        JSONObject objectTest = new JSONObject();
//        Object code = jsonObject.getJSONObject("code");
//        System.out.println(code);
//        if (jsonObject.get("code") instanceof JSONObject) {
//            System.out.println("code 是");
//        } else {
//            System.out.println("code 不是");
//        }
//        Object object = jsonObject.get("data");
//        if (object instanceof JSONObject){
//         System.out.println("yes");
//        }
//        if (jsonObject.get("data") instanceof JSONObject) {
//            System.out.println("data 是");
//        } else {
//            System.out.println("data 不是");
//        }


        testJsonArray(jsonObject, objectTest, "");
//        System.out.println(jsonObject1);
    }

    //另外一种方案，就是结果数据转成一维表格形式
    public static List<JSONObject> action(JSONObject jsonObject, String detailKey) {
        Object detailObj = jsonObject.get(detailKey);
        List<JSONObject> allJSONObjectList = new ArrayList<>();
        if (detailObj != null) {
            JSONObject resultJsonObject, detailObjOne;
            jsonObject.remove(detailKey);
            if (detailObj instanceof JSONArray) {
                JSONArray detailArray = (JSONArray) detailObj;
                for (int i = 0; i < detailArray.size(); i++) {
                    detailObjOne = detailArray.getJSONObject(i);
                    detailObjOne = action1(detailObjOne, detailKey);
                    resultJsonObject = new JSONObject();
                    resultJsonObject.putAll(jsonObject);
                    resultJsonObject.putAll(detailObjOne);
                    allJSONObjectList.add(resultJsonObject);
                }
            } else {
                JSONObject a = (JSONObject) detailObj;
                a = action1(a, detailKey);
                resultJsonObject = new JSONObject();
                resultJsonObject.putAll(jsonObject);
                resultJsonObject.putAll(a);
                allJSONObjectList.add(resultJsonObject);
            }

        } else {
            // 当没有detailKey的时候就认为 本身就是一维的
            allJSONObjectList.add(jsonObject);
        }
        // 现在 allJSONObjectList 所有的对象都是一维的，不存在主从结构
        return allJSONObjectList;
    }

    //把一个json中的key加上指定的前缀
    private static JSONObject action1(JSONObject jsonObject, String detailKey) {
        Iterator iterator = jsonObject.keySet().iterator();
        JSONObject object = new JSONObject();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            object.put(detailKey + "_" + key, jsonObject.get(key));
        }
        return object;
    }

    // 迭代方法
    public static void fun(JSONObject obj, int i, int len, String[] p, List<JSONObject> b) {
        if (i >= len) {
            return;
        } else {
            for (JSONObject jsonObject : action(obj, p[i++])) {
                if (i == len - 1) {
                    b.addAll(action(jsonObject, p[i]));
                } else {
                    fun(jsonObject, i, len, p, b);
                }

            }
        }
    }

    public static void testJsonArray(JSONObject oldJsonObj, JSONObject newJsonObj, String oldKey) {
        //拼接key
        String newKey = oldKey;
        Iterator iterator = oldJsonObj.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Object object = oldJsonObj.get(key);
            if (object instanceof JSONObject) {
                newKey = newKey + "_" + key;
                testJsonArray((JSONObject) object, newJsonObj, newKey);
            }
            newJsonObj.put(newKey, oldJsonObj.get(key));
        }
        System.out.println(newJsonObj);
    }


}
