package org.cbat.codegenerator.enums;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 11:06
 */
public enum  ModuleEnum {
    BPM("BPM","bpm","工作流","bpm"),
    UDC("UDC","udc","低代码平台","udc");
    /**前缀大写*/
    private String prefixUp;
    /**前缀小写*/
    private String prefixLow;
    /**模块名称*/
    private String name;
    /**模块编码*/
    private String code;

    ModuleEnum(String prefixUp, String prefixLow, String name, String code) {
        this.prefixUp = prefixUp;
        this.prefixLow = prefixLow;
        this.name = name;
        this.code = code;
    }

    public String getPrefixUp() {
        return prefixUp;
    }

    public void setPrefixUp(String prefixUp) {
        this.prefixUp = prefixUp;
    }

    public String getPrefixLow() {
        return prefixLow;
    }

    public void setPrefixLow(String prefixLow) {
        this.prefixLow = prefixLow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
