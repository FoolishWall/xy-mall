package com.njupt.xymall.search.service.test;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wall
 * @date 2019/6/2  15:55
 * @description
 */
public class TestSolrCloud {

    @Test
    public void testAddDocument() throws IOException, SolrServerException {
        List<String> zkHosts = new ArrayList<String>();
        zkHosts.add("180.209.64.38:8184");
        zkHosts.add("180.209.64.38:8185");
        zkHosts.add("180.209.64.38:8186");
        Optional<String> zkChroot = Optional.of("/");
        CloudSolrClient.Builder builder = new CloudSolrClient.Builder(zkHosts, zkChroot);
        CloudSolrClient cloudSolrServer = builder.build();

        cloudSolrServer.setDefaultCollection("collection-test");
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域
        document.setField("id","solrcloud");
        document.setField("item_title","测试商品");
        document.setField("item_price",999);
        //把文件写入索引库
        cloudSolrServer.add(document);
        //提交
        cloudSolrServer.commit();

        //关闭
        cloudSolrServer.close();
    }
}
