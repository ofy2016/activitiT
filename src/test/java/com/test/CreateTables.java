package com.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class CreateTables {

	/**
	 * ��һ�ַ������������ݿ��
	 */
	@Test
	public void createTables(){
		
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		/**
		 *  �������ݿ������
		 */
		
		// �������ݿ�����:��Ӧ��ͬ���ݿ����͵�����
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		
		// �������ݿ��JDBC URL
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");
		
		// �����������ݿ���û���
		processEngineConfiguration.setJdbcUsername("root");
		
		// �����������ݿ������
		processEngineConfiguration.setJdbcPassword("root");
		
		/**
		 * DB_SCHEMA_UPDATE_FALSE ="false"; ���Զ�������
		 * 
		 * DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop"; ��ɾ�����ٴ�����
		 * 
		 * DB_SCHEMA_UPDATE_TRUE = "true"; ��������ڣ��Զ�������
		 */
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		// �������ĺ��Ķ���ProcessEnginee����
		ProcessEngine processEngine = processEngineConfiguration
				.buildProcessEngine();

		System.out.println("processEngine:" + processEngine);
		
	}
	
	/**
	 * �ڶ��ַ�����ʹ�������ļ��������ݿ��
	 */
	@Test
	public void createTablesByConfig(){
	    
		// ���������Ķ���
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
	                .createProcessEngineConfigurationFromResource("activiti-cfg.xml");
	        
	    ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
	    System.out.println("processEngine:" + processEngine);
	}
}
