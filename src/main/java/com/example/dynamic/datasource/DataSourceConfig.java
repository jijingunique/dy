package com.example.dynamic.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	@Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    @Primary
    public DataSource testDataSource1() {
        return DataSourceBuilder.create().build();
    }
	
	@Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    @Primary
    public DataSource testDataSource2() {
        return DataSourceBuilder.create().build();
    }
	
	/**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dynamicDS1")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(testDataSource1());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>(5);
        dsMap.put("test1", testDataSource1());
        dsMap.put("test2", testDataSource2());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
}
