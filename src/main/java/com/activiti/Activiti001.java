package com.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class Activiti001 {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**
	 * ���̲��� 
	 * ִ�к����������ű��в�������
	 * 	1��act_re_deployment
	 *  2��act_re_procdef
	 */
	@Test
	public void deploymentProcessDefinition(){
		System.out.println(processEngine);
		Deployment deploment = processEngine.getRepositoryService() //�����̶���Ͳ�����ص�Service
					 .createDeployment() //����һ���������
					 .name("Hello Wold ���ų���!")
					 .addClasspathResource("diagrams/HelloWorld.bpmn") //��classes�м�����Դ
					 .addClasspathResource("diagrams/HelloWorld.png")//��classes�м�����Դ
					 .deploy();
		
		System.out.println(deploment.getName());
		System.out.println(deploment.getId());
	}
	
	/**
	 * ��������ʵ��
	 * 
	 */
	@Test
	public void startProcessInstance(){
		//���̶����KEY,ʹ��KEYʱ��Ĭ��ʹ�����°汾������
		String key = "helloworld"; //helloworld:1:4Ϊ act_hi_actinst���е�PROC_DEF_ID�ֶ�ֵ
		
		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(key); //key��Ӧhelloworld.bpmn�ļ��е�id
		
		System.out.println(pi.getId());
		System.out.println(pi.getProcessDefinitionId());
		
	}
}
