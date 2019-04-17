package com.zcj.demo.configurer;


import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Redis配置
 */
@Configuration
@EnableAutoConfiguration
@EnableCaching
public class RedisConfigurer extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.jedis.timeout}")
     private int timeout;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.pool.jedis.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.jedis.max-active}")
    private int maxActive;

    @Value("${spring.redis.pool.jedis.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.jedis.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.sentinel.nodes}")
    private String redisNodes;

    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        //最大等待毫秒数
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        //最小空闲数
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisPoolConfig;
    }

    @Bean
    public JedisSentinelPool jedisSentinelPool(){
        String[] arrNodes = redisNodes.split(",");
        List<String> listNodes = Arrays.asList(arrNodes);
        Set sentinels = new HashSet(listNodes);

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(master,sentinels,jedisPoolConfig(),password);
        return  jedisSentinelPool;
    }

//    @Bean(name="redisConnectionFactory")
//    JedisConnectionFactory redisConnectionFactory(){
//        return new JedisConnectionFactory();
//    }

    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(@Autowired RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //参照StringRedisTemplate内部实现指定序列化器
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());

        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    //使用Jackson序列化器
    private RedisSerializer<Object> valueSerializer() {
        return new GenericFastJsonRedisSerializer();
    }

//    /**
//     * spring data 1.x的写法
//     * @param redisTemplate
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(
//            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager= new RedisCacheManager (redisTemplate);
//        cacheManager.setDefaultExpiration(5*60);
//        Map<String,Long> expiresMap=new HashMap<>();
//        expiresMap.put("indexdata",3600L);//首页的缓存，默认一小时
//        cacheManager.setExpires(expiresMap);
//        return cacheManager;
//    }

    /**
     * spring data 2.x的写法
     * @param factory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        //初始化一个RedisWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
        //设置Redis值序列化为json格式
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是一小时
        defaultCacheConfig.entryTtl(Duration.ofSeconds(3600L));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }

}