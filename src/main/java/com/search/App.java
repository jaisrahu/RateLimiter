package com.search;


import com.search.async.Consumer;
import com.search.config.AppConfig;
import com.search.servlet.SearchServlet;
import com.search.servlet.SearchAsyncServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
 
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        
        handler.addServlet(new ServletHolder(new SearchServlet()), "/search");
        handler.addServlet(new ServletHolder(new SearchAsyncServlet()), "/searchAsync");

        Thread consumerThread = new Thread(new Consumer());
        consumerThread.start();
        server.start();
        server.join();
        
    }
}


