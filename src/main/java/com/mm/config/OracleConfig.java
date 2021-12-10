package com.mm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: MybatisGpConfig
 * @author: MKC
 * @date: 2021-12-06 17:57
 */
@Configuration
@MapperScan(basePackages = OracleConfig.PACKAGE, sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleConfig {
    /**
     * mapper接口文件存放的目录 精确到 oracle 目录，以便跟其他数据源隔离
     */
    static final String PACKAGE = "com.mm.**.mapper.oracle";
    static final String MAPPER_LOCATION = "classpath:mapper/oracle/*Mapper.xml";

    @Value("${spring.datasource.druid.oracle.url}")
    private String url;

    @Value("${spring.datasource.druid.oracle.username}")
    private String user;

    @Value("${spring.datasource.druid.oracle.password}")
    private String password;

    @Value("${spring.datasource.druid.oracle.driver-class-name}")
    private String driverClass;

    /**
     * 默认数据源
     *
     * @return
     */
    @Bean(name = "oracleDataSource")
    //@Primary
    public DataSource oracleDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    //@Bean(name = "oracleTransactionManager")
    @Bean
    @Primary
    public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    // @Primary
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource oracleDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(oracleDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OracleConfig.MAPPER_LOCATION));
        //mybatis对应spring boot环境的配置
        sessionFactory.setVfs(SpringBootVFS.class);
        // 导入mybatis配置
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setLogImpl(StdOutImpl.class);
        //需要在这边配置分页插件才会生效
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE_12C));
        configuration.addInterceptor(interceptor);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
}
