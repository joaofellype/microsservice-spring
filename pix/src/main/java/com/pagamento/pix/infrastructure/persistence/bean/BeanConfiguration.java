package com.pagamento.pix.infrastructure.persistence.bean;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public DozerBeanMapper getDozerMapper() {
        return new DozerBeanMapper();
    }
}
