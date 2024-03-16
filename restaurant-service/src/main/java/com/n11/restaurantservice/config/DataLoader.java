package com.n11.restaurantservice.config;

import com.n11.restaurantservice.model.Restaurant;
import com.n11.restaurantservice.repository.RestaurantRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

/**
 * Created By Mustafa Aykurt
 * Date:16.03.2024
 * Time:03:04
 */

@Component
public class DataLoader implements ApplicationRunner {

    private RestaurantRepository restaurantRepository;

    public DataLoader(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadUsersFromCsv();
    }

    private void loadUsersFromCsv() {
        try (InputStream inputStream = getClass().getResourceAsStream("/restaurant.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Restaurant restaurant = new Restaurant();
                restaurant.setLatitude(Double.valueOf(data[0]));
                restaurant.setLongitude(Double.valueOf(data[1]));
                restaurant.setName(data[2]);
                restaurant.setAddress(data[3]);
                restaurant.setCity(data[4]);
                restaurant.setDistrict(data[5]);
                restaurant.setCountry(data[6]);
                restaurant.setRating(Double.valueOf(data[7]));
                restaurant.setUpdateDate(LocalDateTime.now());
                restaurant.setCreateDate(LocalDateTime.now());
                restaurantRepository.save(restaurant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
