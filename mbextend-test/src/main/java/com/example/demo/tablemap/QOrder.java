package com.example.demo.tablemap;

import com.mbextend.SqlTaBle;
import com.mbextend.QColumn;

import java.util.Arrays;
import java.util.List;

public class QOrder extends SqlTaBle {
	private static final String tableName = "`order`";
	private String tableAlias = "t0";

    public final QColumn id;
	public final QColumn createTime;
	public final QColumn memberId;
	public final QColumn totalPrice;
	public final QColumn status;
	public final QColumn addressName;
	public final QColumn addressCode;

    public QOrder() {
        this(null);
    }

	public QOrder(String columnPrefix) {
        this.id = new QColumn("`id`",columnPrefix==null?null:columnPrefix+"id");
		this.createTime = new QColumn("`create_time`",columnPrefix==null?null:columnPrefix+"create_time");
		this.memberId = new QColumn("`member_id`",columnPrefix==null?null:columnPrefix+"member_id");
		this.totalPrice = new QColumn("`total_price`",columnPrefix==null?null:columnPrefix+"total_price");
		this.status = new QColumn("`status`",columnPrefix==null?null:columnPrefix+"status");
		this.addressName = new QColumn("`address_name`",columnPrefix==null?null:columnPrefix+"address_name");
		this.addressCode = new QColumn("`address_code`",columnPrefix==null?null:columnPrefix+"address_code");
        this.id.setTable(this);
		this.createTime.setTable(this);
		this.memberId.setTable(this);
		this.totalPrice.setTable(this);
		this.status.setTable(this);
		this.addressName.setTable(this);
		this.addressCode.setTable(this);
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<QColumn> getQColumns() {
        return Arrays.asList(id,createTime,memberId,totalPrice,status,addressName,addressCode);
    }

    protected void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}