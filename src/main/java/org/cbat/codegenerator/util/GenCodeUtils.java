package org.cbat.codegenerator.util;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.cbat.codegenerator.dto.ConfigDto;
import org.cbat.codegenerator.entity.TableEntity;
import org.cbat.codegenerator.enums.ModuleEnum;
import org.cbat.codegenerator.handler.TemplatesHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class GenCodeUtils {

    /**
     * 生成代码
     */
    public static void generatorCode(TableEntity tableEntity, ZipOutputStream zip, ConfigDto configDto) {
        //配置信息
        Configuration config = getConfig();

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        String mainPath = config.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableEntity",tableEntity);
        map.put("configDto",configDto);

        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = TemplatesHandler.getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                String name = TemplatesHandler.getFileName(template, tableEntity,configDto);
                zip.putNextEntry(new ZipEntry(name));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
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



    private static String splitInnerName(String name){
          name = name.replaceAll("\\.","_");
          return name;
    }

public static String cleanPrefix(String source, ModuleEnum moduleEnum){

    String target = StringUtil.IgnoreCaseReplace(source, moduleEnum.getCode()+"_", "");
    target = StringUtil.IgnoreCaseReplace(target, moduleEnum.getCode(), "");
    return target;
}




}
