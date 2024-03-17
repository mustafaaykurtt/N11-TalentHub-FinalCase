package com.n11.restaurantservice.model;


import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.geo.Point;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created By Mustafa Aykurt
 * Date:15.03.2024
 * Time:00:15
 */
@Getter
@Setter
@SolrDocument(solrCoreName = "n11_restaurants")
public class Restaurant implements Serializable {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "address", type = "string")
    private String address;

    @Indexed(name = "city", type = "string")
    private String city;

    @Indexed(name = "district", type = "string")
    private String district;

    @Indexed(name = "country", type = "string")
    private String country;

    @Indexed(name = "location", type = "location")
    private Point location;

    @Indexed(name = "createDate", type = "pdate")
    private LocalDateTime createDate;

    @Indexed(name = "updateDate", type = "pdate")
    private LocalDateTime updateDate;

    @Indexed(name = "rating", type = "tdouble")
    private Double rating;

}
