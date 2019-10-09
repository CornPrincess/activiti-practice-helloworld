import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2019-10-09
 * Time: 22:12
 */
public class TestHelloWorld {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    // 部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("helloworld入门程序")
                .addClasspathResource("helloworld.bpmn20.xml")
                .deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    // 启动流程定义
    @Test
    public void startProcessInstance() {
        String processDefinitionKey = "helloworld";
        // 使用key启动流程，好处是总会启动最新版本的流程定义
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processDefinitionKey);

        System.out.println(processInstance.getDeploymentId()); // null
        System.out.println(processInstance.getId()); // 2501
        System.out.println(processInstance.getProcessDefinitionId()); // helloworld:1:4
        System.out.println(processInstance.getProcessDefinitionKey()); // helloworld
        System.out.println(processInstance.getProcessDefinitionName()); // null
    }

    // 查询当前人的个人任务
    @Test
    public void findMyPersonalTask() {
        String assignee = "王五";

        List<Task> list = processEngine.getTaskService()
                                .createTaskQuery()
                                .taskAssignee(assignee)
                                .list();

        if (list != null && list.size() > 0) {
            for (Task task: list) {
                System.out.println("任务ID：" + task.getId());
                System.out.println("任务名称："  + task.getName());
                System.out.println("任务创建时间：" + task.getCreateTime());
                System.out.println("任务办理人：" + task.getAssignee());
                System.out.println("流程定义id：" + task.getProcessDefinitionId());
                System.out.println("流程实例ID：" + task.getProcessInstanceId());
                System.out.println("执行对象id：" + task.getExecutionId());
                /*
                任务ID：7504
                任务名称：提交申请
                任务创建时间：Wed Oct 09 23:17:39 CST 2019
                任务办理人：张三
                流程定义id：helloworld:2:5004
                流程实例ID：7501
                执行对象id：7501
                 */
            }
        }
    }

    // 完成我的任务
    @Test
    public void completeMyPersonTask() {
        // 任务id
        String taskId = "12502";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务， 任务ID：" + taskId);
    }

}
