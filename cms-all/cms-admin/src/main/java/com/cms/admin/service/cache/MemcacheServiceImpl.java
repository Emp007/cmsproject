package com.cms.admin.service.cache;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.SerializingTranscoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Component("memcacheCache")
public class MemcacheServiceImpl implements CacheService<String, Object> {

  private final static Logger logger = LoggerFactory.getLogger(MemcacheServiceImpl.class);


  private final MemcachedClient cacheClient;

  private final int CACHE_ENTRY_EXIPRAION_TIME = 10;

  @Autowired
  public MemcacheServiceImpl(@Value("${cache.server.address}") String cacheAddress)
      throws IOException {
    this.cacheClient = new MemcachedClient(AddrUtil.getAddresses(cacheAddress));
    final SerializingTranscoder stc = (SerializingTranscoder) cacheClient.getTranscoder();
    stc.setCompressionThreshold(600000000);
  }

  @Override
  public Object get(String key) {
    if (StringUtils.isEmpty(key)) {
      logger.info("Key cannot be null/empty.");
      throw new IllegalArgumentException("Key cannot be null/empty.");
    }
    return cacheClient.get(key);
  }

  @Override
  public void put(String key, Object data) {

    if (StringUtils.isEmpty(key)) {
      logger.info("Key cannot be null/empty.");
      throw new IllegalArgumentException("Key cannot be null/empty.");
    }

    if (ObjectUtils.isEmpty(data)) {
      logger.info("Object to be stored cannot be null/empty.");
      throw new IllegalArgumentException("Object to be stored cannot be null/empty.");
    }
    cacheClient.add(key, CACHE_ENTRY_EXIPRAION_TIME, data);
  }

  @Override
  public void remove(String key) {

    if (StringUtils.isEmpty(key)) {
      logger.info("Key cannot be null/empty.");
      throw new IllegalArgumentException("Key cannot be null/empty.");
    }
    cacheClient.delete(key);
  }

}
