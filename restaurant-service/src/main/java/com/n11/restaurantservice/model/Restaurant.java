package com.n11.restaurantservice.model;

import com.n11.restaurantservice.util.enums.PaymentMethods;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.time.LocalDateTime;

/**
 * Created By Mustafa Aykurt
 * Date:14.03.2024
 * Time:03:13
 */

@SolrDocument(collection = "restaurants")
@Getter
@Setter
public class Restaurant {

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

    @Indexed(name = "paymentMethod", type = "tmulti")
    private PaymentMethods paymentMethod;

    @Indexed(name = "latitude", type = "tpoint")
    private Double latitude;

    @Indexed(name = "longitude", type = "tpoint")
    private Double longitude;

    @Indexed(name = "createDate", type = "pdate")
    private LocalDateTime createDate;

    @Indexed(name = "updateDate", type = "pdate")
    private LocalDateTime updateDate;


}
