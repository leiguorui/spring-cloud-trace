package com.haodf.http.trace;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestTemplatePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof RestTemplate) {
            RestTemplate restTemplate = (RestTemplate) bean;

            List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
            interceptors.add(new HeaderRequestInterceptor());
            restTemplate.setInterceptors(interceptors);
            return restTemplate;
        }
        return bean;
    }
}