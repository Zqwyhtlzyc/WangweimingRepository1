package com.imooc.demo.testMain;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneratorTest {

    public static void main(String[] args) throws XMLParserException, IOException , InvalidConfigurationException,InterruptedException, SQLException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("C:\\Users\\72717\\IdeaProjects\\demo\\src\\main\\resources\\generatorConfig.xml");

        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        Configuration configuration = configurationParser.parseConfiguration(configFile);
        DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,defaultShellCallback,warnings);
        myBatisGenerator.generate(null);
    }
}
