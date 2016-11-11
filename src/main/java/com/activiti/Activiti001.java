package com.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class Activiti001 {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**
	 * 流程部署 
	 * 执行后向以下两张表中插入数据
	 * 	1、act_re_deployment
	 *  2、act_re_procdef
	 */
	@Test
	public void deploymentProcessDefinition(){
		System.out.println(processEngine);
		Deployment deploment = processEngine.getRepositoryService() //与流程定义和部署相关的Service
					 .createDeployment() //创建一个部署对象
					 .name("Hello Wold 入门程序!")
					 .addClasspathResource("diagrams/HelloWorld.bpmn") //从classes中加载资源
					 .addClasspathResource("diagrams/HelloWorld.png")//从classes中加载资源
					 .deploy();
		
		System.out.println(deploment.getName());
		System.out.println(deploment.getId());
	}
	
	/**
	 * 启动流程实例
	 * 
	 */
	@Test
	public void startProcessInstance(){
		//流程定义的KEY,使用KEY时，默认使用最新版本的流程
		String key = "helloworld"; //helloworld:1:4为 act_hi_actinst表中的PROC_DEF_ID字段值
		
		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(key); //key对应helloworld.bpmn文件中的id
		
		System.out.println(pi.getId());
		System.out.println(pi.getProcessDefinitionId());
		
	}
}
