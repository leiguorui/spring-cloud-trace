package com.haodf.http.trace;

import com.google.common.base.Strings;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (!Strings.isNullOrEmpty(RibbonFilterContextHolder.getCurrentContext().get("HDF-test-version"))){
            request.getHeaders().set("hdf-test-version", RibbonFilterContextHolder.getCurrentContext().get("HDF-test-version"));
        }
        return execution.execute(request, body);
    }
}