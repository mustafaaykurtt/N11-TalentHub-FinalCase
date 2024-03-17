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
 * Date:15.03.2024
 * Time:00:14
 */

@Configuration
@EnableSolrRepositories(
        basePackages = "com.n11.restaurantservice",
        namedQueriesLocation = ""
)
@ComponentScan
public class SolrConfig {

    @Bean
    public SolrClient solrClient(){
        return new HttpSolrClient.Builder("http://localhost:8983/solr").build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient){
        return new SolrTemplate(solrClient);
    }


}
