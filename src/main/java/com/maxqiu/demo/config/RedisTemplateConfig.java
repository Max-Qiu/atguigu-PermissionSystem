package com.maxqiu.demo.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis操作配置
 *
 * @author Max_Qiu
 */
@Configuration
public class RedisTemplateConfig {
    /**
     * 自定义RedisTemplate，使用fastjson格式化value
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        // key序列化
        template.setKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        // value序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // hash序列化
        template.setHashKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 启用事务支持
        template.setEnableTransactionSupport(true);
        return template;
    }
}
