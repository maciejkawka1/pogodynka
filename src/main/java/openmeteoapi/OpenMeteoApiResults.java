package openmeteoapi;

import java.util.List;

public class OpenMeteoApiResults {

    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private int elevation;
    private HourlyUnits hourly_units;
    private HourlyData hourly;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGenerationtime_ms() {
        return generationtime_ms;
    }

    public void setGenerationtime_ms(double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(int utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public HourlyUnits getHourly_units() {
        return hourly_units;
    }

    public void setHourly_units(HourlyUnits hourly_units) {
        this.hourly_units = hourly_units;
    }

    public HourlyData getHourly() {
        return hourly;
    }

    public void setHourly(HourlyData hourly) {
        this.hourly = hourly;
    }


    public static class HourlyUnits {
        private String time;
        private String temperature_2m;
        private String relative_humidity_2m;
        private String pressure_msl;
        private String wind_speed_10m;
        private String wind_direction_10m;


    }


    public static class HourlyData {
        private List<String> time;
        private List<Double> temperature_2m;
        private List<Integer> relative_humidity_2m;
        private List<Double> pressure_msl;
        private List<Double> wind_speed_10m;
        private List<Integer> wind_direction_10m;

        public List<String> getTime() {
            return time;
        }

        public List<Double> getTemperature_2m() {
            return temperature_2m;
        }

        public List<Integer> getRelative_humidity_2m() {
            return relative_humidity_2m;
        }

        public List<Double> getPressure_msl() {
            return pressure_msl;
        }

        public List<Double> getWind_speed_10m() {
            return wind_speed_10m;
        }

        public List<Integer> getWind_direction_10m() {
            return wind_direction_10m;
        }
    }
}
