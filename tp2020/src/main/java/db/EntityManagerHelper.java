package db;

import javax.persistence.*;
import java.util.function.Supplier;

public class EntityManagerHelper {

    static EntityManagerFactory overridePersistence() {
        String persistence_unit_name = System.getenv().get("PERSISTENCE_UNIT_NAME");
        System.out.println("persistence_unit_name: "+ persistence_unit_name);
        if(persistence_unit_name != null){
            return Persistence.createEntityManagerFactory(persistence_unit_name);
        }
        return Persistence.createEntityManagerFactory("db-local");

//
//        boolean cambiamo_algo = false;
//        Map<String, String> env = System.getenv();
//        Map<String, Object> configOverrides = new HashMap<String, Object>();
//        configOverrides = Persistence.createEntityManagerFactory("db").getProperties();
//        System.out.println("ENV VARS: "+env.toString());
//        for (String envName : env.keySet()) {
//            if (envName.contains("USER_DB")) {
//                cambiamo_algo = true;
//                configOverrides.put("hibernate.connection.username", env.get(envName));
//                System.out.println(env.get(envName));
//            }
//            if (envName.contains("PASS_DB")) {
//                cambiamo_algo = true;
//                configOverrides.put("hibernate.connection.password", env.get(envName));
//                System.out.println(env.get(envName));
//            }
//            if (envName.contains("URL_DB")) {
//                cambiamo_algo = true;
//                configOverrides.put("hibernate.connection.url", env.get(envName));
//                System.out.println(env.get(envName));
//            }
//            // You can put more code in here to populate configOverrides...
//        }
//        if(cambiamo_algo) {
//            System.out.println("Trueeeeeeeeeeeeasdsd");
//            return Persistence.createEntityManagerFactory("db", configOverrides);
//        }
//        System.out.println("No entro por ahi, debeeria andar");
//        return Persistence.createEntityManagerFactory("db");
    }

    private static EntityManagerFactory emf;

    private static ThreadLocal<EntityManager> threadLocal;

    static {
        try {
//            emf = Persistence.createEntityManagerFactory("db");
            emf = overridePersistence();
            threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager entityManager() {
        return getEntityManager();
    }

    public static EntityManager getEntityManager() {
        EntityManager manager = threadLocal.get();
        if (manager == null || !manager.isOpen()) {
//            System.out.println("Abriendo entity manager");
            manager = emf.createEntityManager();
            threadLocal.set(manager);
        }
        return manager;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null && em.isOpen()) {
//            System.out.println("Cerrando entity manager");
            em.close();
            threadLocal.set(null);
        }
    }

    public static void beginTransaction() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(!tx.isActive()){
            tx.begin();
        }
    }

    public static void commit() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.commit();
        }

    }

    public static void rollback(){
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.rollback();
        }
    }

    public static Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    public static void persist(Object o){
        entityManager().persist(o);
    }

    public static void merge(Object o){
        entityManager().merge(o);
    }

    public static void delete(Object o){
        entityManager().remove(o);
    }

    public static void withTransaction(Runnable action) {
        withTransaction(() -> {
            action.run();
            return null;
        });
    }
    public static <A> A withTransaction(Supplier<A> action) {
        beginTransaction();
        try {
            A result = action.get();
            commit();
            return result;
        } catch(Throwable e) {
            rollback();
            throw e;
        }
    }

//    private static Map<Object, Object> replaceWithEnvironmentVariableValues(Map<String, String> props) {
//        Map<Object, Object> overrideProps = new HashMap<>();
//        for (Map.Entry<String, String> entry : props.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            boolean overridden = false;
//            if (containsVariable(key)) {
//                key = replaceWithVariableValue(key);
//                overridden = true;
//            }
//            if (containsVariable(value)) {
//                value = replaceWithVariableValue(value);
//                overridden = true;
//            }
//            if (overridden) {
//                overrideProps.put(key, value);
//            }
//        }
//        return overrideProps;
//    }
}
