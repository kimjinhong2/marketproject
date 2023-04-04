package com.project.market.domain.redis.repository;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

	private final RedisTemplate<String, String> redisTemplate;
	
	public void setValues(String key,String data, Duration duration) {
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		values.set(key, data,duration);
	}
	
	public String getValues(String key) {
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		return values.get(key);
	}
}
