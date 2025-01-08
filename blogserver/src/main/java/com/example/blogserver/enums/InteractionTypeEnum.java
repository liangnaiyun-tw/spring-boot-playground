package com.example.blogserver.enums;

import java.util.HashMap;
import java.util.Map;

public enum InteractionTypeEnum {
    LIKE(1, "Like"),
    UNLIKE(2, "Unlike");

    private String value;
    private int key;

    private static Map<Integer, String> map = new HashMap();

    static {
        for (InteractionTypeEnum interactionTypeEnum : InteractionTypeEnum.values()) {
            map.put(interactionTypeEnum.key, interactionTypeEnum.value);
        }
    }

    InteractionTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getInteractionTypeNameByKey(int key) {
        return map.get(key);
    }


}
