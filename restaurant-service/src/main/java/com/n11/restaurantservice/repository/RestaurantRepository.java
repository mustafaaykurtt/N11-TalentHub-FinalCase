package com.n11.restaurantservice.repository;

import com.n11.restaurantservice.model.Restaurant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.query.Param;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * Created By Mustafa Aykurt
 * Date:15.03.2024
 * Time:00:33
 */

public interface RestaurantRepository extends SolrCrudRepository<Restaurant,String> {
    List<Restaurant> findByLocationNear(Point point, Distance distance);
}
