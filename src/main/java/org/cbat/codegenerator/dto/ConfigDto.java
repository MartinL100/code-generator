package org.cbat.codegenerator.dto;

import lombok.Getter;
import lombok.Setter;
import org.cbat.codegenerator.enums.ModuleEnum;
import org.cbat.codegenerator.util.DateUtils;

import java.util.Date;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 11:05
 */
@Getter
@Setter
public class ConfigDto {
    /**模块名称*/
    private ModuleEnum module;
    /**类名是否带模块前缀*/
    private Boolean classHasPrefix = Boolean.TRUE;
    /**主路径*/
    private String mainPath = "com.seeyon";
    /**包路径*/
    private String packagePath = "com.seeyon";
    /**作者*/
    private String author = "";
    /**创建时间*/
    private String datetime = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);

}
