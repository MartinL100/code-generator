package org.cbat.codegenerator.service;


import org.cbat.codegenerator.dto.ConfigDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 10:18
 */
public interface CodeGeneratorService {
    String upLoadTemplate(MultipartFile file, ConfigDto dto) throws Exception;

    void downLoadTemplate(InputStream inputStream, HttpServletResponse response)throws Exception ;
}
