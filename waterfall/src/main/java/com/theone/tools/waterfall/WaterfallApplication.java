package com.theone.tools.waterfall;

import com.theone.common.base.register.EnableTheOneBase;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chenxiaotong
 */
@SpringBootApplication
@EnableTheOneBase
@EnableScheduling
@MapperScan("com.theone.tools.waterfall.dao")
@ServletComponentScan("com.theone.tools.waterfall.filter")
public class WaterfallApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterfallApplication.class, args);
    }

}
