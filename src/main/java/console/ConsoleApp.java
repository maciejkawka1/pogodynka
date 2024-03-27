package console;

import api.accuweatherapi.AccuWeatherApi;
import api.accuweatherapi.AccuWeatherApiLocationResponse;
import api.openmeteoapi.OpenMeteoApi;
import api.openmeteoapi.OpenMeteoApiResults;
import api.openmeteoapi.OpenMeteoVariables;

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
                System.out.println(OpenMeteoVariables.RELATIVE_HUMIDITY.getUserInfo());
                System.out.println(Arrays.toString(result.getHourly().getRelative_humidity_2m()));
                System.out.println(OpenMeteoVariables.SEA_LEVEL_PRESSURE.getUserInfo());
                System.out.println(Arrays.toString(result.getHourly().getPressure_msl()));
                System.out.println(OpenMeteoVariables.WIND_SPEED.getUserInfo());
                System.out.println(Arrays.toString(result.getHourly().getWind_speed_10m()));
                System.out.println(OpenMeteoVariables.WIND_DIRECTION.getUserInfo());
                System.out.println(Arrays.toString(result.getHourly().getWind_direction_10m()));

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
