package org.cbat.codegenerator.controller;

import org.cbat.codegenerator.service.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    public void generatorCode(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        generatorService.generatorCode(file,response);
    }
}
