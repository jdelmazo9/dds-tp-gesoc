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
//        overridePersistence();// Comenté esto porque me rompía
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

}
