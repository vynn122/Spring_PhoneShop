//package org.zin.com.phoneshopapi.security.config;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import io.lettuce.core.api.StatefulConnection;
//
//@Configuration
//public class RedisConfig {
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        GenericObjectPoolConfig<StatefulConnection<?, ?>> poolConfig = new GenericObjectPoolConfig<>();
//        poolConfig.setMaxTotal(20);
//        poolConfig.setMaxIdle(10);
//        poolConfig.setMinIdle(2);
//
//        LettucePoolingClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
//                .poolConfig(poolConfig)
//                .build();
//
//        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("localhost", 6379);
//        return new LettuceConnectionFactory(redisConfig, clientConfig);
//    }
//
//    @Bean
//    public StringRedisTemplate redisTemplate(LettuceConnectionFactory connectionFactory) {
//        return new StringRedisTemplate(connectionFactory);
//    }
//
//}
