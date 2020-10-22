package org.cbat.codegenerator.entity;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableEntity {

	public TableEntity() {
		Snowflake snowflake = IdUtil.getSnowflake(1, 1);
		this.entityUid = snowflake.nextId();
		this.dtoUid = snowflake.nextId();
	}

	//表的名称
	private String tableName;
	//表的备注
	private String comments;
	//表的列名(不包含主键)
	private List<ColumnEntity> columns;
	
	//类名(第一个字母大写)，如：sys_user => SysUser
	private String classNameUp;
	//类名(第一个字母小写)，如：sys_user => sysUser
	private String classNameLow;
	//烤串命名  sys_user => sys-user
	private String classNameSkewer;
	//数据类型
	private String dataType;
	//对象ID
	private Long entityUid;
	//DtoID
	private Long dtoUid;
	/**是否包含Decimal*/
	private Boolean hasDecimal = false;
	/**是否包含List*/
	private Boolean hasList = false;

}
