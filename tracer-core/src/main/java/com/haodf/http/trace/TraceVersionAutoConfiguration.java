package com.haodf.http.trace;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnProperty(value = "haodf.http.tracer.enabled", matchIfMissing = false)
public class TraceVersionAutoConfiguration {

    /**
     * 设置
     * @return
     */
    @Bean
    public DynamicRoutesFilter dynamicRoutesFilter() {
        return new DynamicRoutesFilter();
    }

    /**
     * //放在静态类里, 否则RestTemplate会创建比RestTemplatePostProcessor早
     */
    @Configuration
    @ConditionalOnClass(RestTemplate.class)
    static class RestTemplateConfig {
        @Bean
        public RestTemplatePostProcessor restTemplatePostProcessor() {
            return new RestTemplatePostProcessor();
        }
    }
}
