package com.bytesvc.consumer.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.bytesoft.bytejta.supports.jdbc.LocalXADataSource;
import org.bytesoft.bytetcc.supports.springcloud.SpringCloudConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Import(SpringCloudConfiguration.class)
@Configuration
public class ConsumerConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		LocalXADataSource dataSource = new LocalXADataSource();
		dataSource.setDataSource(this.invokeGetDataSource());
		return dataSource;
	}

	public DataSource invokeGetDataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://127.0.0.1:3306/inst02");
		bds.setUsername("root");
		bds.setPassword("123456");
		bds.setMaxActive(50);
		bds.setInitialSize(20);
		bds.setMaxWait(60000);
		bds.setMinIdle(6);
		bds.setLogAbandoned(true);
		bds.setRemoveAbandoned(true);
		bds.setRemoveAbandonedTimeout(1800);
		bds.setTestWhileIdle(true);
		bds.setTestOnBorrow(false);
		bds.setTestOnReturn(false);
		bds.setValidationQuery("select 'x' ");
		bds.setValidationQueryTimeout(1);
		bds.setTimeBetweenEvictionRunsMillis(30000);
		bds.setNumTestsPerEvictionRun(20);
		return bds;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(this.getDataSource());
		return jdbcTemplate;
	}

}
