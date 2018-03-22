package com.example.utilisateur.projetl3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity {
    final static String url = "http://192.168.43.244:1345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final EditText etAge = (EditText) findViewById(R.id.etAge);   //on trouve le textview etAge (issu du xml) et on dit qu'il est du type EditText puis on l'appelle etAge
        final EditText etPrenom = (EditText) findViewById(R.id.etPrenom);
        final EditText etNom = (EditText) findViewById(R.id.etNom);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText etMDP = (EditText) findViewById(R.id.etMDP);

        final Button buttonSinscrire = (Button) findViewById(R.id.buttonSinscrire);



        buttonSinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String prenom = etPrenom.getText().toString();
                final String nom = etNom.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String email = etEmail.getText().toString();
                final String pseudo = etPseudo.getText().toString();
                final String mdp = etMDP.getText().toString();
                // PERMETTRA ENSUITE DE RECUPERER LES DONNEES DE L'UTILISATEUR AFIN DE LES STOCKER


                class Loader extends AsyncTask<Void, Void, JSONObject> {
                    ProgressDialog dialog;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        if (android.os.Build.VERSION.SDK_INT >= 11) {
                            dialog = new ProgressDialog(RegisterActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
                        } else {
                            dialog = new ProgressDialog(RegisterActivity.this);
                        }
                        dialog.setMessage(Html.fromHtml("<b>" + "Chargement..." + "</b>"));
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(false);
                        dialog.show();
                    }

                    @Override
                    protected JSONObject doInBackground(Void... params) {

                        return postJsonObject("L'url vers lequel le jsonObject sera envoyé", makingJson());
                    }

                    @Override
                    protected void onPostExecute(JSONObject result) {
                        super.onPostExecute(result);

                        if (result != null) {
                            dialog.dismiss();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Success post jsonObject", Toast.LENGTH_LONG).show();
                        }
                    }

                }}

            public JSONObject makingJson() {

                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("prenom", etPrenom.getText().toString());
                    jsonObject.put("nom", etNom.getText().toString());
                    jsonObject.put("age", Integer.parseInt(etAge.getText().toString()));
                    jsonObject.put("email", etEmail.getText().toString());
                    jsonObject.put("pseudo", etPseudo.getText().toString());
                    jsonObject.put("mdp", etMDP.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                //queue.add(jsonObject);
                return jsonObject;
            }


            public JSONObject postJsonObject(String url, JSONObject registerJobj) {
                InputStream inputStream = null;
                String result = "";
                try {

                    // 1. create HttpClient
                    HttpClient httpclient = new DefaultHttpClient();

                    // 2. make POST request to the given URL

                    //http://localhost:9000/api/products/GetAllProducts
                    HttpPost httpPost = new HttpPost(url);

                    System.out.println(url);
                    String json = "";

                    // 4. convert JSONObject to JSON to String

                    json = registerJobj.toString();

                    System.out.println(json);
                    // 5. set json to StringEntity
                    StringEntity se = new StringEntity(json);

                    // 6. set httpPost Entity
                    httpPost.setEntity(se);

                    // 7. Set some headers to inform server about the type of the content
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json");

                    // 8. Execute POST request to the given URL
                    HttpResponse httpResponse = httpclient.execute(httpPost);

                    // 9. receive response as inputStream
                    inputStream = httpResponse.getEntity().getContent();

                    // 10. convert inputstream to string
                    if (inputStream != null)
                        result = convertInputStreamToString(inputStream);
                    else
                        result = "Ooops...Did not work!";

                } catch (Exception e) {
                    Log.d("InputStream", e.getLocalizedMessage());
                }

                JSONObject json = null;
                try {

                    json = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // 11. return result

                return json;
            }

            private String convertInputStreamToString(InputStream inputStream) throws IOException {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null)
                    result += line;

                inputStream.close();
                return result;
            }
        });

        }
}