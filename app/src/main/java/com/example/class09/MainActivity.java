package com.example.class09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.PriorityQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   private EditText name,number,pass;
   private Button signUpbtn,logInbtn;

   public  static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameId);
        number = findViewById(R.id.numberId);
        pass = findViewById(R.id.passId);
        signUpbtn = findViewById(R.id.signUpId);
        logInbtn = findViewById(R.id.loginbtnId);


        apiInterface = ApiCall.getApiClient().create(ApiInterface.class);

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                perfromReg();
            }
        });

        logInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void perfromReg() {
        Call<Student> call = apiInterface.performRegistration(name.getText().toString(),
                number.getText().toString(),pass.getText().toString());


        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {


                if (response.body().getResponse().equals("success")){

                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                Log.d("Error","Call"+call+"T"+t);

            }
        });
    }
}
