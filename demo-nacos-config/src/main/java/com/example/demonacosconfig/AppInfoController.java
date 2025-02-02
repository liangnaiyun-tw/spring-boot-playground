package com.example.demonacosconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appInfo")
@RefreshScope
public class AppInfoController {

    @Value("${spring.application.version}")
    private String version;

    @GetMapping("/version")
    public String getVersion(){
        return version;
    }
}
