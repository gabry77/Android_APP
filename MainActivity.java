package org.feup.apm.testexchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends Activity {
    TextView test1, test2;
    Spinner spinner, spinner2, spinner3, spinner4;
    EditText PutValue;
    private TextView ShowSpinner2;
    private TextView ShowSpinner3;
    private TextView ShowSpinner4;
    private static final String KEY_TEXT_VALUE_1 = "textValue1";
    private static final String KEY_TEXT_VALUE_2 = "textValue2";
    private static final String KEY_TEXT_VALUE_3 = "textValue3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PutValue = findViewById(R.id.PutValue);
        ShowSpinner2 = findViewById(R.id.ShowSpinner2);
        ShowSpinner3 = findViewById(R.id.ShowSpinner3);
        ShowSpinner4 = findViewById(R.id.ShowSpinner4);
        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        String[] countryNames = getResources().getStringArray(R.array.Country_list);
        int flags[] = {R.drawable.australia, R.drawable.brasil, R.drawable.bulgaria, R.drawable.canada, R.drawable.china, R.drawable.republica_checa,
                R.drawable.dinamarca, R.drawable.hong_kong, R.drawable.eua, R.drawable.india, R.drawable.japao, R.drawable.mexico,
                R.drawable.malasia, R.drawable.russia, R.drawable.singapore, R.drawable.south_korea, R.drawable.sweden, R.drawable.suica,
                R.drawable.turkish, R.drawable.reino_unido, R.drawable.eu};
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), flags, countryNames);
        spinner.setAdapter(customAdapter);
        spinner2.setAdapter(customAdapter);
        spinner3.setAdapter(customAdapter);
        spinner4.setAdapter(customAdapter);
        if (savedInstanceState != null) {
            CharSequence savedText = savedInstanceState.getCharSequence(KEY_TEXT_VALUE_1);
            ShowSpinner2.setText(savedText);
            CharSequence savedText2 = savedInstanceState.getCharSequence(KEY_TEXT_VALUE_2);
            ShowSpinner3.setText(savedText2);
            CharSequence savedText3 = savedInstanceState.getCharSequence(KEY_TEXT_VALUE_3);
            ShowSpinner4.setText(savedText3);
        }

        final Button button = findViewById(R.id.ConvertButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onItemSelected(spinner, spinner2, ShowSpinner2);
                onItemSelected(spinner, spinner3, ShowSpinner3);
                onItemSelected(spinner, spinner4, ShowSpinner4);

            }
        });

        Button ListRates = findViewById(R.id.ListButton);
        ListRates.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, List_Rates.class));
            }
        });
    }

    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_TEXT_VALUE_1, ShowSpinner2.getText());
        outState.putCharSequence(KEY_TEXT_VALUE_2, ShowSpinner3.getText());
        outState.putCharSequence(KEY_TEXT_VALUE_3, ShowSpinner4.getText());
    }
    public void onItemSelected(Spinner spinner, Spinner spinner2, TextView ShowSpinner) {
        String currency, currency2;
        GetExchangeRate httpRate1;
        int value = spinner.getSelectedItemPosition();
        int value2 = spinner2.getSelectedItemPosition();
        String value_2 = String.valueOf(value2);
        Log.i("spinner 2 selected item", value_2);
        String value1 = String.valueOf(value);
        Log.i("spinner selected item", value1);


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
        Log.i("currency", currency);

        switch (value2) {
            case (0):
                currency2 = "AUD";
                break;
            case (1):
                currency2 = "BRL";
                break;
            case (2):
                currency2 = "BGN";
                break;
            case (3):
                currency2 = "CAD";
                break;
            case (4):
                currency2 = "CNY";
                break;
            case (5):
                currency2 = "CZK";
                break;
            case (6):
                currency2 = "DKK";
                break;
            case (7):
                currency2 = "HKD";
                break;
            case (8):
                currency2 = "USD";
                break;
            case (9):
                currency2 = "INR";
                break;
            case (10):
                currency2 = "JPY";
                break;
            case (11):
                currency2 = "MXN";
                break;
            case (12):
                currency2 = "MYR";
                break;
            case (13):
                currency2 = "RUB";
                break;
            case (14):
                currency2 = "SGD";
                break;
            case (15):
                currency2 = "KRW";
                break;
            case (16):
                currency2 = "SEK";
                break;
            case (17):
                currency2 = "CHF";
                break;
            case (18):
                currency2 = "TRY";
                break;
            case (19):
                currency2 = "GBP";
                break;
            case (20):
                currency2 = "EUR";
                break;
            default:
                currency2 = "EUR";
        }
        Log.i("currency2", currency2);
        httpRate1 = new GetExchangeRate(currency, currency2, ShowSpinner);
        Thread thr1 = new Thread(httpRate1);
        thr1.start();
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

    public void getShowText(final TextView Show, final String terminal) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (Show == ShowSpinner2) {
                    ShowSpinner2.setText(terminal);
                    //return ShowSpinner2;
                } else if (Show == ShowSpinner3) {
                    ShowSpinner3.setText(terminal);
                    //   return ShowSpinner3;
                } else {
                    ShowSpinner4.setText(terminal);
                    // return ShowSpinner4;
                }
            }
        });
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

//**************************************************************************
//Internal class to call HTTP operation in a separate thread

    public class GetExchangeRate implements Runnable {
        String currency;
        String currency2;
        TextView ShowSpinner_;

        GetExchangeRate(String toCurrency, String secondCurrency, TextView ShowSpinner) {
            currency = toCurrency;
            currency2 = secondCurrency;
            ShowSpinner_ = ShowSpinner;
        }

        @Override
        public void run() {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                if (currency.equals(currency2)) {
                    String text_f = PutValue.getText().toString();
                    String last_value = String.valueOf(text_f);
                    getShowText(ShowSpinner_, last_value);
                } else {
                    url = new URL("http://api.fixer.io/latest?base=" + currency + "&symbols=" + currency2);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setUseCaches(false);


                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        String response = readStream(urlConnection.getInputStream());
                        Log.i("Value of response url", response);
                        String val_1 = getStringJson(response, currency2);
                        Log.i("Valor 1 do GetExchange ", val_1);
                        String text_f = PutValue.getText().toString();
                        Float myEditNum = Float.parseFloat(text_f);
                        Float val_2 = Float.parseFloat(val_1);
                        String last_value = String.valueOf(myEditNum * val_2);
                        getShowText(ShowSpinner_, last_value);
                    } else
                        getShowText(ShowSpinner_, "Code: " + responseCode);
                }


            } catch (Exception e) {
                getShowText(ShowSpinner_, e.toString());
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
        }
    }

}


