package com.atxiaojie.myhashmap.service.impl;

import com.atxiaojie.myhashmap.service.MyMap;

/**
 * @ClassName: MyHashMap
 * @Description: 自定义MyHashMap实现类
 * @author: zhouxiaojie
 * @date: 2021/10/30 22:03
 * @Version: V1.0.0
 */
public class MyHashMap<K,V> implements MyMap<K,V> {

    private Entry<K,V> table[] = null;

    private int size = 0;

    public MyHashMap() {
        table = new Entry[16];
    }

    /**
     * @Description 通过key，进行Hash，index下标数组，当前数组对应的对象Entry
     * 判断当前这个对象是否为空，如果为空直接可以存储这个数据，如果不为空，就时hash冲突，要用链表
     * 然后返回这个数
     * @Author zhouxiaojie
     * @Date 22:11 2021/10/30
     * @Param [k, v]
     * @return V
     **/
    @Override
    public V put(K k, V v) {
        int index = getIndex(k);
        Entry<K, V> kvEntry = table[index];
        if(kvEntry == null){
            //直接插入数据,把K V封装成一个对象Entry<>()
            table[index] = new Entry<>(k,v,hash(k),null);
            size++;
        }else{
            Entry<K,V> entry = findValue(kvEntry, k);
            if(entry != null){
                kvEntry.v = v;
            }else{
                table[index] = new Entry<>(k,v,hash(k),kvEntry);
                size++;
            }
        }
        return table[index].getValue();
    }

    private int hash(K key){
        /*int h;
        return (key == null) ? 0 : Math.abs((h = key.hashCode())) ^ (h >>> 16);*/
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int getIndex(K k){
        int flag;
        return (flag = hash(k) % 16) >= 0 ? flag : -flag;
    }

    /**
     * @Description 通过key进行hash,找到数组的下标index,找到Entry对象判断是否为空，
     * 如果为空，直接返回null,没有找到
     * 如果不为空,判断K与当前对象的K是否相等，如果不想等，则判断next是否为空，
     * 如果不为空，在进行key值比较，直到相等才返回 或者返回空
     * @Author zhouxiaojie
     * @Date 22:37 2021/10/30
     * @Param [k]
     * @return V
     **/
    @Override
    public V get(K k) {
        if(size == 0){
            return null;
        }
        int index = getIndex(k);
        Entry<K,V> entry = findValue(table[index], k);
        return entry == null ? null : entry.getValue();
    }

    /**
     * @Description 利用递归的思想查找链表下一个节点
     * @Author zhouxiaojie
     * @Date 22:58 2021/10/30
     * @Param [entry, k]
     * @return com.atxiaojie.myhashmap.service.impl.MyHashMap<K,V>.Entry<K,V>
     **/
    private Entry<K,V> findValue(Entry<K,V> entry, K k) {
        //判断是否时同一个key,如果是则更新，如果不是同一个key则，用链表
        if(hash(entry.getKey()) == hash(k) && entry.getKey().equals(k)){
            return entry;
        }else{
            if(entry.next != null){
                return findValue(entry.next, k);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K,V> implements MyMap.Entry{

        K k;
        V v;
        int hash;
        Entry<K,V> next;

        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
