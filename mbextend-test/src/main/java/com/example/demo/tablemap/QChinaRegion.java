package com.example.demo.tablemap;

import com.mbextend.SqlTaBle;
import com.mbextend.QColumn;

import java.util.Arrays;
import java.util.List;

public class QChinaRegion extends SqlTaBle {
	private static final String tableName = "`china_region`";
	private String tableAlias = "t0";

    public final QColumn id;
	public final QColumn pid;
	public final QColumn regionName;
	public final QColumn type;
	public final QColumn postCode;
	public final QColumn regionCode;
	public final QColumn longitude;
	public final QColumn latitude;

    public QChinaRegion() {
        this(null);
    }

	public QChinaRegion(String columnPrefix) {
        this.id = new QColumn("`id`",columnPrefix==null?null:columnPrefix+"id");
		this.pid = new QColumn("`pid`",columnPrefix==null?null:columnPrefix+"pid");
		this.regionName = new QColumn("`region_name`",columnPrefix==null?null:columnPrefix+"region_name");
		this.type = new QColumn("`type`",columnPrefix==null?null:columnPrefix+"type");
		this.postCode = new QColumn("`post_code`",columnPrefix==null?null:columnPrefix+"post_code");
		this.regionCode = new QColumn("`region_code`",columnPrefix==null?null:columnPrefix+"region_code");
		this.longitude = new QColumn("`longitude`",columnPrefix==null?null:columnPrefix+"longitude");
		this.latitude = new QColumn("`latitude`",columnPrefix==null?null:columnPrefix+"latitude");
        this.id.setTable(this);
		this.pid.setTable(this);
		this.regionName.setTable(this);
		this.type.setTable(this);
		this.postCode.setTable(this);
		this.regionCode.setTable(this);
		this.longitude.setTable(this);
		this.latitude.setTable(this);
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<QColumn> getQColumns() {
        return Arrays.asList(id,pid,regionName,type,postCode,regionCode,longitude,latitude);
    }

    protected void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }
}