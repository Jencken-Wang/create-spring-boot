import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.Collections;
import java.util.stream.Collectors;

//
//@SpringBootTest(classes = newController.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisPlusGenerator {

    public static void main(String[] args){
        final String projectPath = System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://10.191.143.55:8086/cq_cloud?useUnicode=true&amp;characterEncoding=UTF-8", "cloud", "cloud!@1234")
                .globalConfig(builder -> {
                    builder.author("wzg") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/wzg/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wzg.creat") // 设置父包名
                            .moduleName("addData")// 设置父包模块名
                            .mapper("model.mapper")
                            .entity("model.entity")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/wzg/src/main/java/com/wzg/creat/addData/model/xml")); // 设置mapperXml生成路径
//                            .pathInfo(Collections.singletonMap(OutputFile.entity, projectPath + "/wzg/src/main/java/com/wzg/creat/user/model/entity"))
//                            .pathInfo(Collections.singletonMap(OutputFile.mapper, projectPath + "/wzg/src/main/java/com/wzg/creat/user/model/mapper"));

                })
                .strategyConfig(builder -> {
                    builder.addInclude("pm_unified_resources_summery_info_every_time") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> {
                    builder.controller(null);
                })
                .execute();
    }

    @Test
    public void ECHGenerator() {
        FastAutoGenerator.create("url", "username", "password")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_simple") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
