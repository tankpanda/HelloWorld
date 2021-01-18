package com.hhd.es.config;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.context.annotation.Bean;

/**
 * Created by huhengda on 2021/1/18.
 */
public class EsClientFactory {
    // or get from config file
    private static final String HOSTS = "localhost:9200,localhost:10200";

    private static volatile EsClientFactory esClientFactory;
    private RestClientBuilder builder;
    private volatile RestHighLevelClient highClient;
    private RestClient restClient;

    private EsClientFactory() {}

    public static EsClientFactory getInstance() {
        if (esClientFactory == null) {
            synchronized (EsClientFactory.class) {
                if (esClientFactory == null) {
                    esClientFactory = new EsClientFactory();
                    esClientFactory.init();
                }
            }
        }
        return esClientFactory;
    }

    public RestClientBuilder init() {
        String[] hostNamePort = HOSTS.split(",");
        String host;
        int port;
        String[] temp;

        RestClientBuilder restClientBuilder = null;

        if (hostNamePort.length > 0) {
            for (String hostPort : hostNamePort) {
                temp = hostPort.split(":");
                host = temp[0].trim();
                port = Integer.parseInt(temp[1].trim());
                restClientBuilder = RestClient.builder(new HttpHost(host, port, "http"));
            }
        }

        Header[] headers = new Header[]{new BasicHeader("Content-Type", "application/json")};
        restClientBuilder.setDefaultHeaders(headers);

        SniffOnFailureListener sniffOnFailureListener = new SniffOnFailureListener();
        // 每个60s嗅探一次 默认5分钟 若失败 30s嗅探一次
        // https 需要setNodesSniffer
        Sniffer sniffer = Sniffer.builder(restClientBuilder.build()).setSniffIntervalMillis(60000).setSniffAfterFailureDelayMillis(30000).build();
        sniffOnFailureListener.setSniffer(sniffer);
        restClientBuilder.setFailureListener(sniffOnFailureListener);

        restClientBuilder.setRequestConfigCallback(restConfigBuilder -> restConfigBuilder.setSocketTimeout(10000));

        return restClientBuilder;
    }

    /**
     * 高级api
     * @return
     */
//    @Bean
    public RestHighLevelClient getHighLevelClient() {
        if (highClient == null) {
            synchronized (EsClientFactory.class) {
                if (highClient == null) {
                    highClient = new RestHighLevelClient(builder);
                }
            }
        }
        return highClient;
    }

    /**
     * 获取低级api
     * @return
     */
//    @Bean
    public RestClient getLowLevelClient() {
        return restClient;
    }
}
