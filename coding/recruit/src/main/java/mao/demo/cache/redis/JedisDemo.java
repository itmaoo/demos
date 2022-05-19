package mao.demo.cache.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisDemo {
    Jedis jedis;
    @Before
    public void init(){
        String host = "10.21.13.14";
        int port = 6379;
        jedis = new Jedis(host, port);
    }
    public static void main(String[] args) {
        // 1.创建Jedis连接
        String host = "10.21.13.14";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        // 2.检测连通性
        String result = jedis.ping();
        System.out.println("result=" + result);
        // 3.关闭Jedis连接
        jedis.close();
    }
    @Test
    public void testKeys() {
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        String key = "k1";
        String value = jedis.get(key);
        System.out.println(key + " value=" + value);

        Boolean exists = jedis.exists(key);
        System.out.println(key + " exists=" + exists);

        Long ttl = jedis.ttl(key);
        System.out.println(key + " ttl=" + ttl);

        Long del = jedis.del("k1", "k2", "k3");
        System.out.println("k1,k2,k3" + " del=" + del);
    }
    @Test
    public void testSetString() {
        jedis.set("s1", "v1");
        String value = jedis.get("s1");
        System.out.println("s1 value=" + value);
    }

    @Test
    public void testMsetString() {
        jedis.mset("ms1", "mv1", "ms2", "mv2", "ms3", "mv3");
        List<String> mget = jedis.mget("ms1", "ms2", "ms3");
        System.out.println("ms1,2,3, values=" + mget);
    }
    @Test
    public void testList() {
        jedis.lpush("list1", "lv1", "lv2", "lv3");
        List<String> list1 = jedis.lrange("list1", 0, -1);
        System.out.println("list1 lrange=" + list1);

        jedis.rpush("list2", "rv1", "rv2", "rv3");
        List<String> list2 = jedis.lrange("list2", 0, -1);
        System.out.println("list2 lrange=" + list2);

        jedis.rpop("list2");
        jedis.lpop("list2");
        list2 = jedis.lrange("list2", 0, -1);
        System.out.println("list2 lrange=" + list2);
    }
    @Test
    public void testSet() {
        String key1 = "set1";
        jedis.sadd(key1, "tom");
        jedis.sadd(key1, "jerry");
        jedis.sadd(key1, "lucy");

        Set<String> smembers = jedis.smembers(key1);
        System.out.println("set1 smembers=" + smembers);
    }
    @Test
    public void testZset() {
        String key1 = "zset1";
        jedis.zadd(key1, 400d, "C");
        jedis.zadd(key1, 500d, "C++");
        jedis.zadd(key1, 100d, "Java");
        jedis.zadd(key1, 200d, "SQL");
        jedis.zadd(key1, 300d, "Python");

        Set<String> zrange1 = jedis.zrange(key1, 0, -1);
        System.out.println("zset1 [0,-1]=" + zrange1);

        Set<String> zrangeByScore1 = jedis.zrangeByScore(key1, 100, 300);
        System.out.println("zset1 [100,300]=" + zrangeByScore1);

        Set<Tuple> zrangeByScoreWithScores1 = jedis.zrangeByScoreWithScores(key1, 100, 300);
        System.out.println("zset1 [100,300]=" + zrangeByScoreWithScores1);

        Set<String> zrangeByScore2 = jedis.zrangeByScore(key1, "-inf", "+inf");
        System.out.println("zset1 [-inf,+inf]=" + zrangeByScore2);
    }
    @Test
    public void testHash() {
        String key1 = "hash1";
        String field1 = "field1";
        String value1 = "value1";
        jedis.hset(key1, field1, value1);

        String hget1 = jedis.hget(key1, field1);
        System.out.println("hash1:field1 hget=" + hget1);

        String key2 = "hash2";
        Map<String, String> field2value = new HashMap<>();
        field2value.put("id", "90955");
        field2value.put("name", "yuwen");
        field2value.put("address", "changzhou");

        jedis.hmset(key2, field2value);

        List<String> hmget2 = jedis.hmget(key2, "id", "name");
        System.out.println("hash2:id,name hmget=" + hmget2);
    }
}