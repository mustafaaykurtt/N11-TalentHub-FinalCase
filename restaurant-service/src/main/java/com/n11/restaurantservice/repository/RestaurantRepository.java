package com.n11.restaurantservice.repository;

import com.n11.restaurantservice.model.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * Created By Mustafa Aykurt
 * Date:15.03.2024
 * Time:00:33
 */

public interface RestaurantRepository extends SolrCrudRepository<Restaurant,String> {
}
