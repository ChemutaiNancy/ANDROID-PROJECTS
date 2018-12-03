package com.chemutai.mathree;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    //validating username and password

    Context mContext;
    AlertDialog mAlertDialog;
    BackgroundWorker (Context ctx)
    {
        mContext = ctx;
    }//constructor

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://192.168.0.53/passenger/login.php";
        String register_url = "http://192.168.0.53/passenger/register.php";

        if (type.equals("login"))
        {
            try {
                String phoneno = params[6];
                String pass = params[7];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("phoneNo", "UTF-8")+"="+URLEncoder.encode(phoneno, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("register"))
        {
            try {
                String fname = params[1];
                String mname = params[2];
                String sname = params[3];
                String idno = params[4];
                String dob = params[5];
                String phoneno = params[6];
                String pass = params[7];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("firstName", "UTF-8")+"="+URLEncoder.encode(fname, "UTF-8")+"&"
                        +URLEncoder.encode("middleName", "UTF-8")+"="+URLEncoder.encode(mname, "UTF-8")+"&"
                        +URLEncoder.encode("surname", "UTF-8")+"="+URLEncoder.encode(sname, "UTF-8")+"&"
                        +URLEncoder.encode("idNumber", "UTF-8")+"="+URLEncoder.encode(idno, "UTF-8")+"&"
                        +URLEncoder.encode("dob", "UTF-8")+"="+URLEncoder.encode(dob, "UTF-8")+"&"
                        +URLEncoder.encode("phoneNo", "UTF-8")+"="+URLEncoder.encode(phoneno, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        mAlertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        mAlertDialog.setMessage(result);
        mAlertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
