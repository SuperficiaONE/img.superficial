package com.superficial.img.common.config;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

public class EsFactory implements InitializingBean {

  private Logger LOGGER = LoggerFactory.getLogger(EsFactory.class);
  private JestClient jestClient;

  @Value("${es.conn_timeout}")
  private int esConnectionTimeout;

  @Value("${es.read_timeout}")
  private int esReadTimeout;

  @Value("${es.address}")
  private String[] address;

  @Override
  public void afterPropertiesSet() throws Exception {
    Set<String> servers = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    for (String s : this.address) {
      servers.add(s.trim().toLowerCase());
      sb.append(s).append(",");
    }
    JestClientFactory factory = new JestClientFactory();
    factory.setHttpClientConfig(new HttpClientConfig.Builder(servers)
        .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
        .connTimeout(esConnectionTimeout)
        .readTimeout(esReadTimeout).multiThreaded(true).build());
    jestClient = factory.getObject();
    LOGGER.info("ElasticSearch inited address:" + sb.toString());
  }

  public JestClient getJestClient() {
    return jestClient;
  }
}
