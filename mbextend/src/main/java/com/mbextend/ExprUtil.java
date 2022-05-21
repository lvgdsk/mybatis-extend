package com.mbextend;

import com.mbextend.enums.ExprEnum;
import com.mbextend.enums.SqlOperator;

import java.util.Collections;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/7 17:33
 */
public class ExprUtil {

    /** 存在判断 */
    public static ConditionExpr exists(SqlQuery sqlQuery){
        return SqlExprBuilder.buildConditionExpr(null, SqlOperator.ES,sqlQuery);
    }
    /** 不存在判断 */
    public static ConditionExpr notExists(SqlQuery sqlQuery){
        return SqlExprBuilder.buildConditionExpr(null,SqlOperator.NES,sqlQuery);
    }

    /** 构建常量列 */
    public static ArithFuncExpr literal(Object param){
        return new ArithFuncExpr("${param}", Collections.singletonList(param));
    }

    /** 加法 */
    public static ArithFuncExpr add(Object param1, Object param2){
        return SqlExprBuilder.build(ExprEnum.ADD,param1,param2);
    }
    /** 减法 */
    public static ArithFuncExpr sub(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.SUB,param1,param2);
    }
    /** 乘法 */
    public static ArithFuncExpr mul(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.MUL,param1,param2);
    }
    /** 除法 */
    public static ArithFuncExpr div(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.DIV,param1,param2);
    }
    /** 整除 */
    public static ArithFuncExpr divInt(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.DIV_INT,param1,param2);
    }
    /** 求余 */
    public static ArithFuncExpr modAri(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.MOD,param1,param2);
    }
    /** 按位与 */
    public static ArithFuncExpr bitAnd(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.BITAND,param1,param2);
    }
    /** 按位或 */
    public static ArithFuncExpr bitOr(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.BITOR,param1,param2);
    }
    /** 按位取反 */
    public static ArithFuncExpr bitNeg(Object param){
        return SqlExprBuilder.build(ExprEnum.BITNEG,param);
    }
    /** 按位异或 */
    public static ArithFuncExpr bitXor(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.BITXOR,param1,param2);
    }
    /** 按位左移 */
    public static ArithFuncExpr bitMoveLeft(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.BITMOVELEFT,param1,param2);
    }
    /** 按位右移 */
    public static ArithFuncExpr bitMoveRight(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.BITMOVERIGHT,param1,param2);
    }
    /** 返回字符串的第一个字符的ASCII码 */
    public static ArithFuncExpr ascii(Object param1){
        return SqlExprBuilder.build(ExprEnum.ASCII,param1);
    }
    /** 返回字符串的长度 */
    public static ArithFuncExpr charLength(Object param1){
        return SqlExprBuilder.build(ExprEnum.CHAR_LENGTH,param1);
    }
    /** 与CHAR_LENGTH完全相同 */
    public static ArithFuncExpr characterLength(Object param1){
        return SqlExprBuilder.build(ExprEnum.CHARACTER_LENGTH,param1);
    }
    /** 拼接字符串 */
    public static ArithFuncExpr concat(Object ... params){
        return SqlExprBuilder.build(ExprEnum.CONCAT,params);
    }
    /** 使用间隔字符串p1拼接字符串 */
    public static ArithFuncExpr concatWs(Object ... params){
        return SqlExprBuilder.build(ExprEnum.CONCAT_WS,params);
    }
    /** 返回p1字符串在列表中的位置 */
    public static ArithFuncExpr field(Object ... params){
        return SqlExprBuilder.build(ExprEnum.FIELD,params);
    }
    /** 返回p1字符串在以","号分隔的p2字符串中的位置，从1开始 */
    public static ArithFuncExpr findInSet(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.FIND_IN_SET,param1,param2);
    }
    /** 将p1数字格式化为”#,###.##“，p2指定保留的小数位（四舍五入） */
    public static ArithFuncExpr format(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.FORMAT,param1,param2);
    }
    /** 使用p4字符串替换p1字符串中从p2开始长度为p3的子串，p2为1，则从第1个字符开始 */
    public static ArithFuncExpr insert(Object param1,Object param2,Object param3,Object param4){
        return SqlExprBuilder.build(ExprEnum.INSERT,param1,param2,param3,param4);
    }
    /** p1字符串在p2字符串的开始位置 */
    public static ArithFuncExpr locate(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.LOCATE,param1,param2);
    }
    /** 与LOCATE完全相同 */
    public static ArithFuncExpr position(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.POSITION,param1,param2);
    }
    /** 在p1字符串的前端填充p3，使之长度达到p2 */
    public static ArithFuncExpr lpad(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.LPAD,param1,param2,param3);
    }
    /** 在p1字符串的后端填充p3，使之长度达到p2 */
    public static ArithFuncExpr rpad(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.RPAD,param1,param2,param3);
    }
    /** 从p1字符串的p2开始，长度为p3的子串，p2为1时指的是从第1个字符开始 */
    public static ArithFuncExpr mid(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.MID,param1,param2,param3);
    }
    /** 与mid完全相同 */
    public static ArithFuncExpr subStr(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.SUBSTR,param1,param2,param3);
    }
    /** 与SUBSTR完全相同 */
    public static ArithFuncExpr subString(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.SUBSTRING,param1,param2,param3);
    }
    /** p1为“a,b,c”，p2为“,”，p3为2则返回“a,b”，p3为-2则返回“b,c” */
    public static ArithFuncExpr subStringIndex(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.SUBSTRING_INDEX,param1,param2,param3);
    }
    /** 将p1字符串重复p2次 */
    public static ArithFuncExpr repeat(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.REPEAT,param1,param2);
    }
    /** 将p3字符串替换p1字符串中的p2子串 */
    public static ArithFuncExpr replace(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.REPLACE,param1,param2,param3);
    }
    /** 反转字符串 */
    public static ArithFuncExpr reverse(Object param1){
        return SqlExprBuilder.build(ExprEnum.REVERSE,param1);
    }
    /** 返回p1字符串的后p2个字符 */
    public static ArithFuncExpr right(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.RIGHT,param1,param2);
    }
    /** 返回p1字符串的前p2个字符 */
    public static ArithFuncExpr left(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.LEFT,param1,param2);
    }
    /** 返回p1个空格 */
    public static ArithFuncExpr space(Object param1){
        return SqlExprBuilder.build(ExprEnum.SPACE,param1);
    }
    /** 字符串比较，p1=p2返回0，p1>p2返回1，p1<p2返回-1 */
    public static ArithFuncExpr strcmp(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.strcmp,param1,param2);
    }
    /** 除掉字符串前后空格 */
    public static ArithFuncExpr trim(Object param1){
        return SqlExprBuilder.build(ExprEnum.TRIM,param1);
    }
    /** 除掉字符串后端空格 */
    public static ArithFuncExpr rtrim(Object param1){
        return SqlExprBuilder.build(ExprEnum.RTRIM,param1);
    }
    /** 除掉字符串前端空格 */
    public static ArithFuncExpr ltrim(Object param1){
        return SqlExprBuilder.build(ExprEnum.LTRIM,param1);
    }
    /** 将字符串转换成大写 */
    public static ArithFuncExpr ucase(Object param1){
        return SqlExprBuilder.build(ExprEnum.UCASE,param1);
    }
    /** 将字符串转换成大写 */
    public static ArithFuncExpr upper(Object param1){
        return SqlExprBuilder.build(ExprEnum.UPPER,param1);
    }
    /** 将字符串转换成小写 */
    public static ArithFuncExpr lcase(Object param1){
        return SqlExprBuilder.build(ExprEnum.LCASE,param1);
    }
    /** 将字符串转换成小写 */
    public static ArithFuncExpr lower(Object param1){
        return SqlExprBuilder.build(ExprEnum.LOWER,param1);
    }
    /** 使用逗号作为间隔符连接表字段多行值；聚合函数 */
    public static ArithFuncExpr groupConcat(Object param1){
        return SqlExprBuilder.build(ExprEnum.GROUP_CONCAT, param1);
    }
    /** 将多个参数组合成一个json数组 */
    public static ArithFuncExpr jsonArray(Object ... params){
        return SqlExprBuilder.build(ExprEnum.JSON_ARRAY, params);
    }
    /** 将多个参数组合成一个json对象 */
    public static ArithFuncExpr jsonObject(Object ... params){
        return SqlExprBuilder.build(ExprEnum.JSON_OBJECT, params);
    }
    /** 返回 p1 的绝对值 */
    public static ArithFuncExpr abs(Object param1){
        return SqlExprBuilder.build(ExprEnum.ABS,param1);
    }
    /** 求 p1 的反余弦值（单位为弧度），p1 为一个数值 */
    public static ArithFuncExpr acos(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.ACOS,param1,param2);
    }
    /** 求反正弦值（单位为弧度）*/
    public static ArithFuncExpr asin(Object param1){
        return SqlExprBuilder.build(ExprEnum.ASIN,param1);
    }
    /** 求反正切值（单位为弧度）*/
    public static ArithFuncExpr atan(Object param1){
        return SqlExprBuilder.build(ExprEnum.ATAN,param1);
    }
    /** 求反正切值（单位为弧度）*/
    public static ArithFuncExpr atan2(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.ATAN2,param1,param2);
    }
    /** 平均值*/
    public static ArithFuncExpr avg(Object param1){
        return SqlExprBuilder.build(ExprEnum.AVG,param1);
    }
    /** 返回大于或等于 p1 的最小整数*/
    public static ArithFuncExpr ceil(Object param1){
        return SqlExprBuilder.build(ExprEnum.CEIL,param1);
    }
    /** 返回大于或等于 p1 的最小整数*/
    public static ArithFuncExpr ceiling(Object param1){
        return SqlExprBuilder.build(ExprEnum.CEILING,param1);
    }
    /** 求 p1 的反余弦值（单位为弧度）*/
    public static ArithFuncExpr cos(Object param1){
        return SqlExprBuilder.build(ExprEnum.COS,param1);
    }
    /** 求余切值(参数是弧度)*/
    public static ArithFuncExpr cot(Object param1){
        return SqlExprBuilder.build(ExprEnum.COT,param1);
    }
    /** 计数：count(param1) */
    public static ArithFuncExpr count(Object param1){
        return SqlExprBuilder.build(ExprEnum.COUNT,param1);
    }
    /** 计数：count(*) */
    public static ArithFuncExpr count(){
        return SqlExprBuilder.build(ExprEnum.COUNT_ALL);
    }
    /** 将弧度转换为角度 */
    public static ArithFuncExpr degrees(Object param1){
        return SqlExprBuilder.build(ExprEnum.DEGREES,param1);
    }
    /** 返回 e 的 p1 次方 */
    public static ArithFuncExpr exp(Object param1){
        return SqlExprBuilder.build(ExprEnum.EXP,param1);
    }
    /** 返回小于或等于 p1 的最大整数 */
    public static ArithFuncExpr floor(Object param1){
        return SqlExprBuilder.build(ExprEnum.FLOOR,param1);
    }
    /** 返回列表中的最大值 */
    public static ArithFuncExpr greatest(Object ... params){
        return SqlExprBuilder.build(ExprEnum.GREATEST,params);
    }
    /** 返回列表中的最小值 */
    public static ArithFuncExpr least(Object ... params){
        return SqlExprBuilder.build(ExprEnum.LEAST,params);
    }
    /** 返回p1的自然对数，以 e 为底。*/
    public static ArithFuncExpr ln(Object param1){
        return SqlExprBuilder.build(ExprEnum.LN,param1);
    }
    /** 返回以e为底的p1的对数 */
    public static ArithFuncExpr loge(Object param1){
        return SqlExprBuilder.build(ExprEnum.LOG,param1);
    }
    /** 返回以p1为底的p2的对数 */
    public static ArithFuncExpr log(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.LOG_BASE,param1,param2);
    }
    /** 返回以 10 为底p1的对数*/
    public static ArithFuncExpr log10(Object param1){
        return SqlExprBuilder.build(ExprEnum.LOG10,param1);
    }
    /** 返回以 2 为底p1的对数*/
    public static ArithFuncExpr log2(Object param1){
        return SqlExprBuilder.build(ExprEnum.LOG2,param1);
    }
    /** 返回字段 p1 中的最大值*/
    public static ArithFuncExpr max(Object param1){
        return SqlExprBuilder.build(ExprEnum.MAX,param1);
    }
    /** 返回字段 p1 中的最小值*/
    public static ArithFuncExpr min(Object param1){
        return SqlExprBuilder.build(ExprEnum.MIN,param1);
    }
    /** 返回 p1 除以 p2 以后的余数*/
    public static ArithFuncExpr mod(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.MOD_FUN,param1,param2);
    }
    /** 返回圆周率(3.141593）*/
    public static ArithFuncExpr pi( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.PI);
    }
    /** 返回 p1 的 p2 次方 */
    public static ArithFuncExpr pow(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.POW,param1,param2);
    }
    /** 返回 p1 的 p2 次方 */
    public static ArithFuncExpr power(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.POWER,param1,param2);
    }
    /** 将角度转换为弧度 */
    public static ArithFuncExpr radians(Object param1){
        return SqlExprBuilder.build(ExprEnum.RADIANS,param1);
    }
    /** 返回 0 到 1 的随机数 */
    public static ArithFuncExpr rand( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.RAND);
    }
    /** 返回离 p1 最近的整数 */
    public static ArithFuncExpr round(Object param1){
        return SqlExprBuilder.build(ExprEnum.ROUND,param1);
    }
    /** 返回 p1 的符号，p1 是负数、0、正数分别返回 -1、0 和 1 */
    public static ArithFuncExpr sign(Object param1){
        return SqlExprBuilder.build(ExprEnum.SIGN,param1);
    }
    /** 求反正弦值（单位为弧度），p1 为一个数值*/
    public static ArithFuncExpr sin(Object param1){
        return SqlExprBuilder.build(ExprEnum.SIN,param1);
    }
    /** 返回p1的平方根 */
    public static ArithFuncExpr sqrt(Object param1){
        return SqlExprBuilder.build(ExprEnum.SQRT,param1);
    }
    /** 返回指定字段的总和 */
    public static ArithFuncExpr sum(Object param1){
        return SqlExprBuilder.build(ExprEnum.SUM,param1);
    }
    /** 求反正切值（单位为弧度），p1 为一个数值*/
    public static ArithFuncExpr tan(Object param1){
        return SqlExprBuilder.build(ExprEnum.TAN,param1);
    }
    /** 返回数值 p1 保留到小数点后 p2 位的值（与 ROUND 最大的区别是不会进行四舍五入） */
    public static ArithFuncExpr truncate(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.TRUNCATE,param1,param2);
    }
    /** 将p1数字从p2进制转换成p3进制，CONV(15, 10, 2) 返回 1111 */
    public static ArithFuncExpr conv(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.CONV,param1,param2,param3);
    }
    /** 返回p1的二进制编码 */
    public static ArithFuncExpr bin(Object param1){
        return SqlExprBuilder.build(ExprEnum.BIN,param1);
    }
    /** p1日期加上一个p2间隔时间 , p3为间隔时间单位，类型为TimeField */
    public static ArithFuncExpr dateAdd(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.DATE_ADD,param1,param2,param3);
    }
    /** 计算起始日期 p1 加上 p2 天的日期 */
    public static ArithFuncExpr addDate(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.ADDDATE,param1,param2);
    }
    /** p1日期时间加上p2秒 */
    public static ArithFuncExpr addTime(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.ADDTIME,param1,param2);
    }
    /** 返回当前日期 */
    public static ArithFuncExpr curDate( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.CURDATE);
    }
    /** 返回当前日期 */
    public static ArithFuncExpr currentDate( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.CURRENT_DATE);
    }
    /** 返回当前时间 */
    public static ArithFuncExpr currentTime( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.CURRENT_TIME);
    }
    /** 返回当前日期和时间 */
    public static ArithFuncExpr currentTimestamp( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.CURRENT_TIMESTAMP);
    }
    /** 返回当前时间 */
    public static ArithFuncExpr curTime( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.CURTIME);
    }
    /** 从日期或日期时间表达式中提取日期值 */
    public static ArithFuncExpr date(Object param1){
        return SqlExprBuilder.build(ExprEnum.DATE,param1);
    }
    /** 计算日期 d1->d2 之间相隔的天数	 */
    public static ArithFuncExpr dateDiff(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.DATEDIFF,param1,param2);
    }
    /** 按表达式p2的要求显示日期p1 */
    public static ArithFuncExpr dateFormat(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.DATE_FORMAT,param1,param2);
    }
    /** 函数从p1日期减去p2(数值)和p3(单位TimeField)指定的时间间隔。 */
    public static ArithFuncExpr dateSub(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.DATE_SUB,param1,param2,param3);
    }
    /** 返回日期p1的日期部分 */
    public static ArithFuncExpr day(Object param1){
        return SqlExprBuilder.build(ExprEnum.DAY,param1);
    }
    /** 返回日期p1是星期几，如 Monday,Tuesday */
    public static ArithFuncExpr dayName(Object param1){
        return SqlExprBuilder.build(ExprEnum.DAYNAME,param1);
    }
    /** 计算日期p1是本月的第几天 */
    public static ArithFuncExpr dayOfMonth(Object param1){
        return SqlExprBuilder.build(ExprEnum.DAYOFMONTH,param1);
    }
    /** 日期p1今天是星期几，1 星期日，2 星期一，以此类推 */
    public static ArithFuncExpr dayOfWeek(Object param1){
        return SqlExprBuilder.build(ExprEnum.DAYOFWEEK,param1);
    }
    /** 计算日期p1是本年的第几天 */
    public static ArithFuncExpr dayOfYear(Object param1){
        return SqlExprBuilder.build(ExprEnum.DAYOFYEAR,param1);
    }
    /** 从日期p1中获取指定的值，p2(TimeField)指定返回的值。 */
    public static ArithFuncExpr extract(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.EXTRACT,param1,param2);
    }
    /** 计算从 0000 年 1 月 1 日开始p1天后的日期 */
    public static ArithFuncExpr fromDays(Object param1){
        return SqlExprBuilder.build(ExprEnum.FROM_DAYS,param1);
    }
    /** 返回p1(HH:mm:ss)中的小时值 */
    public static ArithFuncExpr hour(Object param1){
        return SqlExprBuilder.build(ExprEnum.HOUR,param1);
    }
    /** 返回给给定日期的那一月份的最后一天 */
    public static ArithFuncExpr lastDay(Object param1){
        return SqlExprBuilder.build(ExprEnum.LAST_DAY,param1);
    }
    /** 返回当前日期和时间 */
    public static ArithFuncExpr localtime( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.LOCALTIME);
    }
    /** 返回当前日期和时间 */
    public static ArithFuncExpr localtimestamp( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.LOCALTIMESTAMP);
    }
    /** 使用p1(year)和p2(day-of-year)组合一个日期 */
    public static ArithFuncExpr makeDate(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.MAKEDATE,param1,param2);
    }
    /** 组合时间，参数分别为小时、分钟、秒 */
    public static ArithFuncExpr makeTime(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.MAKETIME,param1,param2,param3);
    }
    /** 返回日期参数所对应的微秒数，MICROSECOND("2017-06-20 09:34:00.000023"); 返回 23 */
    public static ArithFuncExpr microsecond(Object param1){
        return SqlExprBuilder.build(ExprEnum.MICROSECOND,param1);
    }
    /** 返回p1(HH:mm:ss)中的分钟值 */
    public static ArithFuncExpr minute(Object param1){
        return SqlExprBuilder.build(ExprEnum.MINUTE,param1);
    }
    /** 返回日期当中的月份名称，如 November */
    public static ArithFuncExpr monthName(Object param1){
        return SqlExprBuilder.build(ExprEnum.MONTHNAME,param1);
    }
    /** 计算日期p1是本月的第几天 */
    public static ArithFuncExpr month(Object param1){
        return SqlExprBuilder.build(ExprEnum.MONTH,param1);
    }
    /** 返回当前日期和时间 */
    public static ArithFuncExpr now( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.NOW);
    }
    /** 为[年月]组合日期添加一个时段，PERIOD_ADD(201703, 5) 返回 201708 */
    public static ArithFuncExpr periodAdd(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.PERIOD_ADD,param1,param2);
    }
    /** 返回两个[年月]日期的月份差值，PERIOD_DIFF(201710, 201703) 返回 7 */
    public static ArithFuncExpr periodDiff(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.PERIOD_DIFF,param1,param2);
    }
    /** 返回日期p1是第几季节，返回 1 到 4 */
    public static ArithFuncExpr quarter(Object param1){
        return SqlExprBuilder.build(ExprEnum.QUARTER,param1);
    }
    /** 返回日期参数所对应的微秒数，MICROSECOND("2017-06-20 09:34:00.000023"); 返回 23 */
    public static ArithFuncExpr second(Object param1){
        return SqlExprBuilder.build(ExprEnum.SECOND,param1);
    }
    /** 将以秒为单位的时间p1转换为HH:mm:ss的格式，SEC_TO_TIME(4320) 返回 01:12:00 */
    public static ArithFuncExpr secToTime(Object param1){
        return SqlExprBuilder.build(ExprEnum.SEC_TO_TIME,param1);
    }
    /** 将p1字符串按p2格式转变为日期 */
    public static ArithFuncExpr strToDate(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.STR_TO_DATE,param1,param2);
    }
    /** 日期 p1 减去 p2 天后的日期 */
    public static ArithFuncExpr subDate(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.SUBDATE,param1,param2);
    }
    /** 时间 p1 减去 p2 秒的时间 */
    public static ArithFuncExpr subTime(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.SUBTIME,param1,param2);
    }
    /** 返回当前日期和时间 */
    public static ArithFuncExpr sysdate( QColumn alias){
        return SqlExprBuilder.build(ExprEnum.SYSDATE);
    }
    /** 将以秒为单位的时间p1转换为HH:mm:ss的格式，SEC_TO_TIME(4320) 返回 01:12:00 */
    public static ArithFuncExpr time(Object param1){
        return SqlExprBuilder.build(ExprEnum.TIME,param1);
    }
    /** 按p2格式显示p1时间 */
    public static ArithFuncExpr timeFormat(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.TIME_FORMAT,param1,param2);
    }
    /** 将时间转换为秒 */
    public static ArithFuncExpr timeToSec(Object param1){
        return SqlExprBuilder.build(ExprEnum.TIME_TO_SEC,param1);
    }
    /** 计算p1时间与p2时间的时间差，p1要不小于p2，否则返回null；TIMEDIFF("13:10:11", "13:10:10") 返回 00:00:01 */
    public static ArithFuncExpr timeDiff(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.TIMEDIFF,param1,param2);
    }
    /** 将p1日期时间加上p2时间，TIMESTAMP('2001-01-11','12:00:00')返回2001-01-11 12:12:12；TIMESTAMP('2001-01-11 12:00:00','2:00:00')返回2001-01-11 14:00:00 */
    public static ArithFuncExpr timestamp(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.TIMESTAMP,param1,param2);
    }
    /** 计算时间差，返回 p3 − p2 的时间差，p1指定时差单位 */
    public static ArithFuncExpr timestampDiff(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.TIMESTAMPDIFF,param1,param2,param3);
    }
    /** 计算p1日期距离 0000 年 1 月 1 日的天数 */
    public static ArithFuncExpr toDays(Object param1){
        return SqlExprBuilder.build(ExprEnum.TO_DAYS,param1);
    }
    /** 日期p1今天是星期几，1 星期日，2 星期一，以此类推 */
    public static ArithFuncExpr week(Object param1){
        return SqlExprBuilder.build(ExprEnum.WEEK,param1);
    }
    /** p1日期是星期几，0 表示星期一，1 表示星期二 */
    public static ArithFuncExpr weekday(Object param1){
        return SqlExprBuilder.build(ExprEnum.WEEKDAY,param1);
    }
    /** 计算p1日期是本年的第几个星期（从1开始；不存在第0周，因为被认为是上一年的最后一周），以周1作为一周的第一天，计算方式：过了几次周1则为第几周 */
    public static ArithFuncExpr weekOfYear(Object param1){
        return SqlExprBuilder.build(ExprEnum.WEEKOFYEAR,param1);
    }
    /** 计算日期p1是本年的第几天 */
    public static ArithFuncExpr year(Object param1){
        return SqlExprBuilder.build(ExprEnum.YEAR,param1);
    }
    /** 返回年份和第几周（从1开始，计算方式：过了几次p2指定的星期则为第几周；不存在第0周，因为被认为是上一年的最后一周），如 YEARWEEK('2022-01-10',1) 返回 202202，p2用于指定以周几作为一周的开始，默认0,即以周日作为一周的第一天 */
    public static ArithFuncExpr yearWeek(Object param1){
        return SqlExprBuilder.build(ExprEnum.YEARWEEK,param1);
    }
    /** 判断表达式是否为 NULL，是返回1，不是返回0 */
    public static ArithFuncExpr isnull(Object param1){
        return SqlExprBuilder.build(ExprEnum.ISNULL,param1);
    }
    /** 如果p1的值不为 NULL，则返回p1，否则返回p2。 */
    public static ArithFuncExpr ifNull(Object param1,Object param2){
        return SqlExprBuilder.build(ExprEnum.IFNULL,param1,param2);
    }
    /** 如果表达式p1成立，返回结果p2；否则，返回结果p3。 */
    public static ArithFuncExpr ifFunc(Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(ExprEnum.IF,param1,param2,param3);
    }
    /** 返回参数中的第一个非空表达式（从左向右） */
    public static ArithFuncExpr coalesce(Object ... params){
        return SqlExprBuilder.build(ExprEnum.COALESCE,params);
    }
    /** case表达式 */
    public static ArithFuncExpr caseSwitch(Object ... params){
        return SqlExprBuilder.build(ExprEnum.CASE_SWITCH,params);
    }
    /** case表达式 */
    public static ArithFuncExpr caseCondition(Object ... params){
        return SqlExprBuilder.build(ExprEnum.CASE_CONDITION,params);
    }

    /** 分组排序计算行级 */
    public static ArithFuncExpr overRank(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.RANK ,partitions,orders);
    }
    /** 关联其它行 */
    public static ArithFuncExpr overLag(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders, QColumn qColumn, Object param1, Object param2){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.LAG ,partitions,orders,qColumn,param1,param2);
    }
    /** 添加行号 */
    public static ArithFuncExpr overRow(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.ROW_NUMBER ,partitions,orders);
    }
    /** 计算（行级/分区总行数） */
    public static ArithFuncExpr overPercent(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.PERCENT_RANK ,partitions,orders);
    }
    /** 平均分配桶号 */
    public static ArithFuncExpr overNTile(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders, Object param){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.NTILE ,partitions,orders,param);
    }
    /** 查询第n大的表达式 */
    public static ArithFuncExpr overNthValue(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders, QColumn qColumn, Object param){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.NTH_VALUE ,partitions,orders,qColumn,param);
    }
    /** 关联其它行 */
    public static ArithFuncExpr overLead(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders, QColumn qColumn, Object param1, Object param2){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.LEAD ,partitions,orders,qColumn,param1,param2);
    }
    /** 设置首行表达式 */
    public static ArithFuncExpr overFirst(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders, QColumn qColumn){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.FIRST_VALUE ,partitions,orders,qColumn);
    }
    /** 设置尾行表达式 */
    public static ArithFuncExpr overLast(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders, QColumn qColumn){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.LAST_VALUE ,partitions,orders,qColumn);
    }
    /** 分组排序计算行级，类似rank，区别是行级是连续的 */
    public static ArithFuncExpr overDenseRank(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.DENSE_RANK ,partitions,orders);
    }
    /** 分组排序计算行级，类似rank，区别是行级是连续的 */
    public static ArithFuncExpr overCumeDist(List<GroupOrderExpr> partitions, List<GroupOrderExpr> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(ExprEnum.CUME_DIST ,partitions,orders);
    }
}
