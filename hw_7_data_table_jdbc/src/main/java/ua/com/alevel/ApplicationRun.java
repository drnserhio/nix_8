package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import ua.com.alevel.jdbc.DefaultDateBaseConnectSevice;
import ua.com.alevel.jdbc.impl.MysqlConnectServiceImpl;
import ua.com.alevel.util.MysqlProperties;

@SpringBootApplication
public class ApplicationRun {

    public DefaultDateBaseConnectSevice defaultDateBaseConnectSevice;

    public ApplicationRun(DefaultDateBaseConnectSevice defaultDateBaseConnectSevice) {
        this.defaultDateBaseConnectSevice = defaultDateBaseConnectSevice;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        defaultDateBaseConnectSevice.connection();
    }


}
