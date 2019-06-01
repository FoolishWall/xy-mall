package com.njupt.xymall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wall
 * @date 2019/5/30  21:59
 * @description
 */
public class TestSolrJ {

    @Test
    public void  addDocument(){

        try {
        //创建一个SolrServer对象，创建一个连接，参数为solr服务的url
        SolrServer solrServer = new HttpSolrServer("http://120.79.190.49:8983/solr/xymall");
        //创建一个文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();

        //向文档对象中添加域。文档中必须包含一个id域，所有的域的名称必须在schema.xml中定义。
        document.addField("id","1");
        document.addField("item_title","测试商品");
        document.addField("item_price",1000);
        //把文档写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void queryDocument() throws Exception {
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://120.79.190.49:8983/solr/xymall");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setQuery("*:*");
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
        }
    }


    @Test
    public void queryDocumentWithHighLighting() throws Exception {
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://120.79.190.49:8983/solr/xymall");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setStart(0);
        query.setRows(20);
        query.setQuery("手机");
        //指定默认搜索域
        query.set("df", "item_title");
        //开启高亮显示
        query.setHighlight(true);
        //高亮显示的域
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = null;
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            System.out.println(itemTitle);
            System.out.println(solrDocument.get("item_price"));
        }
    }


}
