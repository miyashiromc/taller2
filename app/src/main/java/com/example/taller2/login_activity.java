package com.example.taller2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class login_activity extends AppCompatActivity implements Asynchtask {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });

        TextInputEditText user = findViewById(R.id.textInputEditText);
        TextInputEditText pass = findViewById(R.id.textInputEditText2);
        Button btLogin = findViewById(R.id.button);






        btLogin.setOnClickListener(v -> {
            String username = user.getText() != null ? user.getText().toString().trim() : "";
            String password = pass.getText() != null ? pass.getText().toString().trim() : "";
            Map<String, String> datos = new HashMap<String, String>();

            WebService ws= new WebService(
                    "https://revistas.uteq.edu.ec/ws/login.php?usr="+username+"&pass="+password,
                    datos, login_activity.this, login_activity.this);
            ws.execute("GET");

        });


    }


    @Override
    public void processFinish(String result) throws JSONException {
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

    }
}