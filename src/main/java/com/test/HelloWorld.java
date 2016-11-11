package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
public class HelloWorld {
	
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
		//流程定义的KEY
		String key = "helloworld:1:4"; //helloworld:1:4为 act_hi_actinst表中的PROC_DEF_ID字段值
		
		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceById(key); //key对应helloworld.bpmn文件中的id
		
		System.out.println(pi.getId());
		System.out.println(pi.getProcessDefinitionId());
		
	}
	
	
	/**
	 *  查询当前办理人的代办流程
	 */
	@Test
	public void findMyPersionTask(){
		
		String assingee = "李小二";
		List<Task> taskList = processEngine.getTaskService().createTaskQuery()
						.taskAssignee(assingee)
						.list();
	
		if(taskList != null && taskList.size() > 0){
			for(Task task:taskList){
				System.out.println(task.getAssignee());
				System.out.println(task.getName());
			}
		}
		
	}
	
	/**
	 * 完成我的任务
	 */
	@Test
	public void completeMyPsersonTask(){
		String taskId = "902";
		processEngine.getTaskService().complete(taskId);
		System.out.println("完成任务："+taskId);
	}
	
	
	/**
	 * 查询最新版本的流程定义
	 */
	@Test
	public void findProcessDefinition(){
		List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery()
											.orderByProcessDefinitionKey().desc()
											.list();
											
		Map<String, ProcessDefinition> map = new HashMap();
		
		if(list!=null && list.size()>0){
			for(ProcessDefinition pd:list){
				map.put(pd.getId(), pd);
			}
		}
		
		list = new ArrayList(map.values());
		if(list != null && list.size()>0){
			for(ProcessDefinition pd:list){
				System.out.println("流程定义Id"+pd.getId());
				System.out.println("流程定义KEY"+pd.getKey());
				System.out.println("流程定义版本"+pd.getVersion());
			}
			
		}
		
	}
	
	/**
	 * 删除流程定义
	 */
	@Test
	public void deleteProcessDefinition(){
		String processDefinitionKey = "helloworld";
		List<ProcessDefinition> list =  processEngine.getRepositoryService()
													 .createProcessDefinitionQuery()
													 .processDefinitionKey(processDefinitionKey)
													 .list();
		
		if(list!=null && list.size()>0){
			for(ProcessDefinition pd:list){
				String deploymentid = pd.getDeploymentId();
				processEngine.getRepositoryService().deleteDeployment(deploymentid, true);
			}
		}
		
	}
}

