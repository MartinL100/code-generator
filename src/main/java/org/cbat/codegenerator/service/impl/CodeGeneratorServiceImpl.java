package org.cbat.codegenerator.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cbat.codegenerator.dto.ConfigDto;
import org.cbat.codegenerator.entity.TableEntity;
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
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 10:18
 */
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {
    /**
     * 代码生成
     * @param file 模板文件
     * @throws Exception
     */
    public String upLoadTemplate(MultipartFile file, ConfigDto configDto) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new Exception("未选择需上传的日志文件");
        }
        String fileName = System.currentTimeMillis()+".zip";
        String path = ResourceUtils.getURL("classpath:").getPath()+File.separator+fileName;
        File fileTemp = new File(path);
        FileOutputStream outputStream  = new FileOutputStream(fileTemp);
        try {
            ZipOutputStream zip = new ZipOutputStream(outputStream);
            TableEntity tableEntity = ExcelToClass.excelToTableEntity((FileInputStream) file.getInputStream());

            dealConfig(tableEntity,configDto);

            GenCodeUtils.generatorCode(tableEntity,zip,configDto);
        } finally {
//            outputStream.close();
        }

        return fileName;
    }

    /**
     * 处理配置信息
     * @param tableEntity
     * @param configDto
     */
    private void dealConfig(TableEntity tableEntity, ConfigDto configDto) {
        if (!configDto.getClassHasPrefix()){
            tableEntity.setClassNameLow(GenCodeUtils.cleanPrefix(tableEntity.getClassNameLow(),configDto.getModule()));
            tableEntity.setClassNameUp(GenCodeUtils.cleanPrefix(tableEntity.getClassNameUp(),configDto.getModule()));
        }
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
