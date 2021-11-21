package com.ccqq.case1;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {

    private String connectStr = "slave101:2181,slave102:2181,slave103:2181";
    private int seesionTimeOut = 2000;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer distributeServer = new DistributeServer();

        distributeServer.getConnect();

        distributeServer.register(args[0]);

        distributeServer.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void register(String hostname) throws KeeperException, InterruptedException {
        String node = zooKeeper.create("/servers/" + hostname, hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println(hostname + "is online");
    }

    private void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectStr, seesionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

    }
}
