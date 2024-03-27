package openmeteoapi;

import api.openmeteoapi.OpenMeteoApi;
import api.openmeteoapi.OpenMeteoVariables;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenMeteoApiTest extends TestCase {


    @Test
    void canCreateCorrectUrl()  {

        List<OpenMeteoVariables> list = new ArrayList<>();
        list.add(OpenMeteoVariables.TEMPERATURE);
        list.add(OpenMeteoVariables.RELATIVE_HUMIDITY);
        list.add(OpenMeteoVariables.SEA_LEVEL_PRESSURE);
        list.add(OpenMeteoVariables.WIND_SPEED);
        list.add(OpenMeteoVariables.WIND_DIRECTION);

        OpenMeteoApi openMeteoApi = new OpenMeteoApi(52.2298,21.0118,list);

        assertEquals(
                "https://api.open-meteo.com/v1/forecast" +
                        "?latitude=52.2298" +
                        "&longitude=21.0118" +
                        "&hourly=temperature_2m,relative_humidity_2m,pressure_msl,wind_speed_10m,wind_direction_10m" +
                        "&forecast_days=1",
                openMeteoApi.prepareUrl());

    }

    @Test
    void canConnectAndReadData()  {

        List<OpenMeteoVariables> list = new ArrayList<>();
        list.add(OpenMeteoVariables.TEMPERATURE);
        list.add(OpenMeteoVariables.RELATIVE_HUMIDITY);
        list.add(OpenMeteoVariables.WIND_SPEED);

        OpenMeteoApi openMeteoApi = new OpenMeteoApi(52.2298,21.0118,list);
        try {
            System.out.println(openMeteoApi.getAllData());
        }catch (IOException ioException){
            System.out.println( ioException.getMessage());
        }

    }

}