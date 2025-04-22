package com.example.service;

import com.example.model.MailConfig;
import org.springframework.stereotype.Service;

@Service
public class MailConfigService {
    private final MailConfig config = new MailConfig("English", 10);

    public MailConfig getConfig() {
        return config;
    }

    public void updateConfig(MailConfig newConfig) {
        config.setLanguage(newConfig.getLanguage());
        config.setPageSize(newConfig.getPageSize());
    }
}
