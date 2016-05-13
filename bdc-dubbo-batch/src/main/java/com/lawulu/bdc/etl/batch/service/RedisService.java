package com.lawulu.bdc.etl.batch.service;

public interface RedisService {
	
	/**
	 * 根据key获取redis中数据
	 * */
	public String getLogStringByKey(String key);
}
