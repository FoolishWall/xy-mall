package com.njupt.xymall.content.service.imp;

import com.njupt.xymall.content.service.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author wall
 * @date 2019/5/29  22:27
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-redis.xml"})
public class ContentServiceImplTest {

    @Autowired
    private ContentService contentService;
    @Test
    public void addContent() {
    }

    @Test
    public void getContentListByCid() {
        System.out.println(contentService.getContentListByCid(89));
    }
}