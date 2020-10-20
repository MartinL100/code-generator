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
	//表的主键
	private ColumnEntity pk;
	//表的列名(不包含主键)
	private List<ColumnEntity> columns;
	
	//类名(第一个字母大写)，如：sys_user => SysUser
	private String className;
	//类名(第一个字母小写)，如：sys_user => sysUser
	private String classname;
	//烤串命名  sys_user => sys-user
	private String class_name;

	private String dataType;

	private Long entityUid;

	private Long dtoUid;

	public Long getDtoUid() {
		return dtoUid;
	}

	public void setDtoUid(Long dtoUid) {
		this.dtoUid = dtoUid;
	}

	public Long getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(Long entityUid) {
		this.entityUid = entityUid;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ColumnEntity getPk() {
		return pk;
	}

	public void setPk(ColumnEntity pk) {
		this.pk = pk;
	}

	public List<ColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
}
