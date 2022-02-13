package com.atxiaojie.myhashmap.service;

public interface MyMap<K,V> {

    V put(K k, V v);
    V get(K k);
    int size();

    interface Entry<K, V>{
        K getKey();
        V getValue();
    }
}
