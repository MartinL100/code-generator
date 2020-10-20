package org.cbat.codegenerator.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cbat.codegenerator.service.CodeGeneratorService;
import org.cbat.codegenerator.util.GenCodeUtils;
import org.cbat.codegenerator.util.excel.ExcelToClass;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 10:18
 */
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    public void generatorCode(MultipartFile file, HttpServletResponse response) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new Exception("未选择需上传的日志文件");
        }
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        ZipOutputStream zip = new ZipOutputStream(response.getOutputStream());
        GenCodeUtils.generatorCode(ExcelToClass.excelToTableEntity(inputStream),zip);
    }
}
