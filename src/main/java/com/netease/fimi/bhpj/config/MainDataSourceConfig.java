package com.netease.fimi.bhpj.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 后台数据库配置
 *
 * @author Mr.ZHUANG
 */
@Configuration
@MapperScan(basePackages = MainDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mainSqlSessionFactory")
public class MainDataSourceConfig {

    static final String PACKAGE = "com.netease.fimi.bhpj.domain";

    private static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Bean(name = "mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.bhpj")
    @Primary
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "mainTransactionManager")
    @Primary
    public DataSourceTransactionManager mainTransactionManager() {
        return new DataSourceTransactionManager(mainDataSource());
    }

    @Bean(name = "mainSqlSessionFactory")
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource mainDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mainDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//        sessionFactory.setTypeAliases(new Class[]{User.class, UserRole.class, UserAuth.class, ModLog.class,UserGroup.class});
        sessionFactory.setTypeAliasesPackage("");
        return sessionFactory.getObject();
    }
}
