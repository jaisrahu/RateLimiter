package com.search.async;

public class Consumer implements Runnable {
	
	
    @Override
    public void run() {
        while (true) {
            try {
                String request = QueueManager.getQueue().take(); 
                processRequest(request);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                e.printStackTrace();
            }
        }
    }

    private void processRequest(String request) {
    	
    	
    	
        System.out.println("Processing request: " + request);
    }
}
