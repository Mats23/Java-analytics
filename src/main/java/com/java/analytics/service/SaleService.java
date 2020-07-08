package com.java.analytics.service;

import java.util.List;
import java.util.Map;

public interface SaleService<T, K, D> {


    List<T> create(List<K> list, List<D> list2);

    Map<K, K>  calculateSale(List<T> list);



}
