package org.cbat.codegenerator.util.excel;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cbat.codegenerator.entity.ColumnEntity;
import org.cbat.codegenerator.entity.TableEntity;
import org.cbat.codegenerator.util.GenCodeUtils;
import org.cbat.codegenerator.util.StringUtil;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/19 11:12
 */
public class ExcelToClass {

        private static List<ColumnEntity> cols = new ArrayList<>();
        private static TableEntity table = new TableEntity();
        public static void main(String[] args) throws IOException {
            FileOutputStream file = new FileOutputStream("C:\\Users\\martingcong\\Desktop\\temp\\class.zip");
            ZipOutputStream zip = new ZipOutputStream(file);

            GenCodeUtils.generatorCode(excelToTableEntity(),zip);
        }


    public static TableEntity excelToTableEntity() throws IOException {
        Configuration config = getConfig();
        //获取excle文件
        FileInputStream fis = new FileInputStream(
                new File("D:\\data\\temp\\data.xlsx"));
        //获取excle对象 XSSF支持 xlsx
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //获取第一个工作薄
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        //获取所有的行
        Iterator<Row> rowIterator = spreadsheet.iterator();
        //迭代行
        while(rowIterator.hasNext()){
            Boolean isTableName = false;
            Boolean isCnName = false;
            Map<String,String> colData = new HashMap<>();
            //获取每一行
            XSSFRow row=  (XSSFRow)rowIterator.next();
            //获取每一行的所有列
            Iterator<Cell> cellIterator=row.cellIterator();
            //迭代所有列
            while(cellIterator.hasNext()){
                //获取每列数据
                Cell cell=cellIterator.next();
                //判断每列的数据类型，这里用的枚举
                switch (cell.getCellTypeEnum())
                {
                    case NUMERIC:  //数字类型
                        colData.put(cell.getColumnIndex()+"",(int)cell.getNumericCellValue()+"");
                        break;
                    case STRING: //字符串类型

                        if("表名".equals(cell.getStringCellValue())){
                            isTableName = true;
                            break;
                        }
                        if("中文名称".equals(cell.getStringCellValue())){
                            isCnName = true;
                            break;
                        }
                        if (isTableName){
                            table.setTableName(cell.getStringCellValue());
                            table.setClassName(StringUtil.columnToJava(table.getTableName()));
                            table.setClassname(StringUtils.uncapitalize(table.getClassName()));
                            String class_name = StringUtil.converToSkewer(StringUtil.replacePrefix(cell.getStringCellValue(),config.getStringArray("tablePrefix")));
                            table.setClass_name(class_name);
                            isTableName = false;
                            continue;
                        }
                        if (isCnName){
                            table.setComments(cell.getStringCellValue());
                            isCnName = false;
                            continue;
                        }
                        colData.put(cell.getColumnIndex()+"",cell.getStringCellValue());
                        break;
                    default:
                        break;
                }

            }
            if(!colData.isEmpty()&&!"字段".equals(colData.get("0"))){
                ColumnEntity entity = converToJavaClass(colData);
                cols.add(entity);
            }

        }
        table.setColumns(cols);
       return table;
    }


    private static ColumnEntity converToJavaClass(Map<String, String> colData) {
        ColumnEntity entity = new ColumnEntity();
        entity.setAttrName(StringUtil.columnToJava(colData.get("0")));
        entity.setAttrname(StringUtil.toLowerCaseFirstOne(entity.getAttrName()));
        entity.setComments(colData.get("1"));
        entity.setDataType(colData.get("2"));

        //列的数据类型，转换成Java类型
        String attrType = getConfig().getString(entity.getDataType().toLowerCase(), StringUtil.columnToJava(entity.getDataType()));
        entity.setAttrType(attrType);

        entity.setColumnName(colData.get("1"));
        String lenth = colData.get("3");
        if (StringUtils.isNotBlank(lenth)){
            entity.setLenth(Integer.valueOf(colData.get("3")));
        }
        if("VARCHAR".equals(entity.getDataType())){
            entity.setDataType("VARCHAR("+entity.getLenth()+")");
        }
        return entity;
    }


    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();//todo
        }

        return null;
    }


}
