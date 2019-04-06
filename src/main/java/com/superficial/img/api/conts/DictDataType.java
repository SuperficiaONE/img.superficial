package com.superficial.img.api.conts;

public enum DictDataType {
    MENU_LEVEL("MENU_LEVEL","菜单等级"),DICT_TYPE("DICT_TYPE","字典类型");

    private String key;

    private String value;

    DictDataType(String key,String value){
        this.key = key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public DictDataType setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DictDataType setValue(String value) {
        this.value = value;
        return this;
    }
}
