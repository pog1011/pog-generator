package com.pog.generator.utils;

import com.pog.generator.entity.ColumnEntity;
import com.pog.generator.entity.GeneratorEntity;
import com.pog.generator.entity.TableEntity;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GeneratorUtil {

    public static void generatorCode(GeneratorEntity generatorEntity, TableEntity tableEntity, List<ColumnEntity> columnEntityList, ZipOutputStream zip){


        Properties config = getConfig();

        String tablePrefix = generatorEntity.getTablePrefix();
        tableEntity.setClassName(tableNameToClassName(tableEntity.getTableName(), tablePrefix));
        tableEntity.setClassname(StringUtils.uncapitalize(tableEntity.getClassName()));

        for (ColumnEntity columnEntity : columnEntityList) {

            columnEntity.setEntityName(toJavaCase(columnEntity.getColumnName()));
            columnEntity.setEntityname(StringUtils.uncapitalize(columnEntity.getEntityName()));
            columnEntity.setEntityType(config.getProperty(columnEntity.getDataType(), toJavaCase(columnEntity.getDataType())));

            if(columnEntity.getColumnKey() != null && columnEntity.getColumnKey().equalsIgnoreCase("PRI") && tableEntity.getPrimaryKey() == null){
                tableEntity.setPrimaryKey(columnEntity);
            }

        }

        tableEntity.setColumns(columnEntityList);

        if(tableEntity.getPrimaryKey() == null){
            tableEntity.setPrimaryKey(tableEntity.getColumns().get(0));
        }

        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableEntity", tableEntity);
        map.put("author", generatorEntity.getAuthor());
        map.put("email", generatorEntity.getEmail());
        map.put("moduleName", generatorEntity.getModuleName());
        map.put("package", generatorEntity.getPackageName());
        map.put("pathName", getPathName(tableEntity.getTableName(), tablePrefix));
        map.put("dateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        VelocityContext context = new VelocityContext(map);

        List<String> templates = getTemplates();

        for (String templatePath : templates) {
            Template template = Velocity.getTemplate(templatePath);
            StringWriter stringWriter = new StringWriter();
            template.merge(context, stringWriter);

            try {
                zip.putNextEntry(new ZipEntry(getZipName(templatePath, tableEntity.getClassName(), generatorEntity.getPackageName(), generatorEntity.getModuleName())));
                IOUtils.write(stringWriter.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(stringWriter);
                zip.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getZipName(String templatePath, String className, String packageName, String moduleName){

        String packagePath = "main" + File.separator + "java" + File.separator;

        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (templatePath.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (templatePath.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (templatePath.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (templatePath.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (templatePath.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (templatePath.contains("Dao.xml.vm")) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
        }

       return null;
    }

    public static String toJavaCase(String name){
        return WordUtils.capitalizeFully(name, new char[]{ '_' }).replace("_", "");
    }

    public static String tableNameToClassName(String name, String prefix){
        return toJavaCase(name.replace(prefix, ""));
    }

    public static Properties getConfig(){
        Properties properties = new Properties();
        InputStream is = GeneratorUtil.class.getClassLoader().getResourceAsStream("generatorConfig.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getPathName(String name, String tablePrefix){
        return WordUtils.uncapitalize(name.replace(tablePrefix, ""), new char[]{ '_' }).replace("_", "-");
    }

    public static List<String> getTemplates() {

        File dir = null;
        try {
            dir = ResourceUtils.getFile("classpath:velocityTemplates");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> fileNames = new ArrayList<>();
        if(dir == null || !dir.exists()){
            return fileNames;
        }

        for (String file : dir.list()) {

            File file1 = new File(dir, file);
            if(file1.isFile()){
                fileNames.add("velocityTemplates" + File.separator + file1.getName());
            }

        }

        return fileNames;
    }



}
