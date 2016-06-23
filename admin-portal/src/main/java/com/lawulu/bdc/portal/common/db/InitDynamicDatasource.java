package com.lawulu.bdc.portal.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.lawulu.datacenter.common.db.DynamicDataSource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by lawulu on 15-9-18.
 */
@Component
public class InitDynamicDatasource implements ApplicationContextAware {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

    final String jdbcUrlTemplate = "jdbc:oracle:thin:@%s:%s/%s";

    final String dynamicDatasourcePrefix ="ddsp_";


    public final Map<Object, Object> targetDataSources = Maps.newLinkedHashMap();

    public final  List<DBInfo> dbInfos = Lists.newArrayList();

    public final Map<String,DBInfo> dbInfoMap = Maps.newHashMap();

    @PostConstruct
    public void init(){
        getDbInfosFromDb();
        Assert.notEmpty(dbInfos, "Init dynamic datasource failed");
        DynamicDataSource dynamicDataSource = (DynamicDataSource) applicationContext.getBean("dynamicDataSource");
        getTargetDataSources(dbInfos);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();//call the method to generate the resolvedDataSources

    }


    private void getTargetDataSources(List<DBInfo> list){


        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;

        // 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext
                .getBeanFactory();


        for (int i = 0; i < list.size(); i++) {
            DBInfo dbInfo = list.get(i);
            if("03".equals(dbInfo.getDepttype())){ //分院
                registerBeanDefinition(dbInfo, defaultListableBeanFactory);
                targetDataSources.put(dbInfo.getDeptid(),applicationContext.getBean(this.dynamicDatasourcePrefix + dbInfo.getDeptid()));
            }

        }


    }

//    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
//    <property name="driverClassName" value="${jdbc.driverClassName}"/>
//    <property name="url" value="${jdbc.url}"/>
//    <property name="username" value="${jdbc.username}"/>
//    <property name="password" value="${jdbc.password}"/>
//    </bean>
// 因为网络问题,维护一个数据库连接池不是很现实所以 不再使用DBCP POOL

// 因为每次新建一个链接对分院的压力比较大,所以要回归原有的方式
//<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
//    <property name="driverClassName" value="#{config['jdbc.driverClassName']}" />
//    <property name="url" value="#{config['jdbc.url']}"/>
//    <property name="username" value="#{config['jdbc.username']}" />
//    <property name="password" value="#{config['jdbc.password']}" />
//
//    <property name="filters" value="stat" />
//
//    <property name="maxActive" value="20" />
//    <property name="initialSize" value="3" />
//    <property name="maxWait" value="60000" />
//    <property name="minIdle" value="1" />
//
//    <property name="timeBetweenEvictionRunsMillis" value="60000" />
//    <property name="minEvictableIdleTimeMillis" value="300000" />
//
//    <property name="validationQuery" value="SELECT 1 FROM DUAL" />
//    <property name="testWhileIdle" value="true" />
//    <property name="testOnBorrow" value="true" />
//    <property name="testOnReturn" value="false" />
//    <property name="poolPreparedStatements" value="true" />
//    <property name="maxPoolPreparedStatementPerConnectionSize" value="20" />
//    </bean>
    private void registerBeanDefinition(DBInfo dbInfo, DefaultListableBeanFactory defaultListableBeanFactory){

        // 通过BeanDefinitionBuilder创建bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(DruidDataSource.class);
        // 设置属性userAcctDAO,此属性引用已经定义的bean:userAcctDAO
/*
        s = s.replace("address",serverip);
        s = s.replace("port",port);

        s = s.replace("dbname",dbname.toLowerCase());*/

        beanDefinitionBuilder.addPropertyValue("driverClassName","oracle.jdbc.driver.OracleDriver");
        beanDefinitionBuilder.addPropertyValue("url", String.format(jdbcUrlTemplate, dbInfo.getServerip(),dbInfo.getServerport(),dbInfo.getSid().toLowerCase()));
        beanDefinitionBuilder.addPropertyValue("username",dbInfo.getDbuser());
        beanDefinitionBuilder.addPropertyValue("password",dbInfo.getDbpwd());
        beanDefinitionBuilder.addPropertyValue("initialSize","1");
        beanDefinitionBuilder.addPropertyValue("maxActive","1");
        beanDefinitionBuilder.addPropertyValue("validationQuery","SELECT 1 FROM DUAL");
        beanDefinitionBuilder.addPropertyValue("testOnBorrow","true");
        beanDefinitionBuilder.addPropertyValue("testWhileIdle","true");
        beanDefinitionBuilder.addPropertyValue("minEvictableIdleTimeMillis", "300000");
        beanDefinitionBuilder.addPropertyValue("timeBetweenEvictionRunsMillis","60000");

        // 注册bean
        defaultListableBeanFactory.registerBeanDefinition(dynamicDatasourcePrefix+dbInfo.getDeptid(),
                beanDefinitionBuilder.getRawBeanDefinition());


    }


    private void getDbInfosFromDb(){

        final  String sql= " select * from as_deptino t order by DEPTID ASC";
//        List<DBInfo> list =Lists.newArrayList();

        Connection conn =null;
        Statement stat = null;
        ResultSet rs = null;
        try{
            conn=dataSource.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while(rs.next())
            {
                String dbuser = rs.getString("DBUSER");
                String dbpwd = rs.getString("DBPWD");
                String sid = rs.getString("SID");
                String port = rs.getString("PORT");
                String serverip = rs.getString("SERVERIP");
                String pdeptid = rs.getString("PDEPTID");
                String deptid = rs.getString("DEPTID");
                String deptname = rs.getString("DEPTNAME");
                String depttype = rs.getString("DEPTTYPE");

                DBInfo info = new DBInfo();
                info.dbpwd= dbpwd;
                info.serverip = serverip;
                info.sid = sid;
                info.serverport = port;
                info.dbuser =  dbuser;
                info.deptid = deptid;
                info.deptname = deptname;
                info.pdeptid = pdeptid;
                info.depttype = depttype;
                dbInfos.add(info);
                dbInfoMap.put(info.getDeptid(),info);

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {

                }
            }
        }

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
