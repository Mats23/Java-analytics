package com.java.analytics.enums;

import lombok.Getter;

@Getter
public enum TypeEnum {
    SALE("003"),
    SALEMAN("001"),
    CLIENT("002");

    private String value;

    TypeEnum(String value) {
        this.value = value;
    }

}