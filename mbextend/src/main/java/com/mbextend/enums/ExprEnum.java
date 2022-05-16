package com.mbextend.enums;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 17:28
 * 表达式
 */
public enum ExprEnum {
    /** 字符串函数 */
    ASCII("ascii(%s)",1),
    CHAR_LENGTH("char_length(%s)", 1),
    CHARACTER_LENGTH("character_length(%s)", 1),
    CONCAT("concat(${})",0),
    CONCAT_WS("concat_ws(%s,${})",1),
    FIELD("field(%s,${})",1),
    FIND_IN_SET("find_in_set(%s,%s)",2),
    FORMAT("format(%s,%s)",2),
    INSERT("insert(%s,%s,%s,%s)",4),
    LOCATE("locate(%s,%s)",2),
    POSITION("position(%s in %s)",2),
    LPAD("lpad(%s,%s,%s)",3),
    RPAD("rpad(%s,%s,%s)",3),
    MID("mid(%s,%s,%s)",3),
    SUBSTR("substr(%s,%s,%s)",3),
    SUBSTRING("substring(%s,%s,%s)",3),
    SUBSTRING_INDEX("substring_index(%s,%s,%s)",3),
    REPEAT("repeat(%s,%s)",2),
    REPLACE("replace(%s,%s,%s)",3),
    REVERSE("reverse(%s)",1),
    RIGHT("right(%s,%s)",2),
    LEFT("left(%s,%s)",2),
    SPACE("space(%s)",1),
    strcmp("strcmp(%s,%s)",2),
    TRIM("trim(%s)",1),
    RTRIM("rtrim(%s)",1),
    LTRIM("ltrim(%s)",1),
    UCASE("ucase()",1),
    UPPER("upper()",1),
    LCASE("lcase(%s)",1),
    LOWER("lower(%s)",1),
    GROUP_CONCAT("group_concat(%s)",1),
    JSON_ARRAY("json_array(${})",0),
    JSON_OBJECT("json_object(${})",0),

    /** 数学函数 */
    ABS("abs(%s)",1),
    ACOS("acos(%s)",2),
    ASIN("asin(%s)",1),
    ATAN("atan(%s)",1),
    ATAN2("atan2(%s,%s)",2),
    AVG("avg(%s)",1),
    CEIL("ceil(%s)",1),
    CEILING("ceiling(%s)",1),
    COS("cos(%s)",1),
    COT("cot(%s)",1),
    COUNT("count(%s)",1),
    DEGREES("degrees(%s)",1),
    EXP("exp(%s)",1),
    FLOOR("floor(%s)",1),
    GREATEST("greatest(${})",0),
    LEAST("least(${})",0),
    LN("ln(%s)",1),
    LOG("log(%s)",1),
    LOG_BASE("log(%s, %s)",2),
    LOG10("log10(%s)",1),
    LOG2("log2(%s)",1),
    MAX("max(%s)",1),
    MIN("min(%s)",1),
    MOD_FUN("mod(%s,%s)",2),
    PI("pi()",0),
    POW("pow(%s,%s)",2),
    POWER("power(%s,%s)",2),
    RADIANS("radians(%s)",1),
    RAND("rand()",0),
    ROUND("round(%s)",1),
    SIGN("sign(%s)",1),
    SIN("sin(%s)",1),
    SQRT("sqrt(%s)",1),
    SUM("sum(%s)",1),
    TAN("tan(%s)",1),
    TRUNCATE("truncate(%s,%s)",2),
    CONV("conv(p1,p2,p3)",3),
    BIN("bin(%s)",1),


    /** 日期函数 */
    DATE_ADD("date_add(%s,INTERVAL %s %s)",3),
    ADDDATE("adddate(%s,%s)",2),
    ADDTIME("addtime(%s,%s)",2),
    CURDATE("curdate()",0),
    CURRENT_DATE("current_date()",0),
    CURRENT_TIME("current_time()",0),
    CURRENT_TIMESTAMP("current_timestamp()",0),
    CURTIME("curtime()",0),
    DATE("date(%s)",1),
    DATEDIFF("datediff(%s,%s)",2),
    DATE_FORMAT("date_format(%s,%s)",2),
    DATE_SUB("date_sub(%s,INTERVAL %s %s)",3),
    DAY("day(%s)",1),
    DAYNAME("dayname(%s)",1),
    DAYOFMONTH("dayofmonth(%s)",1),
    DAYOFWEEK("dayofweek(%s)",1),
    DAYOFYEAR("dayofyear(%s)",1),
    EXTRACT("extract(%s FROM %s)",2),
    FROM_DAYS("from_days(%s)",1),
    HOUR("hour(%s)",1),
    LAST_DAY("last_day(%s)",1),
    LOCALTIME("localtime()",0),
    LOCALTIMESTAMP("localtimestamp()",0),
    MAKEDATE("MAKEDATE(%s, %s)",2),
    MAKETIME("maketime(%s, %s, %s)",3),
    MICROSECOND("microsecond(%s)",1),
    MINUTE("minute(%s)",1),
    MONTHNAME("monthname(%s)",1),
    MONTH("month(%s)",1),
    NOW("now()",0),
    PERIOD_ADD("period_add(%s, %s)",2),
    PERIOD_DIFF("period_diff(%s, %s)",2),
    QUARTER("quarter(%s)",1),
    SECOND("second(%s)",1),
    SEC_TO_TIME("sec_to_time(%s)",1),
    STR_TO_DATE("str_to_date(%s, %s)",2),
    SUBDATE("subdate(%s,%s)",2),
    SUBTIME("subtime(%s,%s)",2),
    SYSDATE("sysdate()",0),
    TIME("time(%s)",1),
    TIME_FORMAT("time_format(%s,%s)",2),
    TIME_TO_SEC("time_to_sec(%s)",1),
    TIMEDIFF("timediff(%s, %s)",2),
    TIMESTAMP("timestamp(%s, %s)",2),
    TIMESTAMPDIFF("timestampdiff(%s,%s,%s)",3),
    TO_DAYS("to_days(%s)",1),
    WEEK("week(%s)",1),
    WEEKDAY("weekday(%s)",1),
    WEEKOFYEAR("weekofyear(%s)",1),
    YEAR("year(%s)",1),
    YEARWEEK("yearweek(%s,%s)",1),

    /** null判断函数 */
    ISNULL("isnull(%s)",1),
    IFNULL("ifnull(%s,%s)",2),
    IF("if(%s,%s,%s)",3),
    COALESCE("coalesce(%s,${})",1),
    CASE_SWITCH("case ${field} ${} end",0),
    CASE_CONDITION("case ${} end",0),

    /** 算术 */
    ADD("(%s + %s)",2),
    SUB("(%s - %s)",2),
    MUL("(%s * %s)",2),
    DIV("(%s / %s)",2),
    DIV_INT("(%s div %s)",2),
    MOD("(%s mod %s)",2),
    BITAND("(%s & %s)",2),
    BITOR("(%s | %s)",2),
    BITXOR("(%s ^ %s)",2),
    BITNEG("(~%s)",1),
    BITMOVELEFT("(%s << %s)",2),
    BITMOVERIGHT("(%s >> %s)",2),

    /** 窗口函数 */
    RANK("rank() over(${group} ${order})",0),
    LAG("lag(%s,%s,%s) over(${group} ${order})",3),
    ROW_NUMBER("row_number() over(${group} ${order})",0),
    PERCENT_RANK("percent_rank() over(${group} ${order})",0),
    NTILE("ntile(%s) over(${group} ${order})",1),
    NTH_VALUE("nth_value(%s,%s) over(${group} ${order})",2),
    LEAD("lead(%s,%s,%s) over(${group} ${order})",3),
    FIRST_VALUE("first_value(%s) over(${group} ${order})",1),
    LAST_VALUE("last_value(%s) over(${group} ${order})",1),
    DENSE_RANK("dense_rank() over(${group} ${order})",0),
    CUME_DIST("cume_dist() over(${group} ${order})",0);
    // 表达式
    private final String expr;
    // %s的个数
    private final int count;

    public String expr() {
        return expr;
    }

    public int count(){
        return count;
    }

    ExprEnum(String expr, int count) {
        this.expr = expr;
        this.count = count;
    }
}
