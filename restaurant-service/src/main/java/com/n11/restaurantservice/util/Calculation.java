package com.n11.restaurantservice.util;

/**
 * Created By Mustafa Aykurt
 * Date:17.03.2024
 * Time:03:58
 */

public final class Calculation {

    public static final Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        // Radius of the earth
        final int R = 6371;
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;
        return distance;
    }
}
