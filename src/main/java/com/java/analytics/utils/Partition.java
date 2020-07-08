package com.java.analytics.utils;

import com.google.common.collect.Lists;

import java.util.List;

public class Partition {



    public static List<List<String>> execute(List<String> stringList) {
        return Lists.partition(stringList,4);
    }

}
