package br.com.local.listapadrinhosmagicos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RequestQueue queue;
    String url = "http://api-padrinhos-magicos.herokuapp.com/temporadas";
    List<Temporadas> temporadasList;
    Button buttonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();

        buttonBuscar = findViewById(R.id.buttonBuscar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        temporadasList = new ArrayList<>();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregaDados();
            }
        });

    }


   public void carregaDados(){
       ProgressDialog progressDialog = new ProgressDialog(this);
       progressDialog.setMessage("Carregando Dados...");
       progressDialog.show();

       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       progressDialog.dismiss();
                       try {
                           JSONObject jsonObject = new JSONObject(response);
                           JSONArray jsonArray = jsonObject.getJSONArray("temporadas");

                           for (int i = 0; i < jsonArray.length(); i++) {
                               JSONObject object = jsonArray.getJSONObject(i);
                               Temporadas temporadas = new Temporadas(
                                       object.getString("temporada"),
                                       object.getString("totalEpisodios"),
                                       object.getString("dataDeLancamento")
                               );
                               temporadasList.add(temporadas);
                           }
                           adapter = new Adapter(getApplicationContext(), temporadasList);
                           recyclerView.setAdapter(adapter);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               progressDialog.dismiss();
               Toast.makeText(getApplicationContext(),
                       error.getMessage(),
                       Toast.LENGTH_SHORT).show();
           }
       });
       queue = Volley.newRequestQueue(this);
       queue.add(stringRequest);
   }



   /* public void listaTemporadas(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("temporadas");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                int id = object.getInt("id");
                                String temporada = object.getString("temporada");
                                String episodios = object.getString("totalEpisodios");
                                String dataLancamento = object.getString("dataDeLacamento");

                                Temporadas temporadas = new Temporadas();
                                temporadas.setTemporada(temporada);
                                temporadas.setEpisodios(episodios);
                                temporadas.setDataLancamento(dataLancamento);

                                temporadasList.add(temporadas);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    */
}