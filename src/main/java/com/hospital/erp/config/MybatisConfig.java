package com.hospital.erp.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.hospital.erp"}
							,sqlSessionFactoryRef="sqlSessionFactory"
							,sqlSessionTemplateRef="sqlSessionTemplate")
public class MybatisConfig {

	/*
	 * @Bean SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws
	 * Exception { SqlSessionFactoryBean sqlSessionFactoryBean = new
	 * SqlSessionFactoryBean(); sqlSessionFactoryBean.setDataSource(dataSource);
	 * sqlSessionFactoryBean.setConfigLocation(new
	 * ClassPathResource("mybatis/mybatis-config.xml"));
	 * 
	 * return sqlSessionFactoryBean.getObject(); }
	 * 
	 * @Bean SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory
	 * sqlSessionFactory) { return new SqlSessionTemplate(sqlSessionFactory); }
	 */
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
        
		 // mapper.xml 의 resultType 패키지 주소 생략
		sqlSessionFactoryBean.setTypeAliasesPackage("com.hospital.erp");
        
		// mybatis 설정 파일 세팅
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:database/config/mybatis-config.xml"));
        
		// mapper.xml 위치 패키지 주소
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:database/mappers/*Mapper.xml"));
        
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name="sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
    
}
