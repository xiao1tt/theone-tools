package com.theone.tools.horde;

import com.theone.common.base.register.EnableTheOneBase;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableTheOneBase
@EnableScheduling
@MapperScan("com.theone.tools.horde.dao")
@ServletComponentScan("com.theone.tools.horde.filter")
public class HordeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HordeApplication.class, args);
    }

}
