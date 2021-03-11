package com.theone.tools.waterfall;

import com.theone.common.base.register.EnableTheOneBase;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenxiaotong
 */
@SpringBootApplication
@EnableTheOneBase
@MapperScan("com.theone.tools.waterfall.dao")
public class WaterfallApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterfallApplication.class, args);
    }

}
