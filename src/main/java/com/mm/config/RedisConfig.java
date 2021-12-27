package com.mm.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Redis配置类
 * @author: MKC
 * @date: 2021-12-07 10:23
 */
@Configuration
@ConditionalOnClass(RedisTemplate.class)
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.max-redirects}")
    private String redirects;
    @Value("${spring.redis.timeout}")
    private String timeout;
    @Value("${spring.redis.password}")
    private String password;

    final String  SPLIT_STR = ",";

    /**
     * redisTemplate的一些配置
     * @param redisConnectionFactory redisConnectionFactory
     * @return redisTemplate的一些配置
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(objectMapper);

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * stringRedisTemplate配置
     * @param redisConnectionFactory redisConnectionFactory
     * @return stringRedisTemplate配置
     * @throws UnknownHostException
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * redis集群配置
     * @return redis集群
     */
    @Bean
    public RedisClusterConfiguration getClusterConfiguration() {
        if (host.split(SPLIT_STR).length > 1) {
            //如果是host是集群模式的才进行以下操作
            Map<String, Object> source = new HashMap<>(4);
            source.put("spring.redis.cluster.nodes", host);
            source.put("spring.redis.cluster.timeout", timeout);
            source.put("spring.redis.cluster.max-redirects", redirects);
            source.put("spring.redis.cluster.password", password);
            RedisClusterConfiguration cluster = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
            cluster.setPassword(password);
            return cluster;
        } else {
            return null;
        }
    }

    /**
     * redis配置
     * @return redis
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        if (host.split(SPLIT_STR).length == 1) {
            RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
            standaloneConfiguration.setHostName(host.split(":")[0]);
            standaloneConfiguration.setPort(Integer.parseInt(host.split(":")[1]));
            standaloneConfiguration.setPassword(RedisPassword.of(password));
            return new JedisConnectionFactory(standaloneConfiguration);
        } else {
            return new JedisConnectionFactory(getClusterConfiguration());
        }
    }
}
