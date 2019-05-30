package com.njupt.xymall.controller;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wall
 * @date 2019/5/25  17:52
 * @description
 */
public class FastDFSTest {

    @Test
    public void testFastDFS() throws IOException, MyException {
        ClientGlobal.init("Z:\\xymall\\xy-manager-web\\src\\main\\resources\\conf\\client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        String [] path = storageClient.upload_file("H:\\Jay1.jpg","jpg",null);
        for (String string:path) {
            System.out.println(string);
        }
    }
}
