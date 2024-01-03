package com.example.proyectoapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoapis.interfaces.GatoApi;
import com.example.proyectoapis.models.Gato;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        button.setOnClickListener(l->find(editText.getText().toString()));
    }
    private void find(String id){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://cat-fact.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        GatoApi gatoApi=retrofit.create(GatoApi.class);
        Call<Gato> call=gatoApi.find(id);
        call.enqueue(new Callback<Gato>() {
            @Override
            public void onResponse(Call<Gato> call, Response<Gato> response) {
                try {
                    if (response.isSuccessful()){
                        Gato gato=response.body();
                        textView.setText(gato.getText());

                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Gato> call, Throwable t) {
                Toast.makeText(MainActivity.this, "NO SE PUDO CONECTAR", Toast.LENGTH_SHORT).show();
            }
        });
    }


}