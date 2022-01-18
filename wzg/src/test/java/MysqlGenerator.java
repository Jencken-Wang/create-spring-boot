//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @FileName: MysqlGenerator
// * @Author: wan pp
// * @Date: 2021/5/18 20:27
// * @Description:
// * @Version: 1.0
// */
//
//public class MysqlGenerator {
//
//    /**
//     * 获取全部的表
//     *
//     * @return
//     */
//    public static String[] getAllTables() {
//        List list = new ArrayList();
//        try {
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://10.191.143.55:8086/cq_cloud?useUnicode=true&amp;characterEncoding=UTF-8",
//                    "cloud",
//                    "cloud!@1234");
//            DatabaseMetaData metaData = connection.getMetaData();
//            ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
//            while (rs.next()) {
//                list.add(rs.getString(3));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (String[]) list.toArray(new String[]{});
//    }
//
//    /**
//     * RUN THIS
//     */
//    public static void main(String[] args) {
//        System.out.println("开始生成！");
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        final String projectPath = System.getProperty("user.dir") + "/daybook-user";
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("wanpp");
//        gc.setOpen(false);
//        gc.setFileOverride(true);
//        //gc.setSwagger2(true);
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/daybook?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        String moduleName = "database";
//        dsc.setPassword("Admin1234!");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        final PackageConfig pc = new PackageConfig();
//        pc.setModuleName(moduleName);
//        pc.setParent("com.daybook.user.infrastructrue");
//        //pc.setMapper();
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null).setController(null));
//
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setEntityLombokModel(true);
//        strategy.setChainModel(true);
//        strategy.setEntityTableFieldAnnotationEnable(true);
//
//
//        strategy.setVersionFieldName("version");
//        strategy.setLogicDeleteFieldName("deleted");
//        strategy.setInclude(getAllTables());//调用方法获取全部表
//        //这里也可以传递String[]进去
//        //strategy.setExclude(excludeTables);//排除的表
//
//        strategy.setControllerMappingHyphenStyle(true);
//        //strategy.setTablePrefix(pc.getModuleName() + "_");
//
//        mpg.setStrategy(strategy);
//
//        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//        System.out.println("生成完毕！");
//    }
//
//}
