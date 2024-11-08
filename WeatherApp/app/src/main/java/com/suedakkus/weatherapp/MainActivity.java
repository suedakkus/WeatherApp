package com.suedakkus.weatherapp;

import static java.util.Calendar.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import android.Manifest;
import android.content.pm.PackageManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Calendar;

//#955BC2 button colour
public class MainActivity extends AppCompatActivity {
    ConstraintLayout allLayout;
    EditText etCity;
    TextView tvResult;
    TextView tvFeelsLike;
    TextView tvDescription;
    TextView tvCityName;
    TextView tvHumidity;
    TextView tvWindSpeed;
    TextView tvPressure;
    TextView tvCloudiness;
    ImageView ivWeatherIcon;
    LinearLayout linearLayoutHumSpedPres;
    LinearLayout linearLayoutsunsetAndSunrise;
    LinearLayout hourlyforecastLayout;
    ImageView ivHourlyForecast1;
    TextView tvTemp1,tvHour1,tvSped1,tvHumidity1;
    ImageView ivHourlyForecast2;
    TextView tvTemp2,tvHour2,tvSped2,tvHumidity2;
    ImageView ivHourlyForecast3;
    TextView tvTemp3,tvHour3,tvSped3,tvHumidity3;
    ImageView ivHourlyForecast4;
    TextView tvTemp4,tvHour4,tvSped4,tvHumidity4;
    ImageView ivHourlyForecast5;
    TextView tvTemp5,tvHour5,tvSped5,tvHumidity5;
    ImageView ivHourlyForecast6;
    TextView tvTemp6,tvHour6,tvSped6,tvHumidity6;
    ImageView ivHourlyForecast7;
    TextView tvTemp7,tvHour7,tvSped7,tvHumidity7;
    ImageView ivHourlyForecast8;
    TextView tvTemp8,tvHour8,tvSped8,tvHumidity8;
    ImageView ivHourlyForecast9;
    TextView tvTemp9,tvHour9,tvSped9,tvHumidity9;
    TextView tvSunrise;
    TextView tvSunset;
    TextView tvCurrentTime;
    ImageView ivHumidity, ivWindsped, ivPressure, ivCloudiness;
    LinearLayout fourhoursForecast;
    TextView tvDateOne,tvDateTwo,tvDateThree,tvDateFour;
    TextView tvDescriptionOne1,tvDescriptionTwo1,tvDescriptionThree1,tvDescriptionFour1;
    TextView tvDescriptionOne2,tvDescriptionTwo2,tvDescriptionThree2,tvDescriptionFour2;
    TextView tvDescriptionOne3,tvDescriptionTwo3,tvDescriptionThree3,tvDescriptionFour3;
    TextView tvDescriptionOne4,tvDescriptionTwo4,tvDescriptionThree4,tvDescriptionFour4;
    TextView tvHourOne1,tvHourTwo1,tvHourThree1,tvHourFour1;
    TextView tvHourOne2,tvHourTwo2,tvHourThree2,tvHourFour2;
    TextView tvHourOne3,tvHourTwo3,tvHourThree3,tvHourFour3;
    TextView tvHourOne4,tvHourTwo4,tvHourThree4,tvHourFour4;
    TextView tvForecastTemp1,tvForecastTemp2,tvForecastTemp3,tvForecastTemp4,tvForecastTemp11,
    tvForecastTemp22,tvForecastTemp33,tvForecastTemp44,tvForecastTemp111,tvForecastTemp222,tvForecastTemp333,
    tvForecastTemp444,tvForecastTemp1111,tvForecastTemp2222,tvForecastTemp3333,tvForecastTemp4444;

    ImageView ivDayOneone, ivDayOnetwo,ivDayOnethree,ivDayOnefour,ivDayOne2,ivDayTwo2,ivDayThree2,ivDayFour2;
    ImageView ivDayone3, ivDaytwo3,ivDaythree3,ivDayfour3,ivDayOne4,ivDayTwo4,ivDayThree4,ivDayFour4;
    LinearLayout forecastDay1, forecastDay2,forecastDay3,forecastDay4;



    private final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final String forecastUrl = "https://api.openweathermap.org/data/2.5/forecast";
    private final String appid = "e22a3911ae01c3006780439bec6e4de8";
    DecimalFormat df = new DecimalFormat("#.##");

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;
    private double longitude;
    private double latitude;

    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;

    long sunsetData;
    String[] weatherDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allLayout = findViewById(R.id.allLayout);
        etCity = findViewById(R.id.etCity);
        tvResult = findViewById(R.id.tvResult);
        tvFeelsLike = findViewById(R.id.tvFeelsLikeDegree);
        tvDescription = findViewById(R.id.tvDescription);
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon);
        tvCityName = findViewById(R.id.tvCityName);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvPressure = findViewById(R.id.tvPressure);
        tvCloudiness = findViewById(R.id.tvCloudiness);
        linearLayoutHumSpedPres = findViewById(R.id.linearLayoutHumSpedPres);
        tvSunrise = findViewById(R.id.tvSunrise);
        tvSunset = findViewById(R.id.tvSunset);
        linearLayoutsunsetAndSunrise = findViewById(R.id.linearLayoutsunriseAndSunset);
        ivHourlyForecast1 = findViewById(R.id.ivHourlyForecast1);
        tvHour1 = findViewById(R.id.tvHour1);
        tvTemp1 = findViewById(R.id.tvTemp1);
        tvSped1 = findViewById(R.id.tvSped1);
        tvHumidity1 = findViewById(R.id.tvHumidity1);

        hourlyforecastLayout = findViewById(R.id.hourlyforecastLayout);

        ivHourlyForecast2 = findViewById(R.id.ivHourlyForecast2);
        tvHour2 = findViewById(R.id.tvHour2);
        tvTemp2 = findViewById(R.id.tvTemp2);
        tvSped2 = findViewById(R.id.tvSped2);
        tvHumidity2 = findViewById(R.id.tvHumidity2);

        ivHourlyForecast3 = findViewById(R.id.ivHourlyForecast3);
        tvHour3 = findViewById(R.id.tvHour3);
        tvTemp3 = findViewById(R.id.tvTemp3);
        tvSped3 = findViewById(R.id.tvSped3);
        tvHumidity3 = findViewById(R.id.tvHumidity3);

        ivHourlyForecast4 = findViewById(R.id.ivHourlyForecast4);
        tvHour4 = findViewById(R.id.tvHour4);
        tvTemp4 = findViewById(R.id.tvTemp4);
        tvSped4 = findViewById(R.id.tvSped4);
        tvHumidity4 = findViewById(R.id.tvHumidity4);

        ivHourlyForecast5 = findViewById(R.id.ivHourlyForecast5);
        tvHour5 = findViewById(R.id.tvHour5);
        tvTemp5 = findViewById(R.id.tvTemp5);
        tvSped5 = findViewById(R.id.tvSped5);
        tvHumidity5 = findViewById(R.id.tvHumidity5);

        ivHourlyForecast6 = findViewById(R.id.ivHourlyForecast6);
        tvHour6 = findViewById(R.id.tvHour6);
        tvTemp6 = findViewById(R.id.tvTemp6);
        tvSped6 = findViewById(R.id.tvSped6);
        tvHumidity6 = findViewById(R.id.tvHumidity6);

        ivHourlyForecast7 = findViewById(R.id.ivHourlyForecast7);
        tvHour7 = findViewById(R.id.tvHour7);
        tvTemp7 = findViewById(R.id.tvTemp7);
        tvSped7 = findViewById(R.id.tvSped7);
        tvHumidity7 = findViewById(R.id.tvHumidity7);

        ivHourlyForecast8 = findViewById(R.id.ivHourlyForecast8);
        tvHour8 = findViewById(R.id.tvHour8);
        tvTemp8 = findViewById(R.id.tvTemp8);
        tvSped8 = findViewById(R.id.tvSped8);
        tvHumidity8 = findViewById(R.id.tvHumidity8);

        ivHourlyForecast9 = findViewById(R.id.ivHourlyForecast9);
        tvHour9 = findViewById(R.id.tvHour9);
        tvTemp9 = findViewById(R.id.tvTemp9);
        tvSped9 = findViewById(R.id.tvSped9);
        tvHumidity9 = findViewById(R.id.tvHumidity9);

        tvCurrentTime = findViewById(R.id.tvCurrentTime);

        ivHumidity=findViewById(R.id.ivHumidity);
        ivWindsped = findViewById(R.id.ivWindsped);
        ivPressure = findViewById(R.id.ivPressure);
        ivCloudiness = findViewById(R.id.ivCloudiness);
        fourhoursForecast = findViewById(R.id.linearLayout4daysForecast);

        forecastDay1 = findViewById(R.id.forecastDay1);
        forecastDay2 = findViewById(R.id.forecastDay2);
        forecastDay3 = findViewById(R.id.forecastDay3);
        forecastDay4 = findViewById(R.id.forecastDay4);

        /*TextView tvDescriptionOne2,tvDescriptionTwo2,tvDescriptionThree2,tvDescriptionFour2;
    TextView tvDescriptionOne3,tvDescriptionTwo3,tvDescriptionThree3,tvDescriptionFour3;
    TextView tvDescriptionOne4,tvDescriptionTwo4,tvDescriptionThree4,tvDescriptionFour4;
    TextView tvHourOne1,tvHourTwo1,tvHourThree1,tvHourFour1;
    TextView tvHourOne2,tvHourTwo2,tvHourThree2,tvHourFour2;
    TextView tvHourOne3,tvHourTwo3,tvHourThree3,tvHourFour3;
    TextView tvHourOne4,tvHourTwo4,tvHourThree4,tvHourFour4;

    ImageView ivDayOneone, ivDayOnetwo,ivDayOnethree,ivDayOnefour,ivDayOne2,ivDayTwo2,ivDayThree2,ivDayFour2;
    ImageView ivDayone3, ivDaytwo3,ivDaythree3,ivDayfour3,ivDayOne4,ivDayTwo4,ivDayThree4,ivDayFour4;*/

        tvDateOne = findViewById(R.id.txtvDayone);
        tvDateTwo = findViewById(R.id.txtvDaytwo);
        tvDateThree = findViewById(R.id.txtvDaythree);
        tvDateFour = findViewById(R.id.txtvDayfour);

        ivDayOneone = findViewById(R.id.ivDayOneone);
        ivDayOnetwo = findViewById(R.id.ivDayOnetwo);
        ivDayOnethree = findViewById(R.id.ivDayOnethree);
        ivDayOnefour = findViewById(R.id.ivDayOnefour);
        tvDescriptionOne1 = findViewById(R.id.tvDescriptionOne);
        tvDescriptionTwo1 = findViewById(R.id.tvDescriptionTwo);
        tvDescriptionThree1 = findViewById(R.id.tvDescriptionThree);
        tvDescriptionFour1 = findViewById(R.id.tvDescriptionFour);
        tvHourOne1 = findViewById(R.id.tvHourOne);
        tvHourTwo1 = findViewById(R.id.tvHourTwo);
        tvHourThree1 = findViewById(R.id.tvHourThree);
        tvHourFour1 = findViewById(R.id.tvHourFour);
        tvDescriptionOne2 = findViewById(R.id.tvDescriptionOne2);
        tvDescriptionTwo2 = findViewById(R.id.tvDescriptionTwo2);
        tvDescriptionThree2 = findViewById(R.id.tvDescriptionThree2);
        tvDescriptionFour2 = findViewById(R.id.tvDescriptionFour2);
        tvHourOne2=findViewById(R.id.tvHourOne2);
        tvHourTwo2 = findViewById(R.id.tvHourTwo2);
        tvHourThree2 = findViewById(R.id.tvHourThree2);
        tvHourFour2=findViewById(R.id.tvHourFour2);
        tvDescriptionOne3 = findViewById(R.id.tvDescriptionOne3);
        tvDescriptionTwo3 = findViewById(R.id.tvDescriptionTwo3);
        tvDescriptionThree3 = findViewById(R.id.tvDescriptionThree3);
        tvDescriptionFour3 = findViewById(R.id.tvDescriptionFour3);
        tvHourOne3=findViewById(R.id.tvHourOne3);
        tvHourTwo3 = findViewById(R.id.tvHourTwo3);
        tvHourThree3 = findViewById(R.id.tvHourThree3);
        tvHourFour3=findViewById(R.id.tvHourFour3);
        tvDescriptionOne4 = findViewById(R.id.tvDescriptionOne4);
        tvDescriptionTwo4 = findViewById(R.id.tvDescriptionTwo4);
        tvDescriptionThree4 = findViewById(R.id.tvDescriptionThree4);
        tvDescriptionFour4 = findViewById(R.id.tvDescriptionFour4);
        tvHourOne4=findViewById(R.id.tvHourOne4);
        tvHourTwo4 = findViewById(R.id.tvHourTwo4);
        tvHourThree4 = findViewById(R.id.tvHourThree4);
        tvHourFour4=findViewById(R.id.tvHourFour4);
        /*
        * ivDayOne2,ivDayTwo2,ivDayThree2,ivDayFour2;
    ImageView ivDayone3, ivDaytwo3,ivDaythree3,ivDayfour3,ivDayOne4,ivDayTwo4,ivDayThree4,ivDayFour4;*/

        ivDayOne2 = findViewById(R.id.ivDayone2);
        ivDayTwo2 = findViewById(R.id.ivDaytwo1);
        ivDayThree2 = findViewById(R.id.ivDaythree2);
        ivDayFour2 = findViewById(R.id.ivDayfour2);

        ivDayone3 = findViewById(R.id.ivDayone3);
        ivDaytwo3= findViewById(R.id.ivDaytwo3);
        ivDaythree3= findViewById(R.id.ivDaythree3);
        ivDayfour3 = findViewById(R.id.ivDayfour3);

        ivDayOne4 = findViewById(R.id.ivDayone4);
        ivDayTwo4 = findViewById(R.id.ivDaytwo4);
        ivDayThree4 = findViewById(R.id.ivDaythree4);
        ivDayFour4 = findViewById(R.id.ivDayfour4);

        tvForecastTemp1=findViewById(R.id.tvForecastTemp1);
        tvForecastTemp2=findViewById(R.id.tvForecastTemp2);
        tvForecastTemp3=findViewById(R.id.tvForecastTemp3);
        tvForecastTemp4=findViewById(R.id.tvForecastTemp4);
        tvForecastTemp11=findViewById(R.id.tvForecastTemp11);
        tvForecastTemp22=findViewById(R.id.tvForecastTemp22);
        tvForecastTemp33=findViewById(R.id.tvForecastTemp33);
        tvForecastTemp44=findViewById(R.id.tvForecastTemp44);
        tvForecastTemp111=findViewById(R.id.tvForecastTemp111);
        tvForecastTemp222=findViewById(R.id.tvForecastTemp222);
        tvForecastTemp333=findViewById(R.id.tvForecastTemp333);
        tvForecastTemp444=findViewById(R.id.tvForecastTemp444);
        tvForecastTemp1111=findViewById(R.id.tvForecastTemp1111);
        tvForecastTemp2222=findViewById(R.id.tvForecastTemp2222);
        tvForecastTemp3333=findViewById(R.id.tvForecastTemp3333);
        tvForecastTemp4444=findViewById(R.id.tvForecastTemp4444);


        String tempUrl = "";
        String temppUrl = "";

        tempUrl = weatherUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + appid;
        temppUrl = forecastUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + appid;

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, temppUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                String output = "";
                try {

                    String currentDateTime ="Date: " + day + "." + month + "." + year + "    Time: " + hour + "." + minute;
                    tvCurrentTime.setText(currentDateTime);

                    JSONObject jsonResponseForecast = new JSONObject(response2);
                    JSONArray jsonArrayForecastList = jsonResponseForecast.getJSONArray("list");

                    JSONObject jsonObjectCity = jsonResponseForecast.getJSONObject("city");
                    String name = jsonObjectCity.getString("name");
                    String country = jsonObjectCity.getString("country");
                    int timezone = jsonObjectCity.getInt("timezone");
                    long sunrise = jsonObjectCity.getLong("sunrise");
                    long sunset = jsonObjectCity.getLong("sunset");

                    sunrise -= 10800;
                    sunset -= 10800;

                    String sunriseTime = unixTimestampToAMPM(sunrise,timezone);
                    String sunsetTime = unixTimestampToAMPM(sunset,timezone);
                    boolean isDaytime = isDaytime(sunrise, sunset, timezone);
                    if(isDaytime){
                        allLayout.setBackgroundResource(R.drawable.day_backgrund);
                        hourlyforecastLayout.setBackgroundResource(R.drawable.background7);
                        ivHumidity.setImageResource(R.drawable.humidityyy);
                        ivWindsped.setImageResource(R.drawable.windspeed);
                        ivPressure.setImageResource(R.drawable.barometer);
                        ivCloudiness.setImageResource(R.drawable.cloudss);
                        forecastDay1.setBackgroundResource(R.drawable.forecastbackground1);
                        forecastDay2.setBackgroundResource(R.drawable.forecastbackground2);
                        forecastDay3.setBackgroundResource(R.drawable.forecastbackground3);
                        forecastDay4.setBackgroundResource(R.drawable.forecastbackground4);
                    } else {
                        allLayout.setBackgroundResource(R.drawable.background);
                        hourlyforecastLayout.setBackgroundResource(R.drawable.background8);
                        ivHumidity.setImageResource(R.drawable.humiditywhite);
                        ivWindsped.setImageResource(R.drawable.windwhite);
                        ivPressure.setImageResource(R.drawable.barometerwhite);
                        ivCloudiness.setImageResource(R.drawable.cloudywhite);
                        forecastDay1.setBackgroundResource(R.drawable.forecastbackground5);
                        forecastDay2.setBackgroundResource(R.drawable.forecastbackground6);
                        forecastDay3.setBackgroundResource(R.drawable.forecastbackground7);
                        forecastDay4.setBackgroundResource(R.drawable.foreecastbackground8);
                    }

                    JSONObject jsonObjectForecast = jsonArrayForecastList.getJSONObject(0);

                    JSONObject mainObjectForecast = jsonObjectForecast.getJSONObject("main");
                    double temperature = mainObjectForecast.getDouble("temp") - 273.15;
                    int humidity = mainObjectForecast.getInt("humidity");

                    JSONArray weatherArrayForecast = jsonObjectForecast.getJSONArray("weather");
                    JSONObject weatherObjectForeast = weatherArrayForecast.getJSONObject(0);
                    String main = weatherObjectForeast.getString("main");
                    String description = weatherObjectForeast.getString("description");

                    JSONObject windObjectForecast = jsonObjectForecast.getJSONObject("wind");
                    String wind = windObjectForecast.getString("speed");

                    String dt_txt = jsonObjectForecast.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast1 = jsonArrayForecastList.getJSONObject(1);
                    JSONObject mainObjectForecast1 = jsonObjectForecast1.getJSONObject("main");
                    double temperature1 = mainObjectForecast1.getDouble("temp") - 273.15;
                    int humidity1 = mainObjectForecast1.getInt("humidity");

                    JSONArray weatherArrayForecast1 = jsonObjectForecast1.getJSONArray("weather");
                    JSONObject weatherObjectForeast1 = weatherArrayForecast1.getJSONObject(0);
                    String main1 = weatherObjectForeast1.getString("main");
                    String description1 = weatherObjectForeast1.getString("description");

                    JSONObject windObjectForecast1 = jsonObjectForecast1.getJSONObject("wind");
                    String wind1 = windObjectForecast1.getString("speed");

                    String dt_txt1 = jsonObjectForecast1.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast2 = jsonArrayForecastList.getJSONObject(2);
                    JSONObject mainObjectForecast2 = jsonObjectForecast2.getJSONObject("main");
                    double temperature2 = mainObjectForecast2.getDouble("temp") - 273.15;
                    int humidity2 = mainObjectForecast2.getInt("humidity");

                    JSONArray weatherArrayForecast2 = jsonObjectForecast2.getJSONArray("weather");
                    JSONObject weatherObjectForeast2 = weatherArrayForecast2.getJSONObject(0);
                    String main2 = weatherObjectForeast2.getString("main");
                    String description2 = weatherObjectForeast2.getString("description");

                    JSONObject windObjectForecast2 = jsonObjectForecast2.getJSONObject("wind");
                    String wind2 = windObjectForecast2.getString("speed");

                    String dt_txt2 = jsonObjectForecast2.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast3 = jsonArrayForecastList.getJSONObject(3);
                    JSONObject mainObjectForecast3 = jsonObjectForecast3.getJSONObject("main");
                    double temperature3 = mainObjectForecast3.getDouble("temp") - 273.15;
                    int humidity3 = mainObjectForecast3.getInt("humidity");

                    JSONArray weatherArrayForecast3 = jsonObjectForecast3.getJSONArray("weather");
                    JSONObject weatherObjectForeast3 = weatherArrayForecast3.getJSONObject(0);
                    String main3 = weatherObjectForeast3.getString("main");
                    String description3 = weatherObjectForeast3.getString("description");

                    JSONObject windObjectForecast3 = jsonObjectForecast3.getJSONObject("wind");
                    String wind3 = windObjectForecast3.getString("speed");

                    String dt_txt3 = jsonObjectForecast3.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast4 = jsonArrayForecastList.getJSONObject(4);
                    JSONObject mainObjectForecast4 = jsonObjectForecast4.getJSONObject("main");
                    double temperature4 = mainObjectForecast4.getDouble("temp") - 273.15;
                    int humidity4 = mainObjectForecast4.getInt("humidity");

                    JSONArray weatherArrayForecast4 = jsonObjectForecast4.getJSONArray("weather");
                    JSONObject weatherObjectForeast4 = weatherArrayForecast4.getJSONObject(0);
                    String main4 = weatherObjectForeast4.getString("main");
                    String description4 = weatherObjectForeast4.getString("description");

                    JSONObject windObjectForecast4 = jsonObjectForecast4.getJSONObject("wind");
                    String wind4 = windObjectForecast4.getString("speed");

                    String dt_txt4 = jsonObjectForecast4.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast5 = jsonArrayForecastList.getJSONObject(5);
                    JSONObject mainObjectForecast5 = jsonObjectForecast5.getJSONObject("main");
                    double temperature5 = mainObjectForecast5.getDouble("temp") - 273.15;
                    int humidity5 = mainObjectForecast5.getInt("humidity");

                    JSONArray weatherArrayForecast5 = jsonObjectForecast5.getJSONArray("weather");
                    JSONObject weatherObjectForeast5 = weatherArrayForecast5.getJSONObject(0);
                    String main5 = weatherObjectForeast5.getString("main");
                    String description5 = weatherObjectForeast5.getString("description");

                    JSONObject windObjectForecast5 = jsonObjectForecast5.getJSONObject("wind");
                    String wind5 = windObjectForecast5.getString("speed");

                    String dt_txt5 = jsonObjectForecast5.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast6 = jsonArrayForecastList.getJSONObject(6);
                    JSONObject mainObjectForecast6 = jsonObjectForecast6.getJSONObject("main");
                    double temperature6 = mainObjectForecast6.getDouble("temp") - 273.15;
                    int humidity6 = mainObjectForecast6.getInt("humidity");

                    JSONArray weatherArrayForecast6 = jsonObjectForecast6.getJSONArray("weather");
                    JSONObject weatherObjectForeast6 = weatherArrayForecast6.getJSONObject(0);
                    String main6 = weatherObjectForeast6.getString("main");
                    String description6 = weatherObjectForeast6.getString("description");

                    JSONObject windObjectForecast6 = jsonObjectForecast3.getJSONObject("wind");
                    String wind6 = windObjectForecast6.getString("speed");

                    String dt_txt6 = jsonObjectForecast6.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast7 = jsonArrayForecastList.getJSONObject(7);
                    JSONObject mainObjectForecast7 = jsonObjectForecast7.getJSONObject("main");
                    double temperature7 = mainObjectForecast7.getDouble("temp") - 273.15;
                    int humidity7 = mainObjectForecast7.getInt("humidity");

                    JSONArray weatherArrayForecast7 = jsonObjectForecast7.getJSONArray("weather");
                    JSONObject weatherObjectForeast7 = weatherArrayForecast7.getJSONObject(0);
                    String main7 = weatherObjectForeast7.getString("main");
                    String description7 = weatherObjectForeast7.getString("description");

                    JSONObject windObjectForecast7 = jsonObjectForecast7.getJSONObject("wind");
                    String wind7 = windObjectForecast7.getString("speed");

                    String dt_txt7 = jsonObjectForecast7.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast8 = jsonArrayForecastList.getJSONObject(8);
                    JSONObject mainObjectForecast8 = jsonObjectForecast8.getJSONObject("main");
                    double temperature8 = mainObjectForecast8.getDouble("temp") - 273.15;
                    int humidity8 = mainObjectForecast8.getInt("humidity");

                    JSONArray weatherArrayForecast8 = jsonObjectForecast8.getJSONArray("weather");
                    JSONObject weatherObjectForeast8 = weatherArrayForecast8.getJSONObject(0);
                    String main8 = weatherObjectForeast8.getString("main");
                    String description8 = weatherObjectForeast8.getString("description");

                    JSONObject windObjectForecast8 = jsonObjectForecast8.getJSONObject("wind");
                    String wind8 = windObjectForecast8.getString("speed");

                    String dt_txt8 = jsonObjectForecast8.getString("dt_txt");
                    //8.datada kaldın
                    //9.data
                    JSONObject jsonObjectForecast9 = jsonArrayForecastList.getJSONObject(9);
                    JSONObject mainObjectForecast9 = jsonObjectForecast9.getJSONObject("main");
                    double temperature9 = mainObjectForecast9.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast9 = jsonObjectForecast9.getJSONArray("weather");
                    JSONObject weatherObjectForeast9 = weatherArrayForecast9.getJSONObject(0);
                    String main9 = weatherObjectForeast9.getString("main");
                    String description9 = weatherObjectForeast9.getString("description");

                    String dt_txt9 = jsonObjectForecast9.getString("dt_txt");
                    //
                    //10.data
                    JSONObject jsonObjectForecast10 = jsonArrayForecastList.getJSONObject(11);
                    JSONObject mainObjectForecast10 = jsonObjectForecast10.getJSONObject("main");
                    double temperature10 = mainObjectForecast10.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast10 = jsonObjectForecast10.getJSONArray("weather");
                    JSONObject weatherObjectForeast10 = weatherArrayForecast10.getJSONObject(0);
                    String main10 = weatherObjectForeast10.getString("main");
                    String description10 = weatherObjectForeast10.getString("description");

                    String dt_txt10 = jsonObjectForecast10.getString("dt_txt");
                    //
                    //11.data
                    JSONObject jsonObjectForecast11 = jsonArrayForecastList.getJSONObject(13);
                    JSONObject mainObjectForecast11 = jsonObjectForecast11.getJSONObject("main");
                    double temperature11 = mainObjectForecast11.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast11 = jsonObjectForecast11.getJSONArray("weather");
                    JSONObject weatherObjectForeast11 = weatherArrayForecast11.getJSONObject(0);
                    String main11 = weatherObjectForeast11.getString("main");
                    String description11 = weatherObjectForeast11.getString("description");

                    String dt_txt11 = jsonObjectForecast11.getString("dt_txt");
                    //
                    //12.data
                    JSONObject jsonObjectForecast12= jsonArrayForecastList.getJSONObject(15);
                    JSONObject mainObjectForecast12 = jsonObjectForecast12.getJSONObject("main");
                    double temperature12 = mainObjectForecast12.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast12 = jsonObjectForecast12.getJSONArray("weather");
                    JSONObject weatherObjectForeast12 = weatherArrayForecast12.getJSONObject(0);
                    String main12 = weatherObjectForeast12.getString("main");
                    String description12 = weatherObjectForeast12.getString("description");

                    String dt_txt12 = jsonObjectForecast12.getString("dt_txt");
                    //
                    //13.data
                    JSONObject jsonObjectForecast13= jsonArrayForecastList.getJSONObject(17);
                    JSONObject mainObjectForecast13 = jsonObjectForecast13.getJSONObject("main");
                    double temperature13 = mainObjectForecast13.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast13 = jsonObjectForecast13.getJSONArray("weather");
                    JSONObject weatherObjectForeast13 = weatherArrayForecast13.getJSONObject(0);
                    String main13 = weatherObjectForeast13.getString("main");
                    String description13 = weatherObjectForeast13.getString("description");

                    String dt_txt13 = jsonObjectForecast13.getString("dt_txt");
                    //
                    //14.data
                    JSONObject jsonObjectForecast14= jsonArrayForecastList.getJSONObject(19);
                    JSONObject mainObjectForecast14 = jsonObjectForecast14.getJSONObject("main");
                    double temperature14 = mainObjectForecast13.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast14 = jsonObjectForecast14.getJSONArray("weather");
                    JSONObject weatherObjectForeast14 = weatherArrayForecast14.getJSONObject(0);
                    String main14 = weatherObjectForeast14.getString("main");
                    String description14 = weatherObjectForeast14.getString("description");

                    String dt_txt14 = jsonObjectForecast14.getString("dt_txt");
                    //
                    //15.data
                    JSONObject jsonObjectForecast15= jsonArrayForecastList.getJSONObject(21);
                    JSONObject mainObjectForecast15 = jsonObjectForecast15.getJSONObject("main");
                    double temperature15 = mainObjectForecast15.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast15 = jsonObjectForecast15.getJSONArray("weather");
                    JSONObject weatherObjectForeast15 = weatherArrayForecast15.getJSONObject(0);
                    String main15 = weatherObjectForeast15.getString("main");
                    String description15 = weatherObjectForeast15.getString("description");

                    String dt_txt15 = jsonObjectForecast15.getString("dt_txt");
                    //
                    //16.data
                    JSONObject jsonObjectForecast16= jsonArrayForecastList.getJSONObject(23);
                    JSONObject mainObjectForecast16 = jsonObjectForecast16.getJSONObject("main");
                    double temperature16 = mainObjectForecast16.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast16 = jsonObjectForecast16.getJSONArray("weather");
                    JSONObject weatherObjectForeast16 = weatherArrayForecast16.getJSONObject(0);
                    String main16 = weatherObjectForeast16.getString("main");
                    String description16 = weatherObjectForeast16.getString("description");

                    String dt_txt16 = jsonObjectForecast16.getString("dt_txt");
                    //
                    //17.data
                    JSONObject jsonObjectForecast17= jsonArrayForecastList.getJSONObject(25);
                    JSONObject mainObjectForecast17 = jsonObjectForecast17.getJSONObject("main");
                    double temperature17 = mainObjectForecast17.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast17 = jsonObjectForecast17.getJSONArray("weather");
                    JSONObject weatherObjectForeast17 = weatherArrayForecast17.getJSONObject(0);
                    String main17 = weatherObjectForeast17.getString("main");
                    String description17 = weatherObjectForeast17.getString("description");

                    String dt_txt17 = jsonObjectForecast17.getString("dt_txt");
                    //
                    //18.data
                    JSONObject jsonObjectForecast18= jsonArrayForecastList.getJSONObject(27);
                    JSONObject mainObjectForecast18 = jsonObjectForecast18.getJSONObject("main");
                    double temperature18 = mainObjectForecast18.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast18 = jsonObjectForecast18.getJSONArray("weather");
                    JSONObject weatherObjectForeast18 = weatherArrayForecast18.getJSONObject(0);
                    String main18 = weatherObjectForeast18.getString("main");
                    String description18 = weatherObjectForeast18.getString("description");

                    String dt_txt18 = jsonObjectForecast18.getString("dt_txt");
                    //
                    //19.data
                    JSONObject jsonObjectForecast19= jsonArrayForecastList.getJSONObject(29);
                    JSONObject mainObjectForecast19 = jsonObjectForecast19.getJSONObject("main");
                    double temperature19 = mainObjectForecast19.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast19 = jsonObjectForecast19.getJSONArray("weather");
                    JSONObject weatherObjectForeast19 = weatherArrayForecast19.getJSONObject(0);
                    String main19 = weatherObjectForeast19.getString("main");
                    String description19 = weatherObjectForeast19.getString("description");

                    String dt_txt19 = jsonObjectForecast19.getString("dt_txt");
                    //
                    //20.data
                    JSONObject jsonObjectForecast20= jsonArrayForecastList.getJSONObject(31);
                    JSONObject mainObjectForecast20 = jsonObjectForecast20.getJSONObject("main");
                    double temperature20 = mainObjectForecast20.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast20 = jsonObjectForecast20.getJSONArray("weather");
                    JSONObject weatherObjectForeast20 = weatherArrayForecast20.getJSONObject(0);
                    String main20 = weatherObjectForeast20.getString("main");
                    String description20 = weatherObjectForeast20.getString("description");

                    String dt_txt20 = jsonObjectForecast20.getString("dt_txt");
                    //
                    //21.data
                    JSONObject jsonObjectForecast21= jsonArrayForecastList.getJSONObject(33);
                    JSONObject mainObjectForecast21 = jsonObjectForecast21.getJSONObject("main");
                    double temperature21 = mainObjectForecast21.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast21 = jsonObjectForecast21.getJSONArray("weather");
                    JSONObject weatherObjectForeast21 = weatherArrayForecast21.getJSONObject(0);
                    String main21 = weatherObjectForeast21.getString("main");
                    String description21 = weatherObjectForeast21.getString("description");

                    String dt_txt21 = jsonObjectForecast21.getString("dt_txt");
                    //
                    //22.data
                    JSONObject jsonObjectForecast22= jsonArrayForecastList.getJSONObject(35);
                    JSONObject mainObjectForecast22 = jsonObjectForecast22.getJSONObject("main");
                    double temperature22 = mainObjectForecast22.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast22 = jsonObjectForecast22.getJSONArray("weather");
                    JSONObject weatherObjectForeast22 = weatherArrayForecast22.getJSONObject(0);
                    String main22 = weatherObjectForeast22.getString("main");
                    String description22 = weatherObjectForeast22.getString("description");

                    String dt_txt22 = jsonObjectForecast22.getString("dt_txt");
                    //
                    //23.data
                    JSONObject jsonObjectForecast23= jsonArrayForecastList.getJSONObject(37);
                    JSONObject mainObjectForecast23 = jsonObjectForecast23.getJSONObject("main");
                    double temperature23 = mainObjectForecast23.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast23 = jsonObjectForecast23.getJSONArray("weather");
                    JSONObject weatherObjectForeast23 = weatherArrayForecast23.getJSONObject(0);
                    String main23 = weatherObjectForeast23.getString("main");
                    String description23 = weatherObjectForeast23.getString("description");

                    String dt_txt23 = jsonObjectForecast23.getString("dt_txt");
                    //
                    //24.data
                    JSONObject jsonObjectForecast24= jsonArrayForecastList.getJSONObject(39);
                    JSONObject mainObjectForecast24 = jsonObjectForecast24.getJSONObject("main");
                    double temperature24 = mainObjectForecast24.getDouble("temp")-273.15;

                    JSONArray weatherArrayForecast24 = jsonObjectForecast24.getJSONArray("weather");
                    JSONObject weatherObjectForeast24 = weatherArrayForecast24.getJSONObject(0);
                    String main24 = weatherObjectForeast24.getString("main");
                    String description24 = weatherObjectForeast24.getString("description");

                    String dt_txt24 = jsonObjectForecast24.getString("dt_txt");
                    //
                    tvSunrise.setText(sunriseTime);
                    tvSunset.setText(sunsetTime);
                    //
                    SimpleDateFormat incomingDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat wantedDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    SimpleDateFormat wantedHourFormat = new SimpleDateFormat("HH.mm");
                    Date dtxt1 = incomingDateFormat.parse(dt_txt9);
                    Date dtxt2 = incomingDateFormat.parse(dt_txt13);
                    Date dtxt3 =incomingDateFormat.parse(dt_txt17);
                    Date dtxt4 = incomingDateFormat.parse(dt_txt21);

                    Date htxt1 = incomingDateFormat.parse(dt_txt5);

                    String date1 = wantedDateFormat.format(dtxt1);
                    String date2 = wantedDateFormat.format(dtxt2);
                    String date3 = wantedDateFormat.format(dtxt3);
                    String date4 = wantedDateFormat.format(dtxt4);

                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                    String day1 = dayFormat.format(dtxt1);
                    String day2 = dayFormat.format(dtxt2);
                    String day3 = dayFormat.format(dtxt3);
                    String day4 = dayFormat.format(dtxt4);

                    tvDateOne.setText(date1+" "+day1);
                    tvDateTwo.setText(date2+" "+day2);
                    tvDateThree.setText(date3+" "+day3);
                    tvDateFour.setText(date4+" "+day4);

                    //1.forecast
                    //String hourone1 = wantedHourFormat.format(htxt1);
                    String hourone1 = convertToAMPM(dt_txt5);
                    String hourtwo1 = convertToAMPM(dt_txt7);
                    String hourthree1 = convertToAMPM(dt_txt9);
                    String hourfour1 = convertToAMPM(dt_txt10);
                    tvHourOne1.setText(hourone1);
                    tvHourTwo1.setText(hourtwo1);
                    tvHourThree1.setText(hourthree1);
                    tvHourFour1.setText(hourfour1);

                    String weatherCondition = description5;
                    String cvc1 = correctWeatherCondition(weatherCondition);
                    //tvDescriptionOne1.setText(cvc1);
                    tvDescriptionOne1.setText(description5);
                    tvDescriptionTwo1.setText(description7);
                    tvDescriptionThree1.setText(description9);
                    tvDescriptionFour1.setText(description10);

                    //2.forecast
                    String hourone2 = convertToAMPM(dt_txt11);
                    String hourtwo2 = convertToAMPM(dt_txt12);
                    String hourthree2 = convertToAMPM(dt_txt13);
                    String hourfour2 = convertToAMPM(dt_txt14);
                    tvHourOne2.setText(hourone2);
                    tvHourTwo2.setText(hourtwo2);
                    tvHourThree2.setText(hourthree2);
                    tvHourFour2.setText(hourfour2);

                    tvDescriptionOne2.setText(description11);
                    tvDescriptionTwo2.setText(description12);
                    tvDescriptionThree2.setText(description13);
                    tvDescriptionFour2.setText(description14);

                    //3.forecast
                    String hourone3 = convertToAMPM(dt_txt15);
                    String hourtwo3 = convertToAMPM(dt_txt16);
                    String hourthree3 = convertToAMPM(dt_txt17);
                    String hourfour3 = convertToAMPM(dt_txt18);
                    tvHourOne3.setText(hourone3);
                    tvHourTwo3.setText(hourtwo3);
                    tvHourThree3.setText(hourthree3);
                    tvHourFour3.setText(hourfour3);

                    tvDescriptionOne3.setText(description15);
                    tvDescriptionTwo3.setText(description16);
                    tvDescriptionThree3.setText(description17);
                    tvDescriptionFour3.setText(description18);

                    //4.forecast
                    String hourone4 = convertToAMPM(dt_txt19);
                    String hourtwo4 = convertToAMPM(dt_txt20);
                    String hourthree4 = convertToAMPM(dt_txt21);
                    String hourfour4 = convertToAMPM(dt_txt22);
                    tvHourOne4.setText(hourone4);
                    tvHourTwo4.setText(hourtwo4);
                    tvHourThree4.setText(hourthree4);
                    tvHourFour4.setText(hourfour4);

                    tvDescriptionOne4.setText(description19);
                    tvDescriptionTwo4.setText(description20);
                    tvDescriptionThree4.setText(description21);
                    tvDescriptionFour4.setText(description22);

                    int roundedTemp1 = (int)Math.round(temperature5);
                    output = df.format(roundedTemp1);
                    tvForecastTemp1.setText(output + "°C");

                    int roundedTemp2 = (int)Math.round(temperature7);
                    output = df.format(roundedTemp2);
                    tvForecastTemp2.setText(output + "°C");

                    int roundedTemp3 = (int)Math.round(temperature9);
                    output = df.format(roundedTemp3);
                    tvForecastTemp3.setText(output + "°C");

                    int roundedTemp4 = (int)Math.round(temperature10);
                    output = df.format(roundedTemp4);
                    tvForecastTemp4.setText(output + "°C");

                    int roundedTemp11 = (int)Math.round(temperature11);
                    output = df.format(roundedTemp11);
                    tvForecastTemp11.setText(output + "°C");

                    int roundedTemp22 = (int)Math.round(temperature12);
                    output = df.format(roundedTemp22);
                    tvForecastTemp22.setText(output + "°C");

                    int roundedTemp33 = (int)Math.round(temperature13);
                    output = df.format(roundedTemp33);
                    tvForecastTemp33.setText(output + "°C");

                    int roundedTemp44 = (int)Math.round(temperature14);
                    output = df.format(roundedTemp44);
                    tvForecastTemp44.setText(output + "°C");

                    int roundedTemp111 = (int)Math.round(temperature15);
                    output = df.format(roundedTemp111);
                    tvForecastTemp111.setText(output + "°C");

                    int roundedTemp222 = (int)Math.round(temperature16);
                    output = df.format(roundedTemp222);
                    tvForecastTemp222.setText(output + "°C");

                    int roundedTemp333 = (int)Math.round(temperature17);
                    output = df.format(roundedTemp333);
                    tvForecastTemp333.setText(output + "°C");

                    int roundedTemp444 = (int)Math.round(temperature18);
                    output = df.format(roundedTemp444);
                    tvForecastTemp444.setText(output + "°C");

                    int roundedTemp1111 = (int)Math.round(temperature19);
                    output = df.format(roundedTemp1111);
                    tvForecastTemp1111.setText(output + "°C");

                    int roundedTemp2222 = (int)Math.round(temperature20);
                    output = df.format(roundedTemp2222);
                    tvForecastTemp2222.setText(output + "°C");

                    int roundedTemp3333 = (int)Math.round(temperature21);
                    output = df.format(roundedTemp3333);
                    tvForecastTemp3333.setText(output + "°C");

                    int roundedTemp4444 = (int)Math.round(temperature22);
                    output = df.format(roundedTemp4444);
                    tvForecastTemp4444.setText(output + "°C");

                    weatherDatas = new String[]{description5,description7,description9,description10
                    ,description11,description12,description13,description14,description15,description16
                    ,description17,description18,description19,description20,description21,description22};

                    ImageView[] iconImageViews = {
                            findViewById(R.id.ivDayOneone),
                            findViewById(R.id.ivDayOnetwo),
                            findViewById(R.id.ivDayOnethree),
                            findViewById(R.id.ivDayOnefour)
                    };
                    sunsetData = sunset;
                    String hourVersion = formatLongToTime(sunset).split(":")[0];
                    /*for(int i = 0; i < weatherDatas.length; i++){
                        String weatherSituation = weatherDatas[i];
                        ImageView iconImageView = iconImageViews[i];

                        boolean isClear = containsKeyword(weatherSituation, "clear");
                        boolean isRainy = containsKeyword(weatherSituation, "rain");
                        boolean isCloudy = containsKeyword(weatherSituation,"cloud");
                        boolean isSnowy = containsKeyword(weatherSituation,"snow");

                        if(isClear && isBeforeSunset(Integer.parseInt(hourVersion))){
                            iconImageView.setImageResource(R.drawable.sunnn);
                        }
                    }*/
                    //item1
                    boolean isAfterSunset = !isAfterSunset(dt_txt,sunset,sunrise);
                    if (description.contains("cloud")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description.contains("clear")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description.contains("rain")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description.contains("snow")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast1.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp = (int)Math.round(temperature);
                    output = df.format(roundeddTemp);
                    tvTemp1.setText(output + "°C");
                    tvHumidity1.setText(humidity + "%");
                    tvSped1.setText(wind + " m/s");
                    String ampmTime = convertToAMPM(dt_txt);
                    tvHour1.setText(ampmTime);

                    //item2
                    boolean isAfterSunset1 = !isAfterSunset(dt_txt1,sunset,sunrise);
                    if (description1.contains("cloud")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description1.contains("clear")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description1.contains("rain")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description1.contains("snow")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast2.setImageResource(R.drawable.cloudysun);
                    }
                    int roundedddTemp = (int)Math.round(temperature1);
                    output = df.format(roundedddTemp);
                    tvTemp2.setText(output + "°C");
                    tvHumidity2.setText(humidity1 + "%");
                    tvSped2.setText(wind1 + " m/s");
                    String ampmTime1 = convertToAMPM(dt_txt1);
                    tvHour2.setText(ampmTime1);

                    //item3
                    boolean isAfterSunset2 = !isAfterSunset(dt_txt2,sunset,sunrise);
                    if (description2.contains("cloud")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description2.contains("clear")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description2.contains("rain")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description2.contains("snow")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast3.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp3 = (int)Math.round(temperature2);
                    output = df.format(roundeddTemp3);
                    tvTemp3.setText(output + "°C");
                    tvHumidity3.setText(humidity2 + "%");
                    tvSped3.setText(wind2 + " m/s");
                    String ampmTime2 = convertToAMPM(dt_txt2);
                    tvHour3.setText(ampmTime2);

                    //item4
                    boolean isAfterSunset3 = !isAfterSunset(dt_txt3,sunset,sunrise);
                    if (description3.contains("cloud")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description3.contains("clear")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description3.contains("rain")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description3.contains("snow")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast4.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp4 = (int)Math.round(temperature3);
                    output = df.format(roundeddTemp4);
                    tvTemp4.setText(output + "°C");
                    tvHumidity4.setText(humidity3 + "%");
                    tvSped4.setText(wind3 + " m/s");
                    String ampmTime3 = convertToAMPM(dt_txt3);
                    tvHour4.setText(ampmTime3);

                    //item5
                    boolean isAfterSunset4 = !isAfterSunset(dt_txt4,sunset,sunrise);
                    if (description4.contains("cloud")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description4.contains("clear")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description4.contains("rain")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description4.contains("snow")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast5.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp5 = (int)Math.round(temperature4);
                    output = df.format(roundeddTemp5);
                    tvTemp5.setText(output + "°C");
                    tvHumidity5.setText(humidity4 + "%");
                    tvSped5.setText(wind4 + " m/s");
                    String ampmTime4 = convertToAMPM(dt_txt4);
                    tvHour5.setText(ampmTime4);

                    //item6
                    boolean isAfterSunset5 = !isAfterSunset(dt_txt5,sunset,sunrise);
                    if (description5.contains("cloud")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description5.contains("clear")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description5.contains("rain")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description5.contains("snow")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast6.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp6 = (int)Math.round(temperature5);
                    output = df.format(roundeddTemp6);
                    tvTemp6.setText(output + "°C");
                    tvHumidity6.setText(humidity5 + "%");
                    tvSped6.setText(wind5 + " m/s");
                    String ampmTime5 = convertToAMPM(dt_txt5);
                    tvHour6.setText(ampmTime5);

                    //item7
                    boolean isAfterSunset6 = !isAfterSunset(dt_txt6,sunset,sunrise);
                    if (description6.contains("cloud")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description6.contains("clear")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description6.contains("rain")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description6.contains("snow")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast7.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp7 = (int)Math.round(temperature6);
                    output = df.format(roundeddTemp7);
                    tvTemp7.setText(output + "°C");
                    tvHumidity7.setText(humidity6 + "%");
                    tvSped7.setText(wind6 + " m/s");
                    String ampmTime6 = convertToAMPM(dt_txt6);
                    tvHour7.setText(ampmTime6);

                    //item8
                    boolean isAfterSunset7 = !isAfterSunset(dt_txt7,sunset,sunrise);
                    if (description7.contains("cloud")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description7.contains("clear")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description7.contains("rain")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description7.contains("snow")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast8.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp8 = (int)Math.round(temperature7);
                    output = df.format(roundeddTemp8);
                    tvTemp8.setText(output + "°C");
                    tvHumidity8.setText(humidity7 + "%");
                    tvSped8.setText(wind7 + " m/s");
                    String ampmTime7 = convertToAMPM(dt_txt7);
                    tvHour8.setText(ampmTime7);

                    //item9
                    boolean isAfterSunset8 = !isAfterSunset(dt_txt8,sunset,sunrise);
                    if (description8.contains("cloud")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description8.contains("clear")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description8.contains("rain")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description8.contains("snow")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast9.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp9 = (int)Math.round(temperature8);
                    output = df.format(roundeddTemp9);
                    tvTemp9.setText(output + "°C");
                    tvHumidity9.setText(humidity8 + "%");
                    tvSped9.setText(wind8 + " m/s");
                    String ampmTime8 = convertToAMPM(dt_txt8);
                    tvHour9.setText(ampmTime8);



                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest1);

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response5) {
                try {
                    JSONObject jsonResponse5 = new JSONObject(response5);
                    JSONArray jsonArray = jsonResponse5.getJSONArray("weather");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String description = jsonObject.getString("description");
                    JSONObject jsonObjectMain = jsonResponse5.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse5.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");
                    int deg = jsonObjectWind.getInt("deg");
                    JSONObject jsonObjectClouds = jsonResponse5.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");
                    JSONObject jsonObjectSys = jsonResponse5.getJSONObject("sys");
                    String countryName = jsonObjectSys.getString("country");
                    String cityName = jsonResponse5.getString("name");
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue3 = Volley.newRequestQueue(this);
        requestQueue3.add(stringRequest2);

        //data provided by
        String fullText = "Data provided by Openweather";
        SpannableString spannableString = new SpannableString(fullText);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, fullText.indexOf("Openweather"), fullText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        /*Drawable cloudIcon = getResources().getDrawable(R.drawable.baseline_cloud);
        cloudIcon.setBounds(0,0,cloudIcon.getIntrinsicWidth(), cloudIcon.getIntrinsicHeight());

        ImageSpan imageSpan = new ImageSpan(cloudIcon, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(imageSpan, fullText.indexOf("Openweather") + "Openweather".length() - 1, fullText.indexOf("Openweather") + "Openweather".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/

        TextView openwText = findViewById(R.id.openwText);
        openwText.setText(spannableString);

        openwText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String openweatherUrl = "https://openweathermap.org/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(openweatherUrl));
                startActivity(intent);
            }
        });
        //data provided by

        Calendar calendar = getInstance();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location;


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                getWeatherDataForLocation(latitude, longitude);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
            }
        };
        checkLocationPermission();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

    }


    private void checkLocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            //getWeatherDataForLocation();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } else {
                // İzin reddedildiğinde yapılacak işlemler buraya gelecek
            }
        }
    }

    private void getWeatherDataForLocation(double latitude, double longitude) {
        String tempUrl = "";
        String temppUrl = "";

        tempUrl = weatherUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + appid;
        temppUrl = forecastUrl + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + appid;


        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, temppUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
                String output = "";
                try {

                    String currentDateTime ="Date: " + day + "." + month + "." + year + "    Time: " + hour + "." + minute;
                    tvCurrentTime.setText(currentDateTime);

                    JSONObject jsonResponseForecast = new JSONObject(response1);
                    JSONArray jsonArrayForecastList = jsonResponseForecast.getJSONArray("list");


                    JSONObject jsonObjectForecast = jsonArrayForecastList.getJSONObject(0);

                    JSONObject mainObjectForecast = jsonObjectForecast.getJSONObject("main");
                    double temperature = mainObjectForecast.getDouble("temp") - 273.15;
                    int humidity = mainObjectForecast.getInt("humidity");

                    JSONArray weatherArrayForecast = jsonObjectForecast.getJSONArray("weather");
                    JSONObject weatherObjectForeast = weatherArrayForecast.getJSONObject(0);
                    String main = weatherObjectForeast.getString("main");
                    String description = weatherObjectForeast.getString("description");

                    JSONObject windObjectForecast = jsonObjectForecast.getJSONObject("wind");
                    String wind = windObjectForecast.getString("speed");

                    String dt_txt = jsonObjectForecast.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast1 = jsonArrayForecastList.getJSONObject(1);
                    JSONObject mainObjectForecast1 = jsonObjectForecast1.getJSONObject("main");
                    double temperature1 = mainObjectForecast1.getDouble("temp") - 273.15;
                    int humidity1 = mainObjectForecast1.getInt("humidity");

                    JSONArray weatherArrayForecast1 = jsonObjectForecast1.getJSONArray("weather");
                    JSONObject weatherObjectForeast1 = weatherArrayForecast1.getJSONObject(0);
                    String main1 = weatherObjectForeast1.getString("main");
                    String description1 = weatherObjectForeast1.getString("description");

                    JSONObject windObjectForecast1 = jsonObjectForecast1.getJSONObject("wind");
                    String wind1 = windObjectForecast1.getString("speed");

                    String dt_txt1 = jsonObjectForecast1.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast2 = jsonArrayForecastList.getJSONObject(2);
                    JSONObject mainObjectForecast2 = jsonObjectForecast2.getJSONObject("main");
                    double temperature2 = mainObjectForecast2.getDouble("temp") - 273.15;
                    int humidity2 = mainObjectForecast2.getInt("humidity");

                    JSONArray weatherArrayForecast2 = jsonObjectForecast2.getJSONArray("weather");
                    JSONObject weatherObjectForeast2 = weatherArrayForecast2.getJSONObject(0);
                    String main2 = weatherObjectForeast2.getString("main");
                    String description2 = weatherObjectForeast2.getString("description");

                    JSONObject windObjectForecast2 = jsonObjectForecast2.getJSONObject("wind");
                    String wind2 = windObjectForecast2.getString("speed");

                    String dt_txt2 = jsonObjectForecast2.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast3 = jsonArrayForecastList.getJSONObject(3);
                    JSONObject mainObjectForecast3 = jsonObjectForecast3.getJSONObject("main");
                    double temperature3 = mainObjectForecast3.getDouble("temp") - 273.15;
                    int humidity3 = mainObjectForecast3.getInt("humidity");

                    JSONArray weatherArrayForecast3 = jsonObjectForecast3.getJSONArray("weather");
                    JSONObject weatherObjectForeast3 = weatherArrayForecast3.getJSONObject(0);
                    String main3 = weatherObjectForeast3.getString("main");
                    String description3 = weatherObjectForeast3.getString("description");

                    JSONObject windObjectForecast3 = jsonObjectForecast3.getJSONObject("wind");
                    String wind3 = windObjectForecast3.getString("speed");

                    String dt_txt3 = jsonObjectForecast3.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast4 = jsonArrayForecastList.getJSONObject(4);
                    JSONObject mainObjectForecast4 = jsonObjectForecast4.getJSONObject("main");
                    double temperature4 = mainObjectForecast4.getDouble("temp") - 273.15;
                    int humidity4 = mainObjectForecast4.getInt("humidity");

                    JSONArray weatherArrayForecast4 = jsonObjectForecast4.getJSONArray("weather");
                    JSONObject weatherObjectForeast4 = weatherArrayForecast4.getJSONObject(0);
                    String main4 = weatherObjectForeast4.getString("main");
                    String description4 = weatherObjectForeast4.getString("description");

                    JSONObject windObjectForecast4 = jsonObjectForecast4.getJSONObject("wind");
                    String wind4 = windObjectForecast4.getString("speed");

                    String dt_txt4 = jsonObjectForecast4.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast5 = jsonArrayForecastList.getJSONObject(5);
                    JSONObject mainObjectForecast5 = jsonObjectForecast5.getJSONObject("main");
                    double temperature5 = mainObjectForecast5.getDouble("temp") - 273.15;
                    int humidity5 = mainObjectForecast5.getInt("humidity");

                    JSONArray weatherArrayForecast5 = jsonObjectForecast5.getJSONArray("weather");
                    JSONObject weatherObjectForeast5 = weatherArrayForecast5.getJSONObject(0);
                    String main5 = weatherObjectForeast5.getString("main");
                    String description5 = weatherObjectForeast5.getString("description");

                    JSONObject windObjectForecast5 = jsonObjectForecast5.getJSONObject("wind");
                    String wind5 = windObjectForecast5.getString("speed");

                    String dt_txt5 = jsonObjectForecast5.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast6 = jsonArrayForecastList.getJSONObject(6);
                    JSONObject mainObjectForecast6 = jsonObjectForecast6.getJSONObject("main");
                    double temperature6 = mainObjectForecast6.getDouble("temp") - 273.15;
                    int humidity6 = mainObjectForecast6.getInt("humidity");

                    JSONArray weatherArrayForecast6 = jsonObjectForecast6.getJSONArray("weather");
                    JSONObject weatherObjectForeast6 = weatherArrayForecast6.getJSONObject(0);
                    String main6 = weatherObjectForeast6.getString("main");
                    String description6 = weatherObjectForeast6.getString("description");

                    JSONObject windObjectForecast6 = jsonObjectForecast3.getJSONObject("wind");
                    String wind6 = windObjectForecast6.getString("speed");

                    String dt_txt6 = jsonObjectForecast6.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast7 = jsonArrayForecastList.getJSONObject(7);
                    JSONObject mainObjectForecast7 = jsonObjectForecast7.getJSONObject("main");
                    double temperature7 = mainObjectForecast7.getDouble("temp") - 273.15;
                    int humidity7 = mainObjectForecast7.getInt("humidity");

                    JSONArray weatherArrayForecast7 = jsonObjectForecast7.getJSONArray("weather");
                    JSONObject weatherObjectForeast7 = weatherArrayForecast7.getJSONObject(0);
                    String main7 = weatherObjectForeast7.getString("main");
                    String description7 = weatherObjectForeast7.getString("description");

                    JSONObject windObjectForecast7 = jsonObjectForecast7.getJSONObject("wind");
                    String wind7 = windObjectForecast7.getString("speed");

                    String dt_txt7 = jsonObjectForecast7.getString("dt_txt");
                    //
                    JSONObject jsonObjectForecast8 = jsonArrayForecastList.getJSONObject(8);
                    JSONObject mainObjectForecast8 = jsonObjectForecast8.getJSONObject("main");
                    double temperature8 = mainObjectForecast8.getDouble("temp") - 273.15;
                    int humidity8 = mainObjectForecast8.getInt("humidity");

                    JSONArray weatherArrayForecast8 = jsonObjectForecast8.getJSONArray("weather");
                    JSONObject weatherObjectForeast8 = weatherArrayForecast8.getJSONObject(0);
                    String main8 = weatherObjectForeast8.getString("main");
                    String description8 = weatherObjectForeast8.getString("description");

                    JSONObject windObjectForecast8 = jsonObjectForecast8.getJSONObject("wind");
                    String wind8 = windObjectForecast8.getString("speed");

                    String dt_txt8 = jsonObjectForecast8.getString("dt_txt");
                    //8.datada kaldın
                    //textView7.setText(dt_txt8);
                    //
                    JSONObject jsonObjectCity = jsonResponseForecast.getJSONObject("city");
                    int timezone = jsonObjectCity.getInt("timezone");
                    long sunrise = jsonObjectCity.getLong("sunrise");
                    long sunset = jsonObjectCity.getLong("sunset");

                    sunrise -= 10800;
                    sunset -= 10800;

                    String sunriseTime = unixTimestampToAMPM(sunrise,timezone);
                    String sunsetTime = unixTimestampToAMPM(sunset,timezone);

                    tvSunrise.setText(sunriseTime);
                    tvSunset.setText(sunsetTime);


                    //linearLayoutsunsetAndSunrise.setVisibility(View.VISIBLE);

                    //item1
                    boolean isAfterSunset = !isAfterSunset(dt_txt,sunset,sunrise);
                    if (description.contains("cloud")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description.contains("clear")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description.contains("rain")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description.contains("snow")) {
                        if (isAfterSunset){
                            ivHourlyForecast1.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast1.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast1.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp = (int)Math.round(temperature);
                    output = df.format(roundeddTemp);
                    tvTemp1.setText(output + "°C");
                    tvHumidity1.setText(humidity + "%");
                    tvSped1.setText(wind + " m/s");
                    String ampmTime = convertToAMPM(dt_txt);
                    tvHour1.setText(ampmTime);

                    //item2
                    boolean isAfterSunset1 = !isAfterSunset(dt_txt1,sunset,sunrise);
                    if (description1.contains("cloud")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description1.contains("clear")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description1.contains("rain")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description1.contains("snow")) {
                        if (isAfterSunset1){
                            ivHourlyForecast2.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast2.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast2.setImageResource(R.drawable.cloudysun);
                    }
                    int roundedddTemp = (int)Math.round(temperature1);
                    output = df.format(roundedddTemp);
                    tvTemp2.setText(output + "°C");
                    tvHumidity2.setText(humidity1 + "%");
                    tvSped2.setText(wind1 + " m/s");
                    String ampmTime1 = convertToAMPM(dt_txt1);
                    tvHour2.setText(ampmTime1);

                    //item3
                    boolean isAfterSunset2 = !isAfterSunset(dt_txt2,sunset,sunrise);
                    if (description2.contains("cloud")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description2.contains("clear")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description2.contains("rain")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description2.contains("snow")) {
                        if (isAfterSunset2){
                            ivHourlyForecast3.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast3.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast3.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp3 = (int)Math.round(temperature2);
                    output = df.format(roundeddTemp3);
                    tvTemp3.setText(output + "°C");
                    tvHumidity3.setText(humidity2 + "%");
                    tvSped3.setText(wind2 + " m/s");
                    String ampmTime2 = convertToAMPM(dt_txt2);
                    tvHour3.setText(ampmTime2);

                    //item4
                    boolean isAfterSunset3 = !isAfterSunset(dt_txt3,sunset,sunrise);
                    if (description3.contains("cloud")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description3.contains("clear")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description3.contains("rain")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description3.contains("snow")) {
                        if (isAfterSunset3){
                            ivHourlyForecast4.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast4.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast4.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp4 = (int)Math.round(temperature3);
                    output = df.format(roundeddTemp4);
                    tvTemp4.setText(output + "°C");
                    tvHumidity4.setText(humidity3 + "%");
                    tvSped4.setText(wind3 + " m/s");
                    String ampmTime3 = convertToAMPM(dt_txt3);
                    tvHour4.setText(ampmTime3);

                    //item5
                    boolean isAfterSunset4 = !isAfterSunset(dt_txt4,sunset,sunrise);
                    if (description4.contains("cloud")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description4.contains("clear")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description4.contains("rain")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description4.contains("snow")) {
                        if (isAfterSunset4){
                            ivHourlyForecast5.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast5.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast5.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp5 = (int)Math.round(temperature4);
                    output = df.format(roundeddTemp5);
                    tvTemp5.setText(output + "°C");
                    tvHumidity5.setText(humidity4 + "%");
                    tvSped5.setText(wind4 + " m/s");
                    String ampmTime4 = convertToAMPM(dt_txt4);
                    tvHour5.setText(ampmTime4);

                    //item6
                    boolean isAfterSunset5 = !isAfterSunset(dt_txt5,sunset,sunrise);
                    if (description5.contains("cloud")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description5.contains("clear")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description5.contains("rain")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description5.contains("snow")) {
                        if (isAfterSunset5){
                            ivHourlyForecast6.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast6.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast6.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp6 = (int)Math.round(temperature5);
                    output = df.format(roundeddTemp6);
                    tvTemp6.setText(output + "°C");
                    tvHumidity6.setText(humidity5 + "%");
                    tvSped6.setText(wind5 + " m/s");
                    String ampmTime5 = convertToAMPM(dt_txt5);
                    tvHour6.setText(ampmTime5);

                    //item7
                    boolean isAfterSunset6 = !isAfterSunset(dt_txt6,sunset,sunrise);
                    if (description6.contains("cloud")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description6.contains("clear")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description6.contains("rain")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description6.contains("snow")) {
                        if (isAfterSunset6){
                            ivHourlyForecast7.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast7.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast7.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp7 = (int)Math.round(temperature6);
                    output = df.format(roundeddTemp7);
                    tvTemp7.setText(output + "°C");
                    tvHumidity7.setText(humidity6 + "%");
                    tvSped7.setText(wind6 + " m/s");
                    String ampmTime6 = convertToAMPM(dt_txt6);
                    tvHour7.setText(ampmTime6);

                    //item8
                    boolean isAfterSunset7 = !isAfterSunset(dt_txt7,sunset,sunrise);
                    if (description7.contains("cloud")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description7.contains("clear")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description7.contains("rain")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description7.contains("snow")) {
                        if (isAfterSunset7){
                            ivHourlyForecast8.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast8.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast8.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp8 = (int)Math.round(temperature7);
                    output = df.format(roundeddTemp8);
                    tvTemp8.setText(output + "°C");
                    tvHumidity8.setText(humidity7 + "%");
                    tvSped8.setText(wind7 + " m/s");
                    String ampmTime7 = convertToAMPM(dt_txt7);
                    tvHour8.setText(ampmTime7);

                    //item9
                    boolean isAfterSunset8 = !isAfterSunset(dt_txt8,sunset,sunrise);
                    if (description8.contains("cloud")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.cloudysun);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.cloudymoonnn);
                        }
                    } else if (description8.contains("clear")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.sunnn);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.crescentmoon);
                        }
                    } else if (description8.contains("rain")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.rainnnn);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.rainymoonnn);
                        }
                    } else if (description8.contains("snow")) {
                        if (isAfterSunset8){
                            ivHourlyForecast9.setImageResource(R.drawable.snowyy);
                        } else{
                            ivHourlyForecast9.setImageResource(R.drawable.snowww);
                        }
                    } else {
                        ivHourlyForecast9.setImageResource(R.drawable.cloudysun);
                    }
                    int roundeddTemp9 = (int)Math.round(temperature8);
                    output = df.format(roundeddTemp9);
                    tvTemp9.setText(output + "°C");
                    tvHumidity9.setText(humidity8 + "%");
                    tvSped9.setText(wind8 + " m/s");
                    String ampmTime8 = convertToAMPM(dt_txt8);
                    tvHour9.setText(ampmTime8);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error1) {
                //Log.d("sueda11", error1.toString());

            }
        });
        RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        requestQueue1.add(stringRequest1);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("response", response);
                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");
                    int deg = jsonObjectWind.getInt("deg");
                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");
                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                    String countryName = jsonObjectSys.getString("country");
                    String cityName = jsonResponse.getString("name");
                    long sunrise = jsonObjectSys.getLong("sunrise");
                    long sunset = jsonObjectSys.getLong("sunset");
                    int timezone = jsonResponse.getInt("timezone");

                    sunrise -= 10800;
                    sunset -= 10800;

                    boolean isDaytime = isDaytime(sunrise, sunset, timezone);

                    if (isDaytime){
                        allLayout.setBackgroundResource(R.drawable.day_backgrund);
                        hourlyforecastLayout.setBackgroundResource(R.drawable.background7);
                        ivHumidity.setImageResource(R.drawable.humidityyy);
                        ivWindsped.setImageResource(R.drawable.windspeed);
                        ivPressure.setImageResource(R.drawable.barometer);
                        ivCloudiness.setImageResource(R.drawable.cloudss);
                        if (description.contains("cloud")) {
                            ivWeatherIcon.setImageResource(R.drawable.cloudysun);
                        } else if (description.contains("clear")) {
                            ivWeatherIcon.setImageResource(R.drawable.sunnn);
                        } else if (description.contains("rain")) {
                            ivWeatherIcon.setImageResource(R.drawable.rainnnn);
                        } else if (description.contains("snow")) {
                            ivWeatherIcon.setImageResource(R.drawable.snowyy);
                        } else {
                            ivWeatherIcon.setImageResource(R.drawable.cloudysun);
                        }
                    } else{
                        allLayout.setBackgroundResource(R.drawable.background);
                        hourlyforecastLayout.setBackgroundResource(R.drawable.background8);
                        ivHumidity.setImageResource(R.drawable.humiditywhite);
                        ivWindsped.setImageResource(R.drawable.windwhite);
                        ivPressure.setImageResource(R.drawable.barometerwhite);
                        ivCloudiness.setImageResource(R.drawable.cloudywhite);
                        if (description.contains("cloud")) {
                            ivWeatherIcon.setImageResource(R.drawable.cloudymoonnn);
                        } else if (description.contains("clear")) {
                            ivWeatherIcon.setImageResource(R.drawable.crescentmoon);
                        } else if (description.contains("rain")) {
                            ivWeatherIcon.setImageResource(R.drawable.rainymoonnn);
                        } else if (description.contains("snow")) {
                            ivWeatherIcon.setImageResource(R.drawable.snowww);
                        } else {
                            ivWeatherIcon.setImageResource(R.drawable.cloudymoonnn);
                        }
                    }

                    String sunriseTime = unixTimestampToAMPM(sunrise,timezone);
                    String sunsetTime = unixTimestampToAMPM(sunset,timezone);




                    int roundedTemp = (int)Math.round(temp);
                    int roundedFeelsLike = (int)Math.round(feelsLike);
                    output = df.format(roundedTemp);
                    ;
                    tvResult.setText(output + " C°");
                    tvFeelsLike.setText("feels like : " + df.format(roundedFeelsLike) + " C°");
                    //
                    String[] words = description.split(" ");
                    StringBuilder result = new StringBuilder();
                    for (String word : words) {
                        if (!word.isEmpty()) {
                            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                            result.append(capitalizedWord).append(" ");
                        }
                    }
                    String capitalizedDescription = result.toString().trim();
                    tvDescription.setText(capitalizedDescription);

                    String capitalizedCityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
                    String capitalizedCountryName = countryName.substring(0, 1).toUpperCase() + countryName.substring(1);
                    tvCityName.setText(capitalizedCityName + " - " + capitalizedCountryName);


                    tvHumidity.setText(humidity + "%");
                    tvWindSpeed.setText(wind + " m/s");
                    tvPressure.setText(pressure + " hPa");
                    tvCloudiness.setText(clouds + " %");

                    String currentTime = hour + "." + minute;
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    //Date currentDate = sdf.parse(currentTime);
                    //Date sunsetDate = sdf.parse(sunsetTime);



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }


    public void getWeatherDetails(View view) {
        String tempUrl = "";
        String temppUrl = "";
        String city = etCity.getText().toString().trim();
        if (city.equals("")) {
            tvResult.setText("City field can not be empty!!");
        } else {
            tempUrl = weatherUrl + "?q=" + city + "&appid=" + appid;
            temppUrl = forecastUrl + "?q=" + city + "&appid=" + appid;


            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, temppUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response1) {
                    String output = "";
                    try {

                        String currentDateTime ="Date: " + day + "." + month + "." + year + "    Time: " + hour + "." + minute;
                        tvCurrentTime.setText(currentDateTime);

                        JSONObject jsonResponseForecast = new JSONObject(response1);
                        JSONArray jsonArrayForecastList = jsonResponseForecast.getJSONArray("list");


                        JSONObject jsonObjectForecast = jsonArrayForecastList.getJSONObject(0);

                        JSONObject mainObjectForecast = jsonObjectForecast.getJSONObject("main");
                        double temperature = mainObjectForecast.getDouble("temp") - 273.15;
                        int humidity = mainObjectForecast.getInt("humidity");

                        JSONArray weatherArrayForecast = jsonObjectForecast.getJSONArray("weather");
                        JSONObject weatherObjectForeast = weatherArrayForecast.getJSONObject(0);
                        String main = weatherObjectForeast.getString("main");
                        String description = weatherObjectForeast.getString("description");

                        JSONObject windObjectForecast = jsonObjectForecast.getJSONObject("wind");
                        String wind = windObjectForecast.getString("speed");

                        String dt_txt = jsonObjectForecast.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast1 = jsonArrayForecastList.getJSONObject(1);
                        JSONObject mainObjectForecast1 = jsonObjectForecast1.getJSONObject("main");
                        double temperature1 = mainObjectForecast1.getDouble("temp") - 273.15;
                        int humidity1 = mainObjectForecast1.getInt("humidity");

                        JSONArray weatherArrayForecast1 = jsonObjectForecast1.getJSONArray("weather");
                        JSONObject weatherObjectForeast1 = weatherArrayForecast1.getJSONObject(0);
                        String main1 = weatherObjectForeast1.getString("main");
                        String description1 = weatherObjectForeast1.getString("description");

                        JSONObject windObjectForecast1 = jsonObjectForecast1.getJSONObject("wind");
                        String wind1 = windObjectForecast1.getString("speed");

                        String dt_txt1 = jsonObjectForecast1.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast2 = jsonArrayForecastList.getJSONObject(2);
                        JSONObject mainObjectForecast2 = jsonObjectForecast2.getJSONObject("main");
                        double temperature2 = mainObjectForecast2.getDouble("temp") - 273.15;
                        int humidity2 = mainObjectForecast2.getInt("humidity");

                        JSONArray weatherArrayForecast2 = jsonObjectForecast2.getJSONArray("weather");
                        JSONObject weatherObjectForeast2 = weatherArrayForecast2.getJSONObject(0);
                        String main2 = weatherObjectForeast2.getString("main");
                        String description2 = weatherObjectForeast2.getString("description");

                        JSONObject windObjectForecast2 = jsonObjectForecast2.getJSONObject("wind");
                        String wind2 = windObjectForecast2.getString("speed");

                        String dt_txt2 = jsonObjectForecast2.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast3 = jsonArrayForecastList.getJSONObject(3);
                        JSONObject mainObjectForecast3 = jsonObjectForecast3.getJSONObject("main");
                        double temperature3 = mainObjectForecast3.getDouble("temp") - 273.15;
                        int humidity3 = mainObjectForecast3.getInt("humidity");

                        JSONArray weatherArrayForecast3 = jsonObjectForecast3.getJSONArray("weather");
                        JSONObject weatherObjectForeast3 = weatherArrayForecast3.getJSONObject(0);
                        String main3 = weatherObjectForeast3.getString("main");
                        String description3 = weatherObjectForeast3.getString("description");

                        JSONObject windObjectForecast3 = jsonObjectForecast3.getJSONObject("wind");
                        String wind3 = windObjectForecast3.getString("speed");

                        String dt_txt3 = jsonObjectForecast3.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast4 = jsonArrayForecastList.getJSONObject(4);
                        JSONObject mainObjectForecast4 = jsonObjectForecast4.getJSONObject("main");
                        double temperature4 = mainObjectForecast4.getDouble("temp") - 273.15;
                        int humidity4 = mainObjectForecast4.getInt("humidity");

                        JSONArray weatherArrayForecast4 = jsonObjectForecast4.getJSONArray("weather");
                        JSONObject weatherObjectForeast4 = weatherArrayForecast4.getJSONObject(0);
                        String main4 = weatherObjectForeast4.getString("main");
                        String description4 = weatherObjectForeast4.getString("description");

                        JSONObject windObjectForecast4 = jsonObjectForecast4.getJSONObject("wind");
                        String wind4 = windObjectForecast4.getString("speed");

                        String dt_txt4 = jsonObjectForecast4.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast5 = jsonArrayForecastList.getJSONObject(5);
                        JSONObject mainObjectForecast5 = jsonObjectForecast5.getJSONObject("main");
                        double temperature5 = mainObjectForecast5.getDouble("temp") - 273.15;
                        int humidity5 = mainObjectForecast5.getInt("humidity");

                        JSONArray weatherArrayForecast5 = jsonObjectForecast5.getJSONArray("weather");
                        JSONObject weatherObjectForeast5 = weatherArrayForecast5.getJSONObject(0);
                        String main5 = weatherObjectForeast5.getString("main");
                        String description5 = weatherObjectForeast5.getString("description");

                        JSONObject windObjectForecast5 = jsonObjectForecast5.getJSONObject("wind");
                        String wind5 = windObjectForecast5.getString("speed");

                        String dt_txt5 = jsonObjectForecast5.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast6 = jsonArrayForecastList.getJSONObject(6);
                        JSONObject mainObjectForecast6 = jsonObjectForecast6.getJSONObject("main");
                        double temperature6 = mainObjectForecast6.getDouble("temp") - 273.15;
                        int humidity6 = mainObjectForecast6.getInt("humidity");

                        JSONArray weatherArrayForecast6 = jsonObjectForecast6.getJSONArray("weather");
                        JSONObject weatherObjectForeast6 = weatherArrayForecast6.getJSONObject(0);
                        String main6 = weatherObjectForeast6.getString("main");
                        String description6 = weatherObjectForeast6.getString("description");

                        JSONObject windObjectForecast6 = jsonObjectForecast3.getJSONObject("wind");
                        String wind6 = windObjectForecast6.getString("speed");

                        String dt_txt6 = jsonObjectForecast6.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast7 = jsonArrayForecastList.getJSONObject(7);
                        JSONObject mainObjectForecast7 = jsonObjectForecast7.getJSONObject("main");
                        double temperature7 = mainObjectForecast7.getDouble("temp") - 273.15;
                        int humidity7 = mainObjectForecast7.getInt("humidity");

                        JSONArray weatherArrayForecast7 = jsonObjectForecast7.getJSONArray("weather");
                        JSONObject weatherObjectForeast7 = weatherArrayForecast7.getJSONObject(0);
                        String main7 = weatherObjectForeast7.getString("main");
                        String description7 = weatherObjectForeast7.getString("description");

                        JSONObject windObjectForecast7 = jsonObjectForecast7.getJSONObject("wind");
                        String wind7 = windObjectForecast7.getString("speed");

                        String dt_txt7 = jsonObjectForecast7.getString("dt_txt");
                        //
                        JSONObject jsonObjectForecast8 = jsonArrayForecastList.getJSONObject(8);
                        JSONObject mainObjectForecast8 = jsonObjectForecast8.getJSONObject("main");
                        double temperature8 = mainObjectForecast8.getDouble("temp") - 273.15;
                        int humidity8 = mainObjectForecast8.getInt("humidity");

                        JSONArray weatherArrayForecast8 = jsonObjectForecast8.getJSONArray("weather");
                        JSONObject weatherObjectForeast8 = weatherArrayForecast8.getJSONObject(0);
                        String main8 = weatherObjectForeast8.getString("main");
                        String description8 = weatherObjectForeast8.getString("description");

                        JSONObject windObjectForecast8 = jsonObjectForecast8.getJSONObject("wind");
                        String wind8 = windObjectForecast8.getString("speed");

                        String dt_txt8 = jsonObjectForecast8.getString("dt_txt");
                        //8.datada kaldın
                        //9.data
                        JSONObject jsonObjectForecast9 = jsonArrayForecastList.getJSONObject(9);
                        JSONObject mainObjectForecast9 = jsonObjectForecast9.getJSONObject("main");
                        double temperature9 = mainObjectForecast9.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast9 = jsonObjectForecast9.getJSONArray("weather");
                        JSONObject weatherObjectForeast9 = weatherArrayForecast9.getJSONObject(0);
                        String main9 = weatherObjectForeast9.getString("main");
                        String description9 = weatherObjectForeast9.getString("description");

                        String dt_txt9 = jsonObjectForecast9.getString("dt_txt");
                        //
                        //10.data
                        JSONObject jsonObjectForecast10 = jsonArrayForecastList.getJSONObject(11);
                        JSONObject mainObjectForecast10 = jsonObjectForecast10.getJSONObject("main");
                        double temperature10 = mainObjectForecast10.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast10 = jsonObjectForecast10.getJSONArray("weather");
                        JSONObject weatherObjectForeast10 = weatherArrayForecast10.getJSONObject(0);
                        String main10 = weatherObjectForeast10.getString("main");
                        String description10 = weatherObjectForeast10.getString("description");

                        String dt_txt10 = jsonObjectForecast10.getString("dt_txt");
                        //
                        //11.data
                        JSONObject jsonObjectForecast11 = jsonArrayForecastList.getJSONObject(13);
                        JSONObject mainObjectForecast11 = jsonObjectForecast11.getJSONObject("main");
                        double temperature11 = mainObjectForecast11.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast11 = jsonObjectForecast11.getJSONArray("weather");
                        JSONObject weatherObjectForeast11 = weatherArrayForecast11.getJSONObject(0);
                        String main11 = weatherObjectForeast11.getString("main");
                        String description11 = weatherObjectForeast11.getString("description");

                        String dt_txt11 = jsonObjectForecast11.getString("dt_txt");
                        //
                        //12.data
                        JSONObject jsonObjectForecast12= jsonArrayForecastList.getJSONObject(15);
                        JSONObject mainObjectForecast12 = jsonObjectForecast12.getJSONObject("main");
                        double temperature12 = mainObjectForecast12.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast12 = jsonObjectForecast12.getJSONArray("weather");
                        JSONObject weatherObjectForeast12 = weatherArrayForecast12.getJSONObject(0);
                        String main12 = weatherObjectForeast12.getString("main");
                        String description12 = weatherObjectForeast12.getString("description");

                        String dt_txt12 = jsonObjectForecast12.getString("dt_txt");
                        //
                        //13.data
                        JSONObject jsonObjectForecast13= jsonArrayForecastList.getJSONObject(17);
                        JSONObject mainObjectForecast13 = jsonObjectForecast13.getJSONObject("main");
                        double temperature13 = mainObjectForecast13.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast13 = jsonObjectForecast13.getJSONArray("weather");
                        JSONObject weatherObjectForeast13 = weatherArrayForecast13.getJSONObject(0);
                        String main13 = weatherObjectForeast13.getString("main");
                        String description13 = weatherObjectForeast13.getString("description");

                        String dt_txt13 = jsonObjectForecast13.getString("dt_txt");
                        //
                        //14.data
                        JSONObject jsonObjectForecast14= jsonArrayForecastList.getJSONObject(19);
                        JSONObject mainObjectForecast14 = jsonObjectForecast14.getJSONObject("main");
                        double temperature14 = mainObjectForecast13.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast14 = jsonObjectForecast14.getJSONArray("weather");
                        JSONObject weatherObjectForeast14 = weatherArrayForecast14.getJSONObject(0);
                        String main14 = weatherObjectForeast14.getString("main");
                        String description14 = weatherObjectForeast14.getString("description");

                        String dt_txt14 = jsonObjectForecast14.getString("dt_txt");
                        //
                        //15.data
                        JSONObject jsonObjectForecast15= jsonArrayForecastList.getJSONObject(21);
                        JSONObject mainObjectForecast15 = jsonObjectForecast15.getJSONObject("main");
                        double temperature15 = mainObjectForecast15.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast15 = jsonObjectForecast15.getJSONArray("weather");
                        JSONObject weatherObjectForeast15 = weatherArrayForecast15.getJSONObject(0);
                        String main15 = weatherObjectForeast15.getString("main");
                        String description15 = weatherObjectForeast15.getString("description");

                        String dt_txt15 = jsonObjectForecast15.getString("dt_txt");
                        //
                        //16.data
                        JSONObject jsonObjectForecast16= jsonArrayForecastList.getJSONObject(23);
                        JSONObject mainObjectForecast16 = jsonObjectForecast16.getJSONObject("main");
                        double temperature16 = mainObjectForecast16.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast16 = jsonObjectForecast16.getJSONArray("weather");
                        JSONObject weatherObjectForeast16 = weatherArrayForecast16.getJSONObject(0);
                        String main16 = weatherObjectForeast16.getString("main");
                        String description16 = weatherObjectForeast16.getString("description");

                        String dt_txt16 = jsonObjectForecast16.getString("dt_txt");
                        //
                        //17.data
                        JSONObject jsonObjectForecast17= jsonArrayForecastList.getJSONObject(25);
                        JSONObject mainObjectForecast17 = jsonObjectForecast17.getJSONObject("main");
                        double temperature17 = mainObjectForecast17.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast17 = jsonObjectForecast17.getJSONArray("weather");
                        JSONObject weatherObjectForeast17 = weatherArrayForecast17.getJSONObject(0);
                        String main17 = weatherObjectForeast17.getString("main");
                        String description17 = weatherObjectForeast17.getString("description");

                        String dt_txt17 = jsonObjectForecast17.getString("dt_txt");
                        //
                        //18.data
                        JSONObject jsonObjectForecast18= jsonArrayForecastList.getJSONObject(27);
                        JSONObject mainObjectForecast18 = jsonObjectForecast18.getJSONObject("main");
                        double temperature18 = mainObjectForecast18.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast18 = jsonObjectForecast18.getJSONArray("weather");
                        JSONObject weatherObjectForeast18 = weatherArrayForecast18.getJSONObject(0);
                        String main18 = weatherObjectForeast18.getString("main");
                        String description18 = weatherObjectForeast18.getString("description");

                        String dt_txt18 = jsonObjectForecast18.getString("dt_txt");
                        //
                        //19.data
                        JSONObject jsonObjectForecast19= jsonArrayForecastList.getJSONObject(29);
                        JSONObject mainObjectForecast19 = jsonObjectForecast19.getJSONObject("main");
                        double temperature19 = mainObjectForecast19.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast19 = jsonObjectForecast19.getJSONArray("weather");
                        JSONObject weatherObjectForeast19 = weatherArrayForecast19.getJSONObject(0);
                        String main19 = weatherObjectForeast19.getString("main");
                        String description19 = weatherObjectForeast19.getString("description");

                        String dt_txt19 = jsonObjectForecast19.getString("dt_txt");
                        //
                        //20.data
                        JSONObject jsonObjectForecast20= jsonArrayForecastList.getJSONObject(31);
                        JSONObject mainObjectForecast20 = jsonObjectForecast20.getJSONObject("main");
                        double temperature20 = mainObjectForecast20.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast20 = jsonObjectForecast20.getJSONArray("weather");
                        JSONObject weatherObjectForeast20 = weatherArrayForecast20.getJSONObject(0);
                        String main20 = weatherObjectForeast20.getString("main");
                        String description20 = weatherObjectForeast20.getString("description");

                        String dt_txt20 = jsonObjectForecast20.getString("dt_txt");
                        //
                        //21.data
                        JSONObject jsonObjectForecast21= jsonArrayForecastList.getJSONObject(33);
                        JSONObject mainObjectForecast21 = jsonObjectForecast21.getJSONObject("main");
                        double temperature21 = mainObjectForecast21.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast21 = jsonObjectForecast21.getJSONArray("weather");
                        JSONObject weatherObjectForeast21 = weatherArrayForecast21.getJSONObject(0);
                        String main21 = weatherObjectForeast21.getString("main");
                        String description21 = weatherObjectForeast21.getString("description");

                        String dt_txt21 = jsonObjectForecast21.getString("dt_txt");
                        //
                        //22.data
                        JSONObject jsonObjectForecast22= jsonArrayForecastList.getJSONObject(35);
                        JSONObject mainObjectForecast22 = jsonObjectForecast22.getJSONObject("main");
                        double temperature22 = mainObjectForecast22.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast22 = jsonObjectForecast22.getJSONArray("weather");
                        JSONObject weatherObjectForeast22 = weatherArrayForecast22.getJSONObject(0);
                        String main22 = weatherObjectForeast22.getString("main");
                        String description22 = weatherObjectForeast22.getString("description");

                        String dt_txt22 = jsonObjectForecast22.getString("dt_txt");
                        //
                        //23.data
                        JSONObject jsonObjectForecast23= jsonArrayForecastList.getJSONObject(37);
                        JSONObject mainObjectForecast23 = jsonObjectForecast23.getJSONObject("main");
                        double temperature23 = mainObjectForecast23.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast23 = jsonObjectForecast23.getJSONArray("weather");
                        JSONObject weatherObjectForeast23 = weatherArrayForecast23.getJSONObject(0);
                        String main23 = weatherObjectForeast23.getString("main");
                        String description23 = weatherObjectForeast23.getString("description");

                        String dt_txt23 = jsonObjectForecast23.getString("dt_txt");
                        //
                        //24.data
                        JSONObject jsonObjectForecast24= jsonArrayForecastList.getJSONObject(39);
                        JSONObject mainObjectForecast24 = jsonObjectForecast24.getJSONObject("main");
                        double temperature24 = mainObjectForecast24.getDouble("temp")-273.15;

                        JSONArray weatherArrayForecast24 = jsonObjectForecast24.getJSONArray("weather");
                        JSONObject weatherObjectForeast24 = weatherArrayForecast24.getJSONObject(0);
                        String main24 = weatherObjectForeast24.getString("main");
                        String description24 = weatherObjectForeast24.getString("description");

                        String dt_txt24 = jsonObjectForecast24.getString("dt_txt");
                        //
                        SimpleDateFormat incomingDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        SimpleDateFormat wantedDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        SimpleDateFormat wantedHourFormat = new SimpleDateFormat("HH.mm");
                        Date dtxt1 = incomingDateFormat.parse(dt_txt9);
                        Date dtxt2 = incomingDateFormat.parse(dt_txt13);
                        Date dtxt3 =incomingDateFormat.parse(dt_txt17);
                        Date dtxt4 = incomingDateFormat.parse(dt_txt21);

                        Date htxt1 = incomingDateFormat.parse(dt_txt5);

                        String date1 = wantedDateFormat.format(dtxt1);
                        String date2 = wantedDateFormat.format(dtxt2);
                        String date3 = wantedDateFormat.format(dtxt3);
                        String date4 = wantedDateFormat.format(dtxt4);

                        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                        String day1 = dayFormat.format(dtxt1);
                        String day2 = dayFormat.format(dtxt2);
                        String day3 = dayFormat.format(dtxt3);
                        String day4 = dayFormat.format(dtxt4);

                        tvDateOne.setText(date1+" "+day1);
                        tvDateTwo.setText(date2+" "+day2);
                        tvDateThree.setText(date3+" "+day3);
                        tvDateFour.setText(date4+" "+day4);

                        //1.forecast
                        //String hourone1 = wantedHourFormat.format(htxt1);
                        String hourone1 = convertToAMPM(dt_txt5);
                        String hourtwo1 = convertToAMPM(dt_txt7);
                        String hourthree1 = convertToAMPM(dt_txt9);
                        String hourfour1 = convertToAMPM(dt_txt10);
                        tvHourOne1.setText(hourone1);
                        tvHourTwo1.setText(hourtwo1);
                        tvHourThree1.setText(hourthree1);
                        tvHourFour1.setText(hourfour1);

                        String weatherCondition = description5;
                        String cvc1 = correctWeatherCondition(weatherCondition);
                        //tvDescriptionOne1.setText(cvc1);
                        tvDescriptionOne1.setText(description5);
                        tvDescriptionTwo1.setText(description7);
                        tvDescriptionThree1.setText(description9);
                        tvDescriptionFour1.setText(description10);

                        //2.forecast
                        String hourone2 = convertToAMPM(dt_txt11);
                        String hourtwo2 = convertToAMPM(dt_txt12);
                        String hourthree2 = convertToAMPM(dt_txt13);
                        String hourfour2 = convertToAMPM(dt_txt14);
                        tvHourOne2.setText(hourone2);
                        tvHourTwo2.setText(hourtwo2);
                        tvHourThree2.setText(hourthree2);
                        tvHourFour2.setText(hourfour2);

                        tvDescriptionOne2.setText(description11);
                        tvDescriptionTwo2.setText(description12);
                        tvDescriptionThree2.setText(description13);
                        tvDescriptionFour2.setText(description14);

                        //3.forecast
                        String hourone3 = convertToAMPM(dt_txt15);
                        String hourtwo3 = convertToAMPM(dt_txt16);
                        String hourthree3 = convertToAMPM(dt_txt17);
                        String hourfour3 = convertToAMPM(dt_txt18);
                        tvHourOne3.setText(hourone3);
                        tvHourTwo3.setText(hourtwo3);
                        tvHourThree3.setText(hourthree3);
                        tvHourFour3.setText(hourfour3);

                        tvDescriptionOne3.setText(description15);
                        tvDescriptionTwo3.setText(description16);
                        tvDescriptionThree3.setText(description17);
                        tvDescriptionFour3.setText(description18);

                        //4.forecast
                        String hourone4 = convertToAMPM(dt_txt19);
                        String hourtwo4 = convertToAMPM(dt_txt20);
                        String hourthree4 = convertToAMPM(dt_txt21);
                        String hourfour4 = convertToAMPM(dt_txt22);
                        tvHourOne4.setText(hourone4);
                        tvHourTwo4.setText(hourtwo4);
                        tvHourThree4.setText(hourthree4);
                        tvHourFour4.setText(hourfour4);

                        tvDescriptionOne4.setText(description19);
                        tvDescriptionTwo4.setText(description20);
                        tvDescriptionThree4.setText(description21);
                        tvDescriptionFour4.setText(description22);


                        JSONObject jsonObjectCity = jsonResponseForecast.getJSONObject("city");
                        int timezone = jsonObjectCity.getInt("timezone");
                        long sunrise = jsonObjectCity.getLong("sunrise");
                        long sunset = jsonObjectCity.getLong("sunset");

                        sunrise -= 10800;
                        sunset -= 10800;

                        String sunriseTime = unixTimestampToAMPM(sunrise,timezone);
                        String sunsetTime = unixTimestampToAMPM(sunset,timezone);

                        tvSunrise.setText(sunriseTime);
                        tvSunset.setText(sunsetTime);


                        //linearLayoutsunsetAndSunrise.setVisibility(View.VISIBLE);

                        //item1
                        boolean isAfterSunset = !isAfterSunset(dt_txt,sunset,sunrise);
                        if (description.contains("cloud")) {
                            if (isAfterSunset){
                                ivHourlyForecast1.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast1.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description.contains("clear")) {
                            if (isAfterSunset){
                                ivHourlyForecast1.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast1.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description.contains("rain")) {
                            if (isAfterSunset){
                                ivHourlyForecast1.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast1.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description.contains("snow")) {
                            if (isAfterSunset){
                                ivHourlyForecast1.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast1.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast1.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp = (int)Math.round(temperature);
                        output = df.format(roundeddTemp);
                        tvTemp1.setText(output + "°C");
                        tvHumidity1.setText(humidity + "%");
                        tvSped1.setText(wind + " m/s");
                        String ampmTime = convertToAMPM(dt_txt);
                        tvHour1.setText(ampmTime);

                        //item2
                        boolean isAfterSunset1 = !isAfterSunset(dt_txt1,sunset,sunrise);
                        if (description1.contains("cloud")) {
                            if (isAfterSunset1){
                                ivHourlyForecast2.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast2.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description1.contains("clear")) {
                            if (isAfterSunset1){
                                ivHourlyForecast2.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast2.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description1.contains("rain")) {
                            if (isAfterSunset1){
                                ivHourlyForecast2.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast2.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description1.contains("snow")) {
                            if (isAfterSunset1){
                                ivHourlyForecast2.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast2.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast2.setImageResource(R.drawable.cloudysun);
                        }
                        int roundedddTemp = (int)Math.round(temperature1);
                        output = df.format(roundedddTemp);
                        tvTemp2.setText(output + "°C");
                        tvHumidity2.setText(humidity1 + "%");
                        tvSped2.setText(wind1 + " m/s");
                        String ampmTime1 = convertToAMPM(dt_txt1);
                        tvHour2.setText(ampmTime1);

                        //item3
                        boolean isAfterSunset2 = !isAfterSunset(dt_txt2,sunset,sunrise);
                        if (description2.contains("cloud")) {
                            if (isAfterSunset2){
                                ivHourlyForecast3.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast3.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description2.contains("clear")) {
                            if (isAfterSunset2){
                                ivHourlyForecast3.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast3.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description2.contains("rain")) {
                            if (isAfterSunset2){
                                ivHourlyForecast3.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast3.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description2.contains("snow")) {
                            if (isAfterSunset2){
                                ivHourlyForecast3.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast3.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast3.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp3 = (int)Math.round(temperature2);
                        output = df.format(roundeddTemp3);
                        tvTemp3.setText(output + "°C");
                        tvHumidity3.setText(humidity2 + "%");
                        tvSped3.setText(wind2 + " m/s");
                        String ampmTime2 = convertToAMPM(dt_txt2);
                        tvHour3.setText(ampmTime2);

                        //item4
                        boolean isAfterSunset3 = !isAfterSunset(dt_txt3,sunset,sunrise);
                        if (description3.contains("cloud")) {
                            if (isAfterSunset3){
                                ivHourlyForecast4.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast4.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description3.contains("clear")) {
                            if (isAfterSunset3){
                                ivHourlyForecast4.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast4.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description3.contains("rain")) {
                            if (isAfterSunset3){
                                ivHourlyForecast4.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast4.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description3.contains("snow")) {
                            if (isAfterSunset3){
                                ivHourlyForecast4.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast4.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast4.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp4 = (int)Math.round(temperature3);
                        output = df.format(roundeddTemp4);
                        tvTemp4.setText(output + "°C");
                        tvHumidity4.setText(humidity3 + "%");
                        tvSped4.setText(wind3 + " m/s");
                        String ampmTime3 = convertToAMPM(dt_txt3);
                        tvHour4.setText(ampmTime3);

                        //item5
                        boolean isAfterSunset4 = !isAfterSunset(dt_txt4,sunset,sunrise);
                        if (description4.contains("cloud")) {
                            if (isAfterSunset4){
                                ivHourlyForecast5.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast5.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description4.contains("clear")) {
                            if (isAfterSunset4){
                                ivHourlyForecast5.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast5.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description4.contains("rain")) {
                            if (isAfterSunset4){
                                ivHourlyForecast5.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast5.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description4.contains("snow")) {
                            if (isAfterSunset4){
                                ivHourlyForecast5.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast5.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast5.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp5 = (int)Math.round(temperature4);
                        output = df.format(roundeddTemp5);
                        tvTemp5.setText(output + "°C");
                        tvHumidity5.setText(humidity4 + "%");
                        tvSped5.setText(wind4 + " m/s");
                        String ampmTime4 = convertToAMPM(dt_txt4);
                        tvHour5.setText(ampmTime4);

                        //item6
                        boolean isAfterSunset5 = !isAfterSunset(dt_txt5,sunset,sunrise);
                        if (description5.contains("cloud")) {
                            if (isAfterSunset5){
                                ivHourlyForecast6.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast6.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description5.contains("clear")) {
                            if (isAfterSunset5){
                                ivHourlyForecast6.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast6.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description5.contains("rain")) {
                            if (isAfterSunset5){
                                ivHourlyForecast6.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast6.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description5.contains("snow")) {
                            if (isAfterSunset5){
                                ivHourlyForecast6.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast6.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast6.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp6 = (int)Math.round(temperature5);
                        output = df.format(roundeddTemp6);
                        tvTemp6.setText(output + "°C");
                        tvHumidity6.setText(humidity5 + "%");
                        tvSped6.setText(wind5 + " m/s");
                        String ampmTime5 = convertToAMPM(dt_txt5);
                        tvHour6.setText(ampmTime5);

                        //item7
                        boolean isAfterSunset6 = !isAfterSunset(dt_txt6,sunset,sunrise);
                        if (description6.contains("cloud")) {
                            if (isAfterSunset6){
                                ivHourlyForecast7.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast7.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description6.contains("clear")) {
                            if (isAfterSunset6){
                                ivHourlyForecast7.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast7.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description6.contains("rain")) {
                            if (isAfterSunset6){
                                ivHourlyForecast7.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast7.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description6.contains("snow")) {
                            if (isAfterSunset6){
                                ivHourlyForecast7.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast7.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast7.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp7 = (int)Math.round(temperature6);
                        output = df.format(roundeddTemp7);
                        tvTemp7.setText(output + "°C");
                        tvHumidity7.setText(humidity6 + "%");
                        tvSped7.setText(wind6 + " m/s");
                        String ampmTime6 = convertToAMPM(dt_txt6);
                        tvHour7.setText(ampmTime6);

                        //item8
                        boolean isAfterSunset7 = !isAfterSunset(dt_txt7,sunset,sunrise);
                        if (description7.contains("cloud")) {
                            if (isAfterSunset7){
                                ivHourlyForecast8.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast8.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description7.contains("clear")) {
                            if (isAfterSunset7){
                                ivHourlyForecast8.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast8.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description7.contains("rain")) {
                            if (isAfterSunset7){
                                ivHourlyForecast8.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast8.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description7.contains("snow")) {
                            if (isAfterSunset7){
                                ivHourlyForecast8.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast8.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast8.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp8 = (int)Math.round(temperature7);
                        output = df.format(roundeddTemp8);
                        tvTemp8.setText(output + "°C");
                        tvHumidity8.setText(humidity7 + "%");
                        tvSped8.setText(wind7 + " m/s");
                        String ampmTime7 = convertToAMPM(dt_txt7);
                        tvHour8.setText(ampmTime7);

                        //item9
                        boolean isAfterSunset8 = !isAfterSunset(dt_txt8,sunset,sunrise);
                        if (description8.contains("cloud")) {
                            if (isAfterSunset8){
                                ivHourlyForecast9.setImageResource(R.drawable.cloudysun);
                            } else{
                                ivHourlyForecast9.setImageResource(R.drawable.cloudymoonnn);
                            }
                        } else if (description8.contains("clear")) {
                            if (isAfterSunset8){
                                ivHourlyForecast9.setImageResource(R.drawable.sunnn);
                            } else{
                                ivHourlyForecast9.setImageResource(R.drawable.crescentmoon);
                            }
                        } else if (description8.contains("rain")) {
                            if (isAfterSunset8){
                                ivHourlyForecast9.setImageResource(R.drawable.rainnnn);
                            } else{
                                ivHourlyForecast9.setImageResource(R.drawable.rainymoonnn);
                            }
                        } else if (description8.contains("snow")) {
                            if (isAfterSunset8){
                                ivHourlyForecast9.setImageResource(R.drawable.snowyy);
                            } else{
                                ivHourlyForecast9.setImageResource(R.drawable.snowww);
                            }
                        } else {
                            ivHourlyForecast9.setImageResource(R.drawable.cloudysun);
                        }
                        int roundeddTemp9 = (int)Math.round(temperature8);
                        output = df.format(roundeddTemp9);
                        tvTemp9.setText(output + "°C");
                        tvHumidity9.setText(humidity8 + "%");
                        tvSped9.setText(wind8 + " m/s");
                        String ampmTime8 = convertToAMPM(dt_txt8);
                        tvHour9.setText(ampmTime8);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error1) {
                    //Log.d("sueda11", response1.toString());

                }
            });
            RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
            requestQueue1.add(stringRequest1);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Log.d("response", response);
                    String output = "";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");
                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");
                        int deg = jsonObjectWind.getInt("deg");
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");
                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonResponse.getString("name");
                        long sunrise = jsonObjectSys.getLong("sunrise");
                        long sunset = jsonObjectSys.getLong("sunset");
                        int timezone = jsonResponse.getInt("timezone");

                        sunrise -= 10800;
                        sunset -= 10800;

                        boolean isDaytime = isDaytime(sunrise, sunset, timezone);

                        if (isDaytime){
                            allLayout.setBackgroundResource(R.drawable.day_backgrund);
                            hourlyforecastLayout.setBackgroundResource(R.drawable.background7);
                            ivHumidity.setImageResource(R.drawable.humidityyy);
                            ivWindsped.setImageResource(R.drawable.windspeed);
                            ivPressure.setImageResource(R.drawable.barometer);
                            ivCloudiness.setImageResource(R.drawable.cloudss);
                            forecastDay1.setBackgroundResource(R.drawable.forecastbackground1);
                            forecastDay2.setBackgroundResource(R.drawable.forecastbackground2);
                            forecastDay3.setBackgroundResource(R.drawable.forecastbackground3);
                            forecastDay4.setBackgroundResource(R.drawable.forecastbackground4);
                            if (description.contains("cloud")) {
                                ivWeatherIcon.setImageResource(R.drawable.cloudysun);
                            } else if (description.contains("clear")) {
                                ivWeatherIcon.setImageResource(R.drawable.sunnn);
                            } else if (description.contains("rain")) {
                                ivWeatherIcon.setImageResource(R.drawable.rainnnn);
                            } else if (description.contains("snow")) {
                                ivWeatherIcon.setImageResource(R.drawable.snowyy);
                            } else {
                                ivWeatherIcon.setImageResource(R.drawable.cloudysun);
                            }
                        } else{
                            allLayout.setBackgroundResource(R.drawable.background);
                            hourlyforecastLayout.setBackgroundResource(R.drawable.background8);
                            ivHumidity.setImageResource(R.drawable.humiditywhite);
                            ivWindsped.setImageResource(R.drawable.windwhite);
                            ivPressure.setImageResource(R.drawable.barometerwhite);
                            ivCloudiness.setImageResource(R.drawable.cloudywhite);
                            forecastDay1.setBackgroundResource(R.drawable.forecastbackground5);
                            forecastDay2.setBackgroundResource(R.drawable.forecastbackground6);
                            forecastDay3.setBackgroundResource(R.drawable.forecastbackground7);
                            forecastDay4.setBackgroundResource(R.drawable.foreecastbackground8);
                            if (description.contains("cloud")) {
                                ivWeatherIcon.setImageResource(R.drawable.cloudymoonnn);
                            } else if (description.contains("clear")) {
                                ivWeatherIcon.setImageResource(R.drawable.crescentmoon);
                            } else if (description.contains("rain")) {
                                ivWeatherIcon.setImageResource(R.drawable.rainymoonnn);
                            } else if (description.contains("snow")) {
                                ivWeatherIcon.setImageResource(R.drawable.snowww);
                            } else {
                                ivWeatherIcon.setImageResource(R.drawable.cloudymoonnn);
                            }
                        }

                        String sunriseTime = unixTimestampToAMPM(sunrise,timezone);
                        String sunsetTime = unixTimestampToAMPM(sunset,timezone);




                        int roundedTemp = (int)Math.round(temp);
                        int roundedFeelsLike = (int)Math.round(feelsLike);
                        output = df.format(roundedTemp);
                        ;
                        tvResult.setText(output + " C°");
                        tvFeelsLike.setText("feels like : " + df.format(roundedFeelsLike) + " C°");
                        //
                        String[] words = description.split(" ");
                        StringBuilder result = new StringBuilder();
                        for (String word : words) {
                            if (!word.isEmpty()) {
                                String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                                result.append(capitalizedWord).append(" ");
                            }
                        }
                        String capitalizedDescription = result.toString().trim();
                        tvDescription.setText(capitalizedDescription);

                        String capitalizedCityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
                        String capitalizedCountryName = countryName.substring(0, 1).toUpperCase() + countryName.substring(1);
                        tvCityName.setText(capitalizedCityName + " - " + capitalizedCountryName);


                        tvHumidity.setText(humidity + "%");
                        tvWindSpeed.setText(wind + " m/s");
                        tvPressure.setText(pressure + " hPa");
                        tvCloudiness.setText(clouds + " %");

                        String currentTime = hour + "." + minute;
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        //Date currentDate = sdf.parse(currentTime);
                        //Date sunsetDate = sdf.parse(sunsetTime);



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
    }

    private boolean isDaytime(long sunriseTimestamp, long sunsetTimestamp, int timezone) {
        long currentTime = System.currentTimeMillis() / 1000;
        sunriseTimestamp += timezone;
        sunsetTimestamp += timezone;

        return currentTime >= sunriseTimestamp && currentTime < sunsetTimestamp;
    }

    private boolean containsKeyword(String weatherSituation, String keyword){
        return weatherSituation.toLowerCase().contains(keyword);
    }
    private boolean isBeforeSunset(int hour){
        int sunsetHour = 18;
        return hour < sunsetHour;
    }
    private String formatLongToTime(long timeInMillis){
        Date date = new Date(timeInMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    private boolean isAfterSunset(String inputTime, long sunsetTime, long sunriseTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        Calendar inputCalendar = Calendar.getInstance();
        inputCalendar.setTime(date);

        Calendar sunsetCalendar = Calendar.getInstance();
        sunsetCalendar.setTimeInMillis(sunsetTime * 1000); // Unix zamanı milisaniyeye çeviriyoruz

        Calendar sunriseCalendar = Calendar.getInstance();
        sunriseCalendar.setTimeInMillis(sunriseTime * 1000); // Unix zamanı milisaniyeye çeviriyoruz

        return inputCalendar.after(sunsetCalendar) && inputCalendar.before(sunriseCalendar);
    }

    private String unixTimestampToAMPM(long timestamp, int timezoneOffset) {
        long utcTimestamp = timestamp + timezoneOffset;
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        Date date = new Date(utcTimestamp * 1000);
        return sdf.format(date);
    }
    public static String correctWeatherCondition(String weatherCondition) {

        return weatherCondition.substring(0, 1).toUpperCase() + weatherCondition.substring(1);
    }
    private String convertToAMPM(String dt_txt) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");

            Date date = inputFormat.parse(dt_txt);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dt_txt;
        }
    }



}

