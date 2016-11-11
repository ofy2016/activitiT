package com.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class CreateTables {

	/**
	 * 第一种方法：创建数据库表
	 */
	@Test
	public void createTables(){
		
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		/**
		 *  连接数据库的配置
		 */
		
		// 配置数据库驱动:对应不同数据库类型的驱动
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		
		// 配置数据库的JDBC URL
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");
		
		// 配置连接数据库的用户名
		processEngineConfiguration.setJdbcUsername("root");
		
		// 配置连接数据库的密码
		processEngineConfiguration.setJdbcPassword("root");
		
		/**
		 * DB_SCHEMA_UPDATE_FALSE ="false"; 不自动创建表
		 * 
		 * DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop"; 先删除表再创建表
		 * 
		 * DB_SCHEMA_UPDATE_TRUE = "true"; 如果表不存在，自动创建表
		 */
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		// 工作流的核心对象，ProcessEnginee对象
		ProcessEngine processEngine = processEngineConfiguration
				.buildProcessEngine();

		System.out.println("processEngine:" + processEngine);
		
	}
	
	/**
	 * 第二种方法：使用配置文件创建数据库表
	 */
	@Test
	public void createTablesByConfig(){
	    
		// 工作流核心对象
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
	                .createProcessEngineConfigurationFromResource("activiti-cfg.xml");
	        
	    ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
	    System.out.println("processEngine:" + processEngine);
	}
}
