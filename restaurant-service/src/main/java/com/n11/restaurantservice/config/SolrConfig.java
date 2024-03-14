package com.n11.restaurantservice.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * Created By Mustafa Aykurt
 * Date:14.03.2024
 * Time:03:42
 */

@Configuration
@EnableSolrRepositories(
        basePackages = "com.n11.restaurantservice",
        namedQueriesLocation = ""
)
@ComponentScan
public class SolrConfig {

    @Bean
    public SolrClient solrClient() {
    return new HttpSolrClient.Builder("http://localhost:8993/solr").build();
    }

    @Bean
    public SolrTemplate solrTemplate() {
        return new SolrTemplate(solrClient());
    }

}
