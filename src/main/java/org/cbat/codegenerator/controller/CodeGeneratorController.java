package org.cbat.codegenerator.controller;

import org.cbat.codegenerator.dto.ConfigDto;
import org.cbat.codegenerator.service.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

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

    @RequestMapping("/template")
    public void downLoadTemplate(HttpServletResponse response) throws Exception {
        generatorService.downLoadTemplate("static/template.xlsx", response);
    }
    @RequestMapping("/downLoadCode")
    public void downLoadCode(String fileName,HttpServletResponse response) throws Exception {
        generatorService.downLoadTemplate(fileName, response);

    }
}
