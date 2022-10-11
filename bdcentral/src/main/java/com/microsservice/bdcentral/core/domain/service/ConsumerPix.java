package com.microsservice.bdcentral.core.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsservice.bdcentral.core.domain.aggregates.Pix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsumerPix {

    private PixRepository pixRepository;

    @Value("${topic.name.consumer")
    private String topicName;
    @Autowired
    public ConsumerPix(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Pix pix;

            pix = objectMapper.readValue(payload.value(), Pix.class);
            pixRepository.save(pix);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
   }
}
