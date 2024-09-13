package com.search.async;

public class Producer {
    public void receiveRequest(String request) {
        try {
            QueueManager.getQueue().add(request);  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

