package com.one.swing.exampleSwing.dropTargetFilePath.exampleCode.po;


/*
由于swing可的下拉框JComboBox不直接支持HTML中的key value对，可以像下面这样初始化：
**/
//  define FileItem
public class FileItem {

    private String key;

    private String value;

    public FileItem() {
        super();
    }

    public FileItem(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}