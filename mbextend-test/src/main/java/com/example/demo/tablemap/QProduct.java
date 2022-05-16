package com.example.demo.tablemap;

import com.mbextend.SqlTaBle;
import com.mbextend.QColumn;

import java.util.Arrays;
import java.util.List;

public class QProduct extends SqlTaBle {
	private static final String tableName = "`product`";
	private String tableAlias = "t0";

    public final QColumn id;
	public final QColumn name;
	public final QColumn price;
	public final QColumn stock;
	public final QColumn categoryId;
	public final QColumn status;

    public QProduct() {
        this(null);
    }

	public QProduct(String columnPrefix) {
        this.id = new QColumn("`id`",columnPrefix==null?null:columnPrefix+"id");
		this.name = new QColumn("`name`",columnPrefix==null?null:columnPrefix+"name");
		this.price = new QColumn("`price`",columnPrefix==null?null:columnPrefix+"price");
		this.stock = new QColumn("`stock`",columnPrefix==null?null:columnPrefix+"stock");
		this.categoryId = new QColumn("`category_id`",columnPrefix==null?null:columnPrefix+"category_id");
		this.status = new QColumn("`status`",columnPrefix==null?null:columnPrefix+"status");
        this.id.setTable(this);
		this.name.setTable(this);
		this.price.setTable(this);
		this.stock.setTable(this);
		this.categoryId.setTable(this);
		this.status.setTable(this);
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<QColumn> getQColumns() {
        return Arrays.asList(id,name,price,stock,categoryId,status);
    }

    protected void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}