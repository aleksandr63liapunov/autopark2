package com.example.autopark.service;

import org.springframework.stereotype.Service;

@Service
public class GPSLogService {

    public static double calculateDistanceTraveled(String gpsData) {
        String[] lines = gpsData.split("\\r?\\n");
        double distance = 0;

        for (int i = 1; i < lines.length; i++) {
            String[] start = lines[i - 1].split(",");
            String[] end = lines[i].split(",");


            if (end.length >= 8 && end[0].equals("$GNVTG")) {
                double speed = parseSpeed(end[7]);
                if (speed != -1 && speed != 0) {
                    double lat1 = parseCoordinate(start[2]); // Извлекаем широту из предыдущей строки
                    double lon1 = parseCoordinate(start[4]); // Извлекаем долготу из предыдущей строки
                    double lat2 = parseCoordinate(end[2]);    // Извлекаем широту из текущей строки
                    double lon2 = parseCoordinate(end[4]);    // Извлекаем долготу из текущей строки
                    distance += calculateDistance(lat1, lon1, lat2, lon2);
                }
            }
        }
        return distance;
    }

    private static double parseCoordinate(String coordinate) {
        try {
            return Double.parseDouble(coordinate);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static double parseSpeed(String speed) {
        try {
            return Double.parseDouble(speed);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c ;
    }
}
