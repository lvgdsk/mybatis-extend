package com.example.demo.generator;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mbextend.BaseSqlProvider;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 生成表映射类
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 16:49
 */
public class TableMapGenerator {
    // 实体类路径
    private static final String entityPath = "com/example/demo/entity";
    // 源码路径
    private static final String sourcePath = "src/main/java/";
    // 项目构建输出路径名
    private static final String outPutDirName = "target";

    public static void main(String[] args){
        String entityPackage = entityPath.replaceAll("/",".");
        // 要生成标记类的实体类对象
        List<Class<?>> entityClasses = Arrays.stream(scanner("实体类名，多个英文逗号分割")
                .split(",")).map(entity -> {
            String entityName = entityPackage + "." + entity;
            try {
                return Class.forName(entityName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(String.format("实体类%s不存在", entityName));
            }
        }).collect(Collectors.toList());

        String tableMapPackage = entityPackage.substring(0,entityPackage.lastIndexOf("."))+"."+"tablemap";
        String path = Class.class.getResource("/").getPath();
        String projectPath = path.substring(1, path.lastIndexOf(outPutDirName));
        String generatePath = projectPath + sourcePath + entityPath.substring(0,entityPath.lastIndexOf("/"))+"/tablemap";
        File directory = new File(generatePath);

        if(!directory.exists()){
            directory.mkdir();
        }
        // 生成实体类辅助类java文件
        for (Class<?> tClass : entityClasses) {
            String tableName;
            TableName tableNameAnno = tClass.getAnnotation(TableName.class);
            if(tableNameAnno==null || tableNameAnno.value().equals("")){
                tableName = camelConvert(tClass.getSimpleName());
            }else{
                tableName = tableNameAnno.value();
                if(!tableName.startsWith("`")){
                    tableName = "`"+tableName+"`";
                }
            }
            String markTemplate;

            List<String> lines = getTemplateFile();
            markTemplate = String.join("\n", lines);
            markTemplate = markTemplate.replaceAll("\\$package\\$", tableMapPackage);
            markTemplate = markTemplate.replaceAll("\\$entity\\$", tClass.getSimpleName());
            markTemplate = markTemplate.replaceAll("\\$table\\$", tableName);
            List<String> fieldsDeclare = new ArrayList<>();
            List<String> fieldInits = new ArrayList<>();
            List<String> setTables = new ArrayList<>();
            List<Field> fields = new ArrayList<>(Arrays.asList(tClass.getDeclaredFields()));
            Class<?> superclass = tClass.getSuperclass();
            if(superclass!=null) {
                fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
            }
            List<String> qColumns = new ArrayList<>();
            fields.forEach(f->{
                int modifiers = f.getModifiers();
                if(!Modifier.isStatic(modifiers)) {
                    TableField tableField = f.getAnnotation(TableField.class);
                    if (tableField == null || tableField.exist()) {
                        String column;
                        if (tableField != null && !tableField.value().equals("")) {
                            column = tableField.value().toLowerCase();
                        } else {
                            column = camelConvert(f.getName());
                        }
                        fieldsDeclare.add(String.format("public final QColumn %s;", f.getName()));
                        fieldInits.add(String.format("this.%s = new QColumn(\"%s\",columnPrefix==null?null:columnPrefix+\"%s\");",
                                f.getName(), column.startsWith("`")?column:("`"+column+"`"), column));
                        setTables.add(String.format("this.%s.setTable(this);",f.getName()));
                        qColumns.add(f.getName());
                    }
                }
            });
            markTemplate = markTemplate.replaceAll("\\$fieldsDeclare\\$", String.join("\n\t",fieldsDeclare));
            markTemplate = markTemplate.replaceAll("\\$fieldInits\\$", String.join("\n\t\t",fieldInits));
            markTemplate = markTemplate.replaceAll("\\$setTables\\$", String.join("\n\t\t",setTables));
            markTemplate = markTemplate.replaceAll("\\$sqlColumns\\$", String.join(",",qColumns));

            File javaFile = new File(directory.getAbsolutePath()+"/Q"+tClass.getSimpleName()+".java");
            try (FileWriter writer = new FileWriter(javaFile)) {
                writer.write(markTemplate);
            } catch (IOException e) {
                throw new RuntimeException(javaFile.getAbsolutePath()+"文件写入失败", e);
            }
        }
    }

    /**
     * 首字母变小写，其余大写字母变成下划线加小写，如：UserName -> user_name
     */
    private static String camelConvert(String targetStr){
        char[] chars = targetStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(Character.isUpperCase(chars[i])){
                targetStr = targetStr.replaceFirst(Character.toString(chars[i]),
                        (i == 0 ? "" : "_") + Character.toLowerCase(chars[i]));
            }
        }
        return targetStr;
    }

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
        throw new RuntimeException("请输入正确的" + tip + "！");
    }

    /**
     * 读取jar包内的模板文件
     */
    private static List<String> getTemplateFile(){
        InputStream resource = BaseSqlProvider.class.getClassLoader()
                .getResourceAsStream("MarkTemplate.txt");
        try {
            assert resource != null;
            try(BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource,StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("模板文件读取失败");
        }
    }
}
