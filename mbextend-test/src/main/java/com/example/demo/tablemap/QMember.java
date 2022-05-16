package com.example.demo.tablemap;

import com.mbextend.SqlTaBle;
import com.mbextend.QColumn;

import java.util.Arrays;
import java.util.List;

public class QMember extends SqlTaBle {
	private static final String tableName = "`member`";
	private String tableAlias = "t0";

    public final QColumn id;
	public final QColumn username;
	public final QColumn phone;
	public final QColumn birthday;
	public final QColumn gender;
	public final QColumn money;
	public final QColumn addressCode;
	public final QColumn addressName;

    public QMember() {
        this(null);
    }

	public QMember(String columnPrefix) {
        this.id = new QColumn("`id`",columnPrefix==null?null:columnPrefix+"id");
		this.username = new QColumn("`username`",columnPrefix==null?null:columnPrefix+"username");
		this.phone = new QColumn("`phone`",columnPrefix==null?null:columnPrefix+"phone");
		this.birthday = new QColumn("`birthday`",columnPrefix==null?null:columnPrefix+"birthday");
		this.gender = new QColumn("`gender`",columnPrefix==null?null:columnPrefix+"gender");
		this.money = new QColumn("`money`",columnPrefix==null?null:columnPrefix+"money");
		this.addressCode = new QColumn("`address_code`",columnPrefix==null?null:columnPrefix+"address_code");
		this.addressName = new QColumn("`address_name`",columnPrefix==null?null:columnPrefix+"address_name");
        this.id.setTable(this);
		this.username.setTable(this);
		this.phone.setTable(this);
		this.birthday.setTable(this);
		this.gender.setTable(this);
		this.money.setTable(this);
		this.addressCode.setTable(this);
		this.addressName.setTable(this);
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<QColumn> getQColumns() {
        return Arrays.asList(id,username,phone,birthday,gender,money,addressCode,addressName);
    }

    protected void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}