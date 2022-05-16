package com.example.demo.tablemap;

import com.mbextend.SqlTaBle;
import com.mbextend.QColumn;

import java.util.Arrays;
import java.util.List;

public class QProductCategory extends SqlTaBle {
	private static final String tableName = "`product_category`";
	private String tableAlias = "t0";

    public final QColumn id;
	public final QColumn name;
	public final QColumn parentId;

    public QProductCategory() {
        this(null);
    }

	public QProductCategory(String columnPrefix) {
        this.id = new QColumn("`id`",columnPrefix==null?null:columnPrefix+"id");
		this.name = new QColumn("`name`",columnPrefix==null?null:columnPrefix+"name");
		this.parentId = new QColumn("`parent_id`",columnPrefix==null?null:columnPrefix+"parent_id");
        this.id.setTable(this);
		this.name.setTable(this);
		this.parentId.setTable(this);
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<QColumn> getQColumns() {
        return Arrays.asList(id,name,parentId);
    }

    protected void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}