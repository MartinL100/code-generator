package org.cbat.codegenerator.handler;

import org.cbat.codegenerator.dto.ConfigDto;
import org.cbat.codegenerator.entity.TableEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/22 15:31
 */
public class TemplatesHandler {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/Dao.java.vm");
        templates.add("template/AppService.java.vm");
        templates.add("template/AppServiceImpl.java.vm");
        templates.add("template/Dto.java.vm");
        templates.add("template/AppServiceTest.java.vm");
        return templates;
    }


    /**
     * 获取文件名
     */
    public static String getFileName(String template, TableEntity tableEntity, ConfigDto configDto) {
        String packagePath = "main" + File.separator + "java" + File.separator+configDto.getModule().getCode()+File.separator;
        String testPackagePath = "test" + File.separator + "java" + File.separator+configDto.getModule().getCode()+File.separator;
        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + tableEntity.getClassNameUp() + ".java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + tableEntity.getClassNameUp()  + "Dao.java";
        }

        if (template.contains("Service.java.vm")&&!template.contains("App")) {
            return packagePath + "service" + File.separator + tableEntity.getClassNameUp()  + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")&&!template.contains("App")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + tableEntity.getClassNameUp()  + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + tableEntity.getClassNameUp()  + "Controller.java";
        }

        if(template.contains("AppService.java.vm")){
            return packagePath + "api" + File.separator + tableEntity.getClassNameUp()  + "AppService.java";
        }

        if(template.contains("AppServiceImpl.java.vm")){
            return packagePath + "appservice" + File.separator + tableEntity.getClassNameUp()  + "AppServiceImpl.java";
        }

        if(template.contains("Dto.java.vm")){
            return packagePath + "dto" + File.separator + tableEntity.getClassNameUp()  + "Dto.java";
        }

        if(template.contains("AppServiceTest.java.vm")){
            return testPackagePath + "appservice" + File.separator + tableEntity.getClassNameUp()  + "AppServiceTest.java";
        }


        return null;
    }
}
