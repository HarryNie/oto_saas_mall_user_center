package pecker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author hongql
 *
 * @Date Created by blm on 24/4/18.
 * @Description 描述
 */
@Component
@ConfigurationProperties(prefix = "data")
public class DataConfig {
    private String rrdbHost;
    private String masterRedisHost;
    private String payRedisHost;
    private String activityRedisHost;
    private String rocksDBRedisHost;
    private Integer masterRedisPort;
    private Integer payRedisPort;
    private Integer activityRedisPort;
    private Integer rocksDBRedisPort;
    private String mmsPgUrl;
    private String mmsPgDriverClassName;
    private String mmsPgUserName;
    private String mmsPgPassword;
    private Integer dataBaseInitialSize;
    private Integer dataBaseMinIdle;
    private Integer dataBaseMaxActive;
    private Boolean dataBaseTestOnBorrow;
    private String databaseValidationQuery;
    private String rabbitMQUrl;
    private String rabbitMQUserName;
    private String rabbitMQPassword;

    public String getRabbitMQUrl() {
        return rabbitMQUrl;
    }

    public void setRabbitMQUrl(String rabbitMQUrl) {
        this.rabbitMQUrl = rabbitMQUrl;
    }

    public String getRabbitMQUserName() {
        return rabbitMQUserName;
    }

    public void setRabbitMQUserName(String rabbitMQUserName) {
        this.rabbitMQUserName = rabbitMQUserName;
    }

    public String getRabbitMQPassword() {
        return rabbitMQPassword;
    }

    public void setRabbitMQPassword(String rabbitMQPassword) {
        this.rabbitMQPassword = rabbitMQPassword;
    }

    public String getRrdbHost() {
        return rrdbHost;
    }

    public void setRrdbHost(String rrdbHost) {
        this.rrdbHost = rrdbHost;
    }

    public String getMasterRedisHost() {
        return masterRedisHost;
    }

    public void setMasterRedisHost(String masterRedisHost) {
        this.masterRedisHost = masterRedisHost;
    }

    public String getPayRedisHost() {
        return payRedisHost;
    }

    public void setPayRedisHost(String payRedisHost) {
        this.payRedisHost = payRedisHost;
    }

    public String getActivityRedisHost() {
        return activityRedisHost;
    }

    public void setActivityRedisHost(String activityRedisHost) {
        this.activityRedisHost = activityRedisHost;
    }

    public String getRocksDBRedisHost() {
        return rocksDBRedisHost;
    }

    public void setRocksDBRedisHost(String rocksDBRedisHost) {
        this.rocksDBRedisHost = rocksDBRedisHost;
    }

    public Integer getMasterRedisPort() {
        return masterRedisPort;
    }

    public void setMasterRedisPort(Integer masterRedisPort) {
        this.masterRedisPort = masterRedisPort;
    }

    public Integer getPayRedisPort() {
        return payRedisPort;
    }

    public void setPayRedisPort(Integer payRedisPort) {
        this.payRedisPort = payRedisPort;
    }

    public Integer getActivityRedisPort() {
        return activityRedisPort;
    }

    public void setActivityRedisPort(Integer activityRedisPort) {
        this.activityRedisPort = activityRedisPort;
    }

    public Integer getRocksDBRedisPort() {
        return rocksDBRedisPort;
    }

    public void setRocksDBRedisPort(Integer rocksDBRedisPort) {
        this.rocksDBRedisPort = rocksDBRedisPort;
    }

    public String getMmsPgUrl() {
        return mmsPgUrl;
    }

    public void setMmsPgUrl(String mmsPgUrl) {
        this.mmsPgUrl = mmsPgUrl;
    }

    public String getMmsPgDriverClassName() {
        return mmsPgDriverClassName;
    }

    public void setMmsPgDriverClassName(String mmsPgDriverClassName) {
        this.mmsPgDriverClassName = mmsPgDriverClassName;
    }

    public String getMmsPgUserName() {
        return mmsPgUserName;
    }

    public void setMmsPgUserName(String mmsPgUserName) {
        this.mmsPgUserName = mmsPgUserName;
    }

    public String getMmsPgPassword() {
        return mmsPgPassword;
    }

    public void setMmsPgPassword(String mmsPgPassword) {
        this.mmsPgPassword = mmsPgPassword;
    }

    public Integer getDataBaseInitialSize() {
        return dataBaseInitialSize;
    }

    public void setDataBaseInitialSize(Integer dataBaseInitialSize) {
        this.dataBaseInitialSize = dataBaseInitialSize;
    }

    public Integer getDataBaseMinIdle() {
        return dataBaseMinIdle;
    }

    public void setDataBaseMinIdle(Integer dataBaseMinIdle) {
        this.dataBaseMinIdle = dataBaseMinIdle;
    }

    public Integer getDataBaseMaxActive() {
        return dataBaseMaxActive;
    }

    public void setDataBaseMaxActive(Integer dataBaseMaxActive) {
        this.dataBaseMaxActive = dataBaseMaxActive;
    }

    public Boolean getDataBaseTestOnBorrow() {
        return dataBaseTestOnBorrow;
    }

    public void setDataBaseTestOnBorrow(Boolean dataBaseTestOnBorrow) {
        this.dataBaseTestOnBorrow = dataBaseTestOnBorrow;
    }

    public String getDatabaseValidationQuery() {
        return databaseValidationQuery;
    }

    public void setDatabaseValidationQuery(String databaseValidationQuery) {
        this.databaseValidationQuery = databaseValidationQuery;
    }
}
