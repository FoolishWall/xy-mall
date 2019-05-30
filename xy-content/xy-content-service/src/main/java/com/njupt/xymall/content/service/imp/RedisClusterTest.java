package com.njupt.xymall.content.service.imp;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wall
 * @date 2019/5/29  20:53
 * @description
 */
public class RedisClusterTest {
    @Test
    public void testJedisCluster() throws IOException {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("120.79.190.49",8660));
        nodes.add(new HostAndPort("120.79.190.49",8661));
        nodes.add(new HostAndPort("120.79.190.49",8662));
        nodes.add(new HostAndPort("120.79.190.49",8663));
        nodes.add(new HostAndPort("120.79.190.49",8664));
        nodes.add(new HostAndPort("120.79.190.49",8665));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("test","v");
        String string = jedisCluster.get("test");
        System.out.println(string);
        jedisCluster.close();
    }
}
