package com.example.demo.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

/**
 * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class MybatisPlusCodeGenerator{
    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("src/main/java");
        gc.setAuthor("lvqi");
        // 创建完后是否打开文件夹：OutputDir
        gc.setOpen(false);
        gc.setIdType(IdType.ID_WORKER_STR);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        //是否覆盖已有文件
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.178.128:3306/test?serverTimeZone=Asia/Shanghai");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent("com.example.demo");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 自定义继承的Entity类全称，带包名
        // strategy.setSuperEntityClass(String);
        // 自定义基础的Entity类，公共字段
        // strategy.setSuperEntityColumns(Set<String>);
        // 自定义继承的Mapper类全称，带包名，默认为 mybatis-plus 的 BaseMapper
        // strategy.setSuperMapperClass(Sting)
        // 自定义继承的Service类全称，带包名，默认为 mybatis-plus 的 IService
        // strategy.setSuperServiceClass(Sting)
        // 自定义继承的ServiceImpl类全称，带包名，默认为 mybatis-plus 的 ServiceImpl
        // strategy.setSuperServiceImplClass(String)
        // 自定义继承的Controller类全称，带包名
        // strategy.setSuperControllerClass(String)

        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        // 控制器公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");

        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 乐观锁属性名称
//        strategy.setVersionFieldName("version");
        // 逻辑删除属性名称
//        strategy.setLogicDeleteFieldName("deleteFlag");

        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 若不使用 velocity 模板引擎，可在此修改，如修改为使用 freemarker模板引擎
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}