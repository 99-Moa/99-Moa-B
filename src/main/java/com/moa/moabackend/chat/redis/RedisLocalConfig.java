package com.moa.moabackend.chat.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 로컬 환경일경우 내장 레디스가 실행됩니다.
// 스프링이 실행됨과 동시에 embedded redis 서버도 동시 실행
// EmbeddedRedisConfig
//@Profile("local")
@Configuration
public class RedisLocalConfig {

    @Value("${spring.redis.port}")
    private int redisPort = 6379;

    private RedisServer redisServer;

//    @PostConstruct
//    public void redisServer() {
//        redisServer = new RedisServer(redisPort);
//        redisServer.start();
//    }

    @PostConstruct
    public void start() {
        redisServer = RedisServer.builder()
                .port(6379)
                .setting("maxmemory 128M")
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}