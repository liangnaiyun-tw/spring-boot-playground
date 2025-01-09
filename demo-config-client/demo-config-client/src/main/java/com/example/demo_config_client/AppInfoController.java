package com.example.demo_config_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AppInfoController {

    @Value("${version:123}")
    private String version;

    @GetMapping("/version")
    public String getVersion() {
        return version;
    }
}
