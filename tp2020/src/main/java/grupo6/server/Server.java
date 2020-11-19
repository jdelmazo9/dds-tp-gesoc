package grupo6.server;

import spark.Spark;
import spark.debug.DebugScreen;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        //Spark.port(9000);
        Spark.port(getHerokuAssignedPort());
        //overridePersistence(); Comenté esto porque me rompía
        Router.init();
        DebugScreen.enableDebugScreen();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 9000; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    static void overridePersistence() {
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();
        for (String envName : env.keySet()) {
            if (envName.contains("USER_DB")) {
                configOverrides.put("hibernate.connection.username", env.get(envName));
                System.out.println(env.get(envName));
            }
            if (envName.contains("PASS_DB")) {
                configOverrides.put("hibernate.connection.password", env.get(envName));
                System.out.println(env.get(envName));
            }
            if (envName.contains("URL_DB")) {
                configOverrides.put("hibernate.connection.url", env.get(envName));
                System.out.println(env.get(envName));
            }
            // You can put more code in here to populate configOverrides...
        }
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default", configOverrides);
    }
}
