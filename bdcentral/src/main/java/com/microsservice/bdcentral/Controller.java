/*
package com.microsservice.bdcentral;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class Controller {
    private final kafkaProducer kafkaProducer;
    @GetMapping(value = "/send")
    public void send(){
        kafkaProducer.send("Mensagem de teste enviada ao tópico");
    }
}
*/