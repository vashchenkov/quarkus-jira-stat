package ru.example.community.jirastat.issuestatus.imp.dto;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class JiraChangeDeserializer extends JsonbDeserializer<JiraChangeDTO> {
    public JiraChangeDeserializer(){
        super(JiraChangeDTO.class);
    }
}
