package com.search.async;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueManager {
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    public static BlockingQueue<String> getQueue() {
        return queue;
    }
}
