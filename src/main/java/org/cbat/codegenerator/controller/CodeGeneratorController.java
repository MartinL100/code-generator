package org.cbat.codegenerator.controller;

import org.cbat.codegenerator.dto.ConfigDto;
import org.cbat.codegenerator.service.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 10:16
 */
@Controller
public class CodeGeneratorController {
    @Autowired
    CodeGeneratorService generatorService;

    /**
     * 生成代码
     */
    @RequestMapping("/code")
    public String generatorCode(@RequestParam("file") MultipartFile file, HttpServletResponse response,ConfigDto dto) throws Exception {
        String fileName  = generatorService.upLoadTemplate(file, dto);
        response.getWriter().write(fileName);
        return null;
    }

    /**
     * 下载模板
     * @param response
     * @throws Exception
     */
    @RequestMapping("/template")
    public void downLoadTemplate(HttpServletResponse response) throws Exception {
        String name = "/static/template.xlsx";
        ClassPathResource classPathResource = new ClassPathResource(name);
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);
        generatorService.downLoadTemplate(classPathResource.getInputStream(), response);
    }

    /**
     * 下载生成代码。下载后删除
     *
     * @param fileName
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downLoadCode")
    public void downLoadCode(String fileName,HttpServletResponse response) throws Exception {
        String path = System.getProperty("user.dir")+File.separator+"temp"+File.separator+fileName;
        File file = new File(path);
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        generatorService.downLoadTemplate(new FileInputStream(file), response);
        file.delete();
    }
}
