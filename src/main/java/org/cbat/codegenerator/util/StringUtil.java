package org.cbat.codegenerator.util;

import org.apache.commons.lang.WordUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/19 11:16
 */
public class StringUtil {

    /**
     * 首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
    /**
     * 表名转换成Java类名 去除前缀 表名只能小写
     */
    public static String replacePrefix(String tableName, String[] tablePrefixArray) {
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                if (tableName.startsWith(tablePrefix)){
                    tableName = tableName.replaceFirst(tablePrefix, "");
                }
            }
        }
        return tableName;
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 下划线转烤串命名法
     * @param str
     * @return
     */
    public static String converToSkewer(String str){
        return str.toLowerCase().replace("_","-");
    }

    /**
     * java实现不区分大小写替换
     * @param source  源字符串
     * @param oldString 旧字符串
     * @param newString 新字符串
     * @return
     */
    public static String IgnoreCaseReplace(String source, String oldString,
                                           String newString){
        Pattern p = Pattern.compile(oldString, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(source);
        String ret = m.replaceAll(newString);
        return ret;
    }
}
