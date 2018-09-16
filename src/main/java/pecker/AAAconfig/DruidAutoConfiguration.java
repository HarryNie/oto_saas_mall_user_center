package pecker.AAAconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author chenwenjie
 * @since 2017/2/23.
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "pecker.model.mapper.business" , sqlSessionFactoryRef = "businessSqlFactory")
public class DruidAutoConfiguration {

    @Autowired
    private DataConfig dataConfig;

    @Bean(name = "businessDataSource")
    @Primary
    public DataSource dataSource() {
        System.out.println("DruidDataSource");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataConfig.getMmsPgUrl());
        dataSource.setDriverClassName(dataConfig.getMmsPgDriverClassName());
        dataSource.setUsername(dataConfig.getMmsPgUserName());
        dataSource.setPassword(dataConfig.getMmsPgPassword());
        dataSource.setInitialSize(dataConfig.getDataBaseInitialSize());
        dataSource.setMinIdle(dataConfig.getDataBaseMinIdle());
        dataSource.setMaxActive(dataConfig.getDataBaseMaxActive());
        dataSource.setTestOnBorrow(dataConfig.getDataBaseTestOnBorrow());
        dataSource.setValidationQuery(dataConfig.getDatabaseValidationQuery());
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    @Bean(name = "businessTransactionManager")
    @Primary
    public DataSourceTransactionManager dcTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "businessSqlFactory")
    @Primary
    public SqlSessionFactory createBusinessFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        Interceptor[] plugins = new Interceptor[]{pageHelper()};
        sessionFactory.setPlugins(plugins);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    public PageHelper pageHelper() {
        System.out.println("pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
