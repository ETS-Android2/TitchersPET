package pt.ubi.di.pmd.titcherspet;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Tests{

    @Mock
    // WeatherHelper's dependency
    private HTTPRequester http;

    @InjectMocks
    // Mockito + JUnit
    private WeatherHelper helper = new WeatherHelper();

    // JUnit
    private InputChecker checker = new InputChecker();

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
    }

    // TEST WEATHER FORECAST
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testJSONParser(){ // test the parsing ability of the WeatherHelper class

        String expected_JSON = "{\"DateTime\":\"2019-12-05T12:00:00+00:00\",\"EpochDateTime\":1575547200,\"WeatherIcon\":3,\"IconPhrase\":\"Partly sunny\",\"HasPrecipitation\":false,\"IsDaylight\":true,\"Temperature\":{\"Value\":47.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=12&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=12&lang=en-us\"},{\"DateTime\":\"2019-12-05T13:00:00+00:00\",\"EpochDateTime\":1575550800,\"WeatherIcon\":3,\"IconPhrase\":\"Partly sunny\",\"HasPrecipitation\":false,\"IsDaylight\":true,\"Temperature\":{\"Value\":49.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=13&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=13&lang=en-us\"},{\"DateTime\":\"2019-12-05T14:00:00+00:00\",\"EpochDateTime\":1575554400,\"WeatherIcon\":3,\"IconPhrase\":\"Partly sunny\",\"HasPrecipitation\":false,\"IsDaylight\":true,\"Temperature\":{\"Value\":51.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=14&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=14&lang=en-us\"},{\"DateTime\":\"2019-12-05T15:00:00+00:00\",\"EpochDateTime\":1575558000,\"WeatherIcon\":2,\"IconPhrase\":\"Mostly sunny\",\"HasPrecipitation\":false,\"IsDaylight\":true,\"Temperature\":{\"Value\":51.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=15&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=15&lang=en-us\"},{\"DateTime\":\"2019-12-05T16:00:00+00:00\",\"EpochDateTime\":1575561600,\"WeatherIcon\":2,\"IconPhrase\":\"Mostly sunny\",\"HasPrecipitation\":false,\"IsDaylight\":true,\"Temperature\":{\"Value\":49.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=16&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=16&lang=en-us\"},{\"DateTime\":\"2019-12-05T17:00:00+00:00\",\"EpochDateTime\":1575565200,\"WeatherIcon\":2,\"IconPhrase\":\"Mostly sunny\",\"HasPrecipitation\":false,\"IsDaylight\":true,\"Temperature\":{\"Value\":46.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=17&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=17&lang=en-us\"},{\"DateTime\":\"2019-12-05T18:00:00+00:00\",\"EpochDateTime\":1575568800,\"WeatherIcon\":34,\"IconPhrase\":\"Mostly clear\",\"HasPrecipitation\":false,\"IsDaylight\":false,\"Temperature\":{\"Value\":45.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=18&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=18&lang=en-us\"},{\"DateTime\":\"2019-12-05T19:00:00+00:00\",\"EpochDateTime\":1575572400,\"WeatherIcon\":33,\"IconPhrase\":\"Clear\",\"HasPrecipitation\":false,\"IsDaylight\":false,\"Temperature\":{\"Value\":43.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":0,\"MobileLink\":\"http://m.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=19&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/pt/covilha/1-272687_1_al/hourly-weather-forecast/1-272687_1_al?day=1&hbhhour=19&lang=en-us\"}";

        when(http.getWeather()).thenReturn(expected_JSON);

        assertArrayEquals(new String[]{"13","49.0","Partly sunny"},helper.parseJSON(0));
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // TEST USER INPUTS - PHONE NUMBER
    // ------------------------------------------------------------------------------------
    @Test
    public void testInputChecker_PhoneNumber_true1(){

        String input_example = "912345678";

        assertEquals(true,checker.inputCheck(input_example,"phone_number"));
    }

    @Test
    public void testInputChecker_PhoneNumber_false1(){

        String input_example = "";

        assertEquals(false,checker.inputCheck(input_example,"phone_number"));
    }

    @Test
    public void testInputChecker_PhoneNumber_false2(){

        String input_example = "91234567";

        assertEquals(false,checker.inputCheck(input_example,"phone_number"));
    }

    @Test
    public void testInputChecker_PhoneNumber_false3(){

        String input_example = "abcdefghi";

        assertEquals(false,checker.inputCheck(input_example,"phone_number"));
    }

    @Test
    public void testInputChecker_PhoneNumber_false4(){

        String input_example = "9876abcde";

        assertEquals(false,checker.inputCheck(input_example,"phone_number"));
    }

    @Test
    public void testInputChecker_PhoneNumber_false5(){

        String input_example = "!#$%&/()=";

        assertEquals(false,checker.inputCheck(input_example,"phone_number"));
    }

    @Test
    public void testInputChecker_PhoneNumber_false6(){

        String input_example = "98765!#$%";

        assertEquals(false,checker.inputCheck(input_example,"phone_number"));
    }
    // -------------------------------------------------------------------------------------

    // TEST USER INPUTS - EMAIL
    // ------------------------------------------------------------------------------
    @Test
    public void testInputChecker_Email_true1(){

        String input_example = "teste@gmail.com";

        assertEquals(true,checker.inputCheck(input_example,"email"));
    }

    @Test
    public void testInputChecker_Email_true2(){

        String input_example = "teste@ubi.com";

        assertEquals(true,checker.inputCheck(input_example,"email"));
    }

    @Test
    public void testInputChecker_Email_true3(){

        String input_example = "teste@hotmail.com";

        assertEquals(true,checker.inputCheck(input_example,"email"));
    }

    @Test
    public void testInputChecker_Email_false1(){

        String input_example = "";

        assertEquals(false,checker.inputCheck(input_example,"email"));
    }

    @Test
    public void testInputChecker_Email_false2(){

        String input_example = "testehotmail.com";

        assertEquals(false,checker.inputCheck(input_example,"email"));
    }

    @Test
    public void testInputChecker_Email_false3(){

        String input_example = "teste@gmailcom";

        assertEquals(false,checker.inputCheck(input_example,"email"));
    }

    @Test
    // should fail on purpose
    public void testInputChecker_Email_false4(){

        String input_example = "teste@gmail.random";

        assertEquals(false,checker.inputCheck(input_example,"email"));
    }

    @Test
    public void testInputChecker_Email_false5(){

        String input_example = "te%&@gmail.com";

        assertEquals(false,checker.inputCheck(input_example,"email"));
    }

    @Test
    public void testInputChecker_Email_false6(){

        String input_example = "teste@hotm#?l.com";

        assertEquals(false,checker.inputCheck(input_example,"email"));
    }
    // ------------------------------------------------------------------------------

    // TEST USER INPUTS - NAME
    // -----------------------------------------------------------------------------
    @Test
    public void testInputChecker_Name_true1(){

        String input_example = "Maria";

        assertEquals(true,checker.inputCheck(input_example,"name"));
    }

    @Test
    public void testInputChecker_Name_true2(){

        String input_example = "maria";

        assertEquals(true,checker.inputCheck(input_example,"name"));
    }

    @Test
    public void testInputChecker_Name_true3(){

        String input_example = "Maria Silva";

        assertEquals(true,checker.inputCheck(input_example,"name"));
    }

    @Test
    public void testInputChecker_Name_false1(){

        String input_example = "";

        assertEquals(false,checker.inputCheck(input_example,"name"));
    }

    @Test
    public void testInputChecker_Name_false2(){

        String input_example = "12345";

        assertEquals(false,checker.inputCheck(input_example,"name"));
    }

    @Test
    public void testInputChecker_Name_false3(){

        String input_example = "Maria123";

        assertEquals(false,checker.inputCheck(input_example,"name"));
    }

    @Test
    public void testInputChecker_Name_false4(){

        String input_example = "#@!?=";

        assertEquals(false,checker.inputCheck(input_example,"name"));
    }

    @Test
    public void testInputChecker_Name_false5(){

        String input_example = "Maria#@!?=";

        assertEquals(false,checker.inputCheck(input_example,"name"));
    }
    // -----------------------------------------------------------------------------

    // TEST USER INPUTS - PASSWORD
    // --------------------------------------------------------------------------------
    @Test
    public void testInputChecker_Password_true1(){

        String input_example = "P@ssword1";

        assertEquals(true,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_true2(){

        String input_example = "#passwoRD123";

        assertEquals(true,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_false1(){

        String input_example = "";

        assertEquals(false,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_false2(){

        String input_example = "P@ss1";

        assertEquals(false,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_false3(){

        String input_example = "password";

        assertEquals(false,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_false4(){

        String input_example = "12345";

        assertEquals(false,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_false5(){

        String input_example = "#@%&=";

        assertEquals(false,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_false6(){

        String input_example = "password123";

        assertEquals(false,checker.inputCheck(input_example,"password"));
    }

    @Test
    public void testInputChecker_Password_false7(){

        String input_example = "PASSWORD123";

        assertEquals(false,checker.inputCheck(input_example,"password"));
    }
    // ---------------------------------------------------------------------------------

}
