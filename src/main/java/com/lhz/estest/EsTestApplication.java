package com.lhz.estest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.net.InetAddress;

@SpringBootApplication
@EnableOpenApi
@Slf4j
@MapperScan("com.lhz.estest.mapper")
public class EsTestApplication implements ApplicationListener<WebServerInitializedEvent> {

    public static void main(String[] args) {

        SpringApplication.run(EsTestApplication.class, args);
    }
    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServer server = event.getWebServer();
        WebServerApplicationContext context = event.getApplicationContext();
        Environment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        int port = server.getPort();
        String contextPath = env.getProperty("server.servlet.context-path");
        if (contextPath == null) {
            contextPath = "";
        }
        log.info("\n---------------------------------------------------------\n" +
                "\tApplication is running! Access address:\n" +
                "\tLocal:\t\thttp://localhost:{}/index.html" +
                "\n\tExternal:\thttp://{}:{}{}/index.html" +
                "\n\tswagger-ui:\thttp://localhost:{}/swagger-ui/index.html" +
                "\n---------------------------------------------------------\n", port, ip, port, contextPath,port);
    }

}
