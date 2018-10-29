package com.chemutai.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.darksky.net/forecast/d6186559c591840950c5dcf2969e68a2/-1.28333,%2036.81667";

    TextView txtTimeZone, txtTemperature, txtSummary, txtVisibility, txtTime, txtTemp, txtTimeOne, txtTempOne,
    txtTimeTwo, txtTempTwo, txtTimeThree, txtTempThree, txtTimeFour, txtTempFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimeZone = findViewById(R.id.txtTimeZone);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtSummary = findViewById(R.id.txtSummary);
        txtVisibility = findViewById(R.id.txtVisibility);
        txtTime = findViewById(R.id.txtTime);
        txtTemp = findViewById(R.id.txtTemp);
        txtTimeOne = findViewById(R.id.txtTimeOne);
        txtTempOne = findViewById(R.id.txtTempOne);
        txtTimeTwo = findViewById(R.id.txtTimeTwo);
        txtTempTwo = findViewById(R.id.txtTempTwo);
        txtTimeThree = findViewById(R.id.txtTempThree);
        txtTempThree = findViewById(R.id.txtTempThree);
        txtTimeFour = findViewById(R.id.txtTimeFour);
        txtTempFour = findViewById(R.id.txtTempFour);

        fetch();
    }

    private void fetch() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject mainObject = new JSONObject(responseString);//convert data to json
                    String timezone = mainObject.getString("timezone");
                    txtTimeZone.setText(timezone);

                    String summary = mainObject.getJSONObject("currently").getString("summary");
                    txtSummary.setText(summary);

                    double temperature = mainObject.getJSONObject("currently").getDouble("temperature");
                    temperature = (temperature -32) * 5/9.0;
                    txtTemperature.setText(temperature+"");

                    double visibility = mainObject.getJSONObject("currently").getDouble("visibility");
                    visibility = visibility * 1.6;
                    txtVisibility.setText("Visibility is: "+visibility);


                    long time = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getLong("time");
                    Date date = new Date(time*1000);
                    DateFormat d = new SimpleDateFormat("HH:mm");
                    txtTime.setText(d.format(date)+"");

                    double temp = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(0).getDouble("temperature");
                    temp = (temp-32)*5/9.0;
                    txtTemp.setText(""+temp);

                    int timeOne = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getInt("time");
                    Date dateOne = new Date(timeOne*1000);
                    DateFormat d1 = new SimpleDateFormat("HH:mm");
                    txtTimeOne.setText(""+d1.format(dateOne));

                    double tempOne = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(1).getDouble("temperature");
                    tempOne = (tempOne-32)*5/9.0;
                    txtTempOne.setText(""+tempOne);

                    int timeTwo = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getInt("time");
                    Date dateTwo = new Date(time*1000);
                    DateFormat d2 = new SimpleDateFormat("HH:mm");
                    txtTimeTwo.setText(""+d2.format(dateTwo));

                    double tempTwo = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(2).getDouble("temperature");
                    tempTwo = (tempTwo-32)*5/9.0;
                    txtTempTwo.setText(""+tempTwo);

                    int timeThree = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getInt("time");
                    Date dateThree = new Date(timeThree*1000);
                    DateFormat d3 = new SimpleDateFormat("HH:mm");
                    txtTimeThree.setText(""+d3.format(dateThree));

                    double tempThree = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(3).getDouble("temperature");
                    tempThree = (tempThree-32)*5/9.0;
                    txtTempThree.setText(""+tempThree);

                    int timeFour = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getInt("time");
                    Date dateFour = new Date(time*1000);
                    DateFormat d4 = new SimpleDateFormat("HH:mm");
                    txtTimeFour.setText(""+d4.format(dateFour));

                    double tempFour = mainObject.getJSONObject("hourly").getJSONArray("data").getJSONObject(4).getDouble("temperature");
                    tempFour = (tempFour-32)*5/9.0;
                    txtTempFour.setText(""+tempFour);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    d6186559c591840950c5dcf2969e68a2
}
