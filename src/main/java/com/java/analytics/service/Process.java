package com.java.analytics.service;


import java.util.List;

public interface Process<T, K> {



    List<T> create(List<K> list);

}
