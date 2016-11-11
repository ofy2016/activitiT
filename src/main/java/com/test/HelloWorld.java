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
		//���̶����KEY
		String key = "helloworld:1:4"; //helloworld:1:4Ϊ act_hi_actinst���е�PROC_DEF_ID�ֶ�ֵ
		
		ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceById(key); //key��Ӧhelloworld.bpmn�ļ��е�id
		
		System.out.println(pi.getId());
		System.out.println(pi.getProcessDefinitionId());
		
	}
	
	
	/**
	 *  ��ѯ��ǰ�����˵Ĵ�������
	 */
	@Test
	public void findMyPersionTask(){
		
		String assingee = "��С��";
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
	 * ����ҵ�����
	 */
	@Test
	public void completeMyPsersonTask(){
		String taskId = "902";
		processEngine.getTaskService().complete(taskId);
		System.out.println("�������"+taskId);
	}
	
	
	/**
	 * ��ѯ���°汾�����̶���
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
				System.out.println("���̶���Id"+pd.getId());
				System.out.println("���̶���KEY"+pd.getKey());
				System.out.println("���̶���汾"+pd.getVersion());
			}
			
		}
		
	}
	
	/**
	 * ɾ�����̶���
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

