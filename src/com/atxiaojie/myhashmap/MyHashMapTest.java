package com.atxiaojie.myhashmap;

import com.atxiaojie.myhashmap.service.MyMap;
import com.atxiaojie.myhashmap.service.impl.MyHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyHashMapTest
 * @Description: 手写HashMap测试
 * @author: zhouxiaojie
 * @date: 2021/10/30 21:55
 * @Version: V1.0.0
 */
public class MyHashMapTest {

    public static void main(String[] args) {
        MyMap<String,Object> map = new MyHashMap();
        map.put("name","zxj");
        map.put("刘一", "liuyi");
        map.put("张三", "zhangsan");
        map.put("Monkey", "Monkey");
        map.put("age", 27);
        map.put("age", 28);
        System.out.println(map.size());
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));

        Map<String,Object> map1 = new HashMap<>();
        map1.put("name","zxj");
        map1.put("刘一", "liuyi");
        map1.put("张三", "zhangsan");
        map1.put("Monkey", "Monkey");
        map1.put("age", 27);
        map1.put("age", 28);
        System.out.println(map1.size());
        System.out.println(map1.get("name"));
        System.out.println(map1.get("age"));

    }
}
