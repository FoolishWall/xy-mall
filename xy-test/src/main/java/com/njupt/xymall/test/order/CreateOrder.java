package com.njupt.xymall.test.order;


import redis.clients.jedis.JedisCluster;

/**
 * @author wall
 * @date 2019/6/14  21:12
 * @description
 */
public class CreateOrder {

    private static JedisCluster jedisClient = new CreateJedisCluster().getJedisCluster();

    public void getOrder(){
        //生成订单号。使用redis的incr生成。默认从0开始
        String orderId = jedisClient.incr("Test-Order-Id").toString();
        System.out.println(orderId);
    }
}
