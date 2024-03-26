import com.google.gson.Gson;
import openmeteoapi.OpenMeteoApi;
import openmeteoapi.OpenMeteoApiResults;
import openmeteoapi.OpenMeteoVariables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();

        List<OpenMeteoVariables> list = new ArrayList<>();
        list.add(OpenMeteoVariables.TEMPERATURE);
        list.add(OpenMeteoVariables.RELATIVE_HUMIDITY);
        list.add(OpenMeteoVariables.SEA_LEVEL_PRESSURE);
        list.add(OpenMeteoVariables.WIND_SPEED);
        list.add(OpenMeteoVariables.WIND_DIRECTION);

        OpenMeteoApi openMeteoApi = new OpenMeteoApi(52.2298,21.0118,list);

        try {
            String result = openMeteoApi.getAllData();

           OpenMeteoApiResults openMeteoApiResults = gson.fromJson(result, OpenMeteoApiResults.class);

            openMeteoApiResults.getHourly().getTime().forEach(System.out::println);
            openMeteoApiResults.getHourly().getTemperature_2m().forEach(System.out::println);
            openMeteoApiResults.getHourly().getRelative_humidity_2m().forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
