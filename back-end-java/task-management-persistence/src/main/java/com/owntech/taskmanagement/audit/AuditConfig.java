package com.owntech.taskmanagement.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditAware")
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditAware() {
        return new AuditorAwareImpl();
    }
}
