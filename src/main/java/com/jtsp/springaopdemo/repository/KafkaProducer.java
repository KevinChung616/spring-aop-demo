package com.jtsp.springaopdemo.repository;


import com.jtsp.springaopdemo.dto.AuditPayload;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    public static final String TOPIC_AUDIT = "audit";

    public String sendPayload(AuditPayload auditPayload) {
        return "success";
    }
}
