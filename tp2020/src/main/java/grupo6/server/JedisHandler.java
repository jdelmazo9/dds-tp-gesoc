package grupo6.server;


import grupo6.dominio.entidades.Sesion;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class JedisHandler
{
    static JedisPool pool ;

    static {
        URI redisURI = null;
        try {
            redisURI = new URI(System.getenv("REDIS_URL"));
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(10);
            poolConfig.setMaxIdle(5);
            poolConfig.setMinIdle(1);
            poolConfig.setTestOnBorrow(true);
            poolConfig.setTestOnReturn(true);
            poolConfig.setTestWhileIdle(true);
            pool = new JedisPool(poolConfig, redisURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

//    public static JedisPool getPool() {
//        URI redisURI = new URI(System.getenv("REDIS_URL"));
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(10);
//        poolConfig.setMaxIdle(5);
//        poolConfig.setMinIdle(1);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnReturn(true);
//        poolConfig.setTestWhileIdle(true);
//        JedisPool pool = new JedisPool(poolConfig, redisURI);
//        return pool;
//    }

    public static Jedis getResource(){
        return pool.getResource();
    }

    public static Sesion getSesion(){
        if(getResource().exists("sesion")){
            return Sesion.sesionFromJson(getResource().get("sesion"));
        }
        return null;
    }

    public static void setSesion(Sesion sesion){
        getResource().set("sesion", sesion.toJson());
    }

    public static void closeSesion(){
        getResource().close();
    }

// In your multithreaded code this is where you'd checkout a connection
//// and then return it to the pool
//    try (Jedis jedis = pool.getResource()){
//        jedis.set("foo", "bar");
//    }

}
