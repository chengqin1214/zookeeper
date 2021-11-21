package com.ccqq.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class zkClient {

    private String connectString = "slave101:2181,slave102:2181,slave103:2181";
    private int seesionTimeout = 2000;
    private ZooKeeper zkClient;


    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, seesionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
//                List<String> children = null;
//                try {
//                    System.out.println("-----------------------------------");
//                    children = zkClient.getChildren("/", true);
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                for (String child : children) {
//                    System.out.println(child);
//                }
//                System.out.println("----------------------------------------");
            }
        });
    }

    @Test
    public void create() throws KeeperException, InterruptedException {
        String nodeCreated = zkClient.create("/atguigu", "ss.avi".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);

        for (String child : children) {
            System.out.println(child);
        }

        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/atguigu", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }
}
