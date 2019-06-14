package com.njupt.xymall.test.order;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wall
 * @date 2019/6/14  21:02
 * @description 测试高并发下生成订单编号的情况
 */
public class TestCreateOrder {


    public static void main(String[] args) {

        //使用同步屏障模拟并发访问
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
        //单机模式下，一个实例
        CreateOrder createOrder = new CreateOrder();
        for (int i = 0 ; i < 100; i ++){
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                createOrder.getOrder();
            }).start();
        }
    }

}
