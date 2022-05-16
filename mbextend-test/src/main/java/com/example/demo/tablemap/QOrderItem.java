package com.example.demo.tablemap;

import com.mbextend.SqlTaBle;
import com.mbextend.QColumn;

import java.util.Arrays;
import java.util.List;

public class QOrderItem extends SqlTaBle {
	private static final String tableName = "`order_item`";
	private String tableAlias = "t0";

    public final QColumn id;
	public final QColumn productId;
	public final QColumn number;
	public final QColumn price;
	public final QColumn orderId;

    public QOrderItem() {
        this(null);
    }

	public QOrderItem(String columnPrefix) {
        this.id = new QColumn("`id`",columnPrefix==null?null:columnPrefix+"id");
		this.productId = new QColumn("`product_id`",columnPrefix==null?null:columnPrefix+"product_id");
		this.number = new QColumn("`number`",columnPrefix==null?null:columnPrefix+"number");
		this.price = new QColumn("`price`",columnPrefix==null?null:columnPrefix+"price");
		this.orderId = new QColumn("`order_id`",columnPrefix==null?null:columnPrefix+"order_id");
        this.id.setTable(this);
		this.productId.setTable(this);
		this.number.setTable(this);
		this.price.setTable(this);
		this.orderId.setTable(this);
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<QColumn> getQColumns() {
        return Arrays.asList(id,productId,number,price,orderId);
    }

    protected void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}