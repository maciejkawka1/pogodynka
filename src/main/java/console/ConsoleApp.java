package console;

import api.accuweatherapi.AccuWeatherApi;
import api.accuweatherapi.AccuWeatherApiLocationResponse;
import api.openmeteoapi.OpenMeteoApi;
import api.openmeteoapi.OpenMeteoApiResults;
import api.openmeteoapi.OpenMeteoVariables;
import chart.Chart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private AccuWeatherApiLocationResponse searchingLocation;


    private  AccuWeatherApiLocationResponse getLocalization(Scanner scanner){

        System.out.print("Podaj nazwę miasta dla którego chcesz sprawdzić pogodę: ");
        String city = scanner.nextLine();

        AccuWeatherApi accuWeatherApi = new AccuWeatherApi();

        while (true) {
            AccuWeatherApiLocationResponse[] responses;
            try {
                responses = accuWeatherApi.getLocationsByCityName(city);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (responses.length == 0) {
                System.out.print("Nie znaleziono miasta, podaj jeszcze raz: ");
                city = scanner.nextLine();
            } else {
                return responses[0];
            }
        }
    }
    public void startApp() {

        Scanner scanner = new Scanner(System.in);

        searchingLocation = getLocalization(scanner);


        List<OpenMeteoVariables> list = new ArrayList<>();
        list.add(OpenMeteoVariables.TEMPERATURE);
        list.add(OpenMeteoVariables.RELATIVE_HUMIDITY);
        list.add(OpenMeteoVariables.SEA_LEVEL_PRESSURE);
        list.add(OpenMeteoVariables.WIND_SPEED);
        list.add(OpenMeteoVariables.WIND_DIRECTION);


        OpenMeteoApi openMeteoApi = new OpenMeteoApi(searchingLocation.getGeoPosition().getLatitude(),
                searchingLocation.getGeoPosition().getLongitude(),
                list);

        try {
            OpenMeteoApiResults result = openMeteoApi.getAllData();
            if(result != null){

                System.out.println("Dla miasta: "+searchingLocation.getLocalizedName());
                System.out.println("W kraju: " + searchingLocation.getCountry().getLocalizedName());
                System.out.println("Reionu: " + searchingLocation.getRegion().getLocalizedName());
                System.out.println("Znaleziono następujące dane:");

                System.out.println("Czas:");
                System.out.println(Arrays.toString(result.getHourly().getTime()));
                System.out.println(OpenMeteoVariables.TEMPERATURE.getUserInfo());
                System.out.println(Arrays.toString(result.getHourly().getTemperature_2m()));

                Chart chart1 = new Chart(
                        OpenMeteoVariables.TEMPERATURE.getUserInfo());

                chart1.addDataset(
                        result.getHourly().getTime(),
                        result.getHourly().getTemperature_2m(),
                        OpenMeteoVariables.TEMPERATURE.getUnit()
                        );

                chart1.showChart();


                Chart chart2 = new Chart(
                        OpenMeteoVariables.RELATIVE_HUMIDITY.getUserInfo());

                chart2.addDataset(
                        result.getHourly().getTime(),
                        result.getHourly().getRelative_humidity_2m(),
                        OpenMeteoVariables.RELATIVE_HUMIDITY.getUnit()
                );

                chart2.showChart();

                Chart chart3 = new Chart(
                        OpenMeteoVariables.SEA_LEVEL_PRESSURE.getUserInfo());
                chart3.addDataset(
                        result.getHourly().getTime(),
                        result.getHourly().getPressure_msl(),
                        OpenMeteoVariables.SEA_LEVEL_PRESSURE.getUnit());
                chart3.showChart();

                Chart chart4 = new Chart(
                        OpenMeteoVariables.WIND_SPEED.getUserInfo());
                chart4.addDataset(
                        result.getHourly().getTime(),
                        result.getHourly().getWind_speed_10m(),
                        OpenMeteoVariables.WIND_SPEED.getUnit());
                chart4.showChart();

                Chart chart5 = new Chart(
                        OpenMeteoVariables.WIND_DIRECTION.getUserInfo());
                chart5.addDataset(
                        result.getHourly().getTime(),
                        result.getHourly().getWind_direction_10m(),
                        OpenMeteoVariables.WIND_DIRECTION.getUnit());
                chart5.showChart();

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void showChart(String fileTitle, String label, String[] x, String unit, Double[] y){

        Chart chart = new Chart(fileTitle);
        chart.addDataset(x,y,unit);

    }

}
