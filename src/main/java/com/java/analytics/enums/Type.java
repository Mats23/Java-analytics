package com.java.analytics.enums;

import lombok.Getter;

@Getter
public enum Type {
    SALE("003"),
    SALEMAN("001"),
    CLIENT("002");

    private String value;

    Type(String value) {
        this.value = value;
    }

}