import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2019-09-27
 * Time: 21:41
 */
public class TestActivit {

    // create 23 activiti table mannually
    @Test
    public void createTable() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        // datebase config
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti-practice0928?userUnicode=true&characterEncoding=utf8");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("DXQfmy52");

        /*
        public static final String DB_SCHEMA_UPDATE_FALSE = "false"; can not create tables
        public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop"; drop first, then create tables
        public static final String DB_SCHEMA_UPDATE_TRUE = "true"; can create tables
         */

        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // create tables when create processEngine
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println("ProcessEngine: " + processEngine);

    }

    // create 23 tables use config xml
    @Test
    public void createTable2() {
//        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//        ProcessEngine processEngine = configuration.buildProcessEngine();
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

        ProcessEngine processEngine1 = ProcessEngines.getDefaultProcessEngine();
        System.out.println("ProcessEngine: " + processEngine);
        System.out.println("ProcessEngine: " + processEngine1);
    }
}

