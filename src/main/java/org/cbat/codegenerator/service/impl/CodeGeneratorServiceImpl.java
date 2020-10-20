package org.cbat.codegenerator.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cbat.codegenerator.service.CodeGeneratorService;
import org.cbat.codegenerator.util.GenCodeUtils;
import org.cbat.codegenerator.util.excel.ExcelToClass;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    public void downLoadTemplate(String name, HttpServletResponse response) throws Exception {
        File file = new File(ResourceUtils.getURL("classpath:").getPath()+name);

        if (!file.exists()) {
            throw new Exception(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
    }
}
