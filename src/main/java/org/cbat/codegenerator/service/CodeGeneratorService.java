package org.cbat.codegenerator.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 10:18
 */
public interface CodeGeneratorService {
    void generatorCode(MultipartFile file,HttpServletResponse response) throws Exception;
}
