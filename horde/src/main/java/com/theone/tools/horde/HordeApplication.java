package com.theone.tools.horde;

import com.theone.common.base.register.EnableTheOneBase;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTheOneBase
@MapperScan("com.theone.tools.horde.dao")
public class HordeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HordeApplication.class, args);
    }

}
