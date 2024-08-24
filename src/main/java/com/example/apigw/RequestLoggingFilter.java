package com.example.apigw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

@Component
public class RequestLoggingFilter implements GlobalFilter {
    Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Request Query Param : {}", exchange.getRequest().getQueryParams());
        logger.info("Request Query Path : {}", exchange.getRequest().getPath());
        logger.info("Request will be routed to {}", exchange.getAttributeOrDefault(GATEWAY_REQUEST_URL_ATTR, "Unknown"));

        return chain.filter(exchange);
    }
}