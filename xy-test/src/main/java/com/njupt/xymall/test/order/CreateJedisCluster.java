package com.njupt.xymall.test.order;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wall
 * @date 2019/6/14  21:28
 * @description
 */
public class CreateJedisCluster {

    public JedisCluster getJedisCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("120.79.190.49",8660));
        nodes.add(new HostAndPort("120.79.190.49",8661));
        nodes.add(new HostAndPort("120.79.190.49",8662));
        nodes.add(new HostAndPort("120.79.190.49",8663));
        nodes.add(new HostAndPort("120.79.190.49",8664));
        nodes.add(new HostAndPort("120.79.190.49",8665));
        return new JedisCluster(nodes);
    }
}
