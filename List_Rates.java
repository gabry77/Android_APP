package org.feup.apm.testexchange;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class List_Rates extends Activity {
    List<RowItem> RowItems = new ArrayList<>();
    String[] currency2 = {"AUD", "BRL", "BGN", "CAD", "CNY", "CZK", "DKK", "HKD", "USD", "INR", "JPY", "MXN", "MYR", "RUB", "SGD", "KRW", "SEK", "CHF", "TRY", "GBP", "EUR"};
    String[] val_1 = new String[21];
    String[] countryNames;
    int flags[] = {R.drawable.australia, R.drawable.brasil, R.drawable.bulgaria, R.drawable.canada, R.drawable.china, R.drawable.republica_checa,
            R.drawable.dinamarca, R.drawable.hong_kong, R.drawable.eua, R.drawable.india, R.drawable.japao, R.drawable.mexico,
            R.drawable.malasia, R.drawable.russia, R.drawable.singapore, R.drawable.south_korea, R.drawable.sweden, R.drawable.suica,
            R.drawable.turkish, R.drawable.reino_unido, R.drawable.eu};

    ListView List_of_Rates;
    ListAdapter list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_rates);
        countryNames = getResources().getStringArray(R.array.Country_list);
        List_of_Rates = findViewById(R.id.List_of_Rates);

        for (int k=0; k<=20; k++) {
            RowItem row_i = new RowItem(flags[k], countryNames[k], "1.0");
            RowItems.add(row_i);
        }
        list = new ListAdapter();
        List_of_Rates.setAdapter(list);

        final Spinner spinnerList = findViewById(R.id.SpinnerList);
        CustomAdapter customAdapter = new CustomAdapter(this, flags, countryNames);
        spinnerList.setAdapter(customAdapter);
        spinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int value = spinnerList.getSelectedItemPosition();

                String currency;
                GetExchangeRate1 httpRate2;
                switch (value) {
                    case (0):
                        currency = "AUD";
                        break;
                    case (1):
                        currency = "BRL";
                        break;
                    case (2):
                        currency = "BGN";
                        break;
                    case (3):
                        currency = "CAD";
                        break;
                    case (4):
                        currency = "CNY";
                        break;
                    case (5):
                        currency = "CZK";
                        break;
                    case (6):
                        currency = "DKK";
                        break;
                    case (7):
                        currency = "HKD";
                        break;
                    case (8):
                        currency = "USD";
                        break;
                    case (9):
                        currency = "INR";
                        break;
                    case (10):
                        currency = "JPY";
                        break;
                    case (11):
                        currency = "MXN";
                        break;
                    case (12):
                        currency = "MYR";
                        break;
                    case (13):
                        currency = "RUB";
                        break;
                    case (14):
                        currency = "SGD";
                        break;
                    case (15):
                        currency = "KRW";
                        break;
                    case (16):
                        currency = "SEK";
                        break;
                    case (17):
                        currency = "CHF";
                        break;
                    case (18):
                        currency = "TRY";
                        break;
                    case (19):
                        currency = "GBP";
                        break;
                    case (20):
                        currency = "EUR";
                        break;
                    default:
                        currency = "EUR";
                }


                httpRate2 = new GetExchangeRate1(currency);
                Thread thr1 = new Thread(httpRate2);
                thr1.start();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }

    public String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            return e.getMessage();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }
        }
        return response.toString();
    }

    public static String getStringJson(String StrCurrencyJson, String key) {
        try {
            final String RATE = "rates";

            JSONObject currencyJson = new JSONObject(StrCurrencyJson);

            currencyJson = currencyJson.getJSONObject(RATE);

            return currencyJson.getString(key);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    class ListAdapter extends ArrayAdapter<RowItem> {

        ListAdapter() {
            super(List_Rates.this, R.layout.customadapter, RowItems);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.customadapter, parent, false);
            }
            RowItem r = RowItems.get(position);

            ((ImageView) row.findViewById(R.id.spinner_image)).setImageResource(r.getImage());
            ((TextView) row.findViewById(R.id.spinner_textView)).setText(r.getCountryNames());
            ((TextView) row.findViewById(R.id.listText)).setText(r.getRate());
            return row;
        }


    }

    private void runThread(){
        runOnUiThread(new Thread (new Runnable() {
            @Override
            public void run() {
                for (int k=0; k<currency2.length; k++) {
                    RowItem row_i = list.getItem(k);
                    row_i.setRate(val_1[k]);
                }
                list.notifyDataSetChanged();

            }
        }));
    }


    public class GetExchangeRate1 implements Runnable {
        String currency;

        GetExchangeRate1(String toCurrency){
            currency = toCurrency;

        }


        @Override
        public void run() {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://api.fixer.io/latest?base=" + currency);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setUseCaches(false);

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 200) {
                    String response = readStream(urlConnection.getInputStream());
                    Log.i("Value of response url", response);


                    for (int j = 0; j < currency2.length; j++) {

                        if (currency != currency2[j]) {
                           val_1[j] = getStringJson(response, currency2[j]);
                            //Log.i("value of val_1", val_1[j]);

                        } else {

                            val_1[j] = "1";
                            //Log.i("value of val_1, qd é =",val_1[j]);

                        }

                    }
                    runThread();
                }

            } catch (Exception e) {
                System.err.println("Caugth Exception"+e.getMessage());
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
        }

    }





}