package com.example.abhishek.blood;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button submit, login;
    EditText name, password, gender, mobile, city;
    Spinner simpleSpinner;
    Spinner blood_grp;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        simpleSpinner = (Spinner)findViewById(R.id.blood_grp);
        final List<String> simplelist = new ArrayList<String>();
        simplelist.add("Select Blood Group");
        simplelist.add("A+");
        simplelist.add("A-");
        simplelist.add("B+");
        simplelist.add("B-");
        simplelist.add("O+");
        simplelist.add("O-");
        simplelist.add("AB+");
        simplelist.add("AB-");
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(signup.this,R.layout.support_simple_spinner_dropdown_item,simplelist);
        simpleSpinner.setAdapter(simpleAdapter);
        simpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(signup.this,simplelist.get(position),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                simpleSpinner.setSelection(0);
            }
        });

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        blood_grp = (Spinner) findViewById(R.id.blood_grp);
        gender = (EditText) findViewById(R.id.gender);
        mobile = (EditText) findViewById(R.id.mobile);
        city = (EditText) findViewById(R.id.city_search);
        submit = (Button) findViewById(R.id.submit);
        login = (Button) findViewById(R.id.login);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String password1 = password.getText().toString();
                String blood_grp1 = blood_grp.getSelectedItem().toString();
                String gender1 = gender.getText().toString();
                String mobile1 = mobile.getText().toString();
                String city1 = city.getText().toString();
                if(!(name1.contentEquals("")) && !(password1.contentEquals("")) && !(blood_grp1.contentEquals("")) &&!(mobile1.contentEquals("")) && !(city1.contentEquals(""))) {
                    HashMap<String,String> dataMap = new HashMap<>();
                    dataMap.put("Name",name1);
                    dataMap.put("Password",password1);
                    dataMap.put("BloodGrp",blood_grp1);
                    dataMap.put("Gender",gender1);
                    dataMap.put("Mobile",mobile1);
                    dataMap.put("City",city1);

                    mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Registration successfully Done", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Registration not successfully Done", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{

                }
            }

        });


    }





}

