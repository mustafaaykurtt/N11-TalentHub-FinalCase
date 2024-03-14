package com.n11.restaurantservice.repository;

import com.n11.restaurantservice.model.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Created By Mustafa Aykurt
 * Date:14.03.2024
 * Time:03:40
 */

public interface RestaurantRepository extends SolrCrudRepository<Restaurant,String> {
}
