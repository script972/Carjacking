package com.script972.carjacking.ui.acitivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.script972.carjacking.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    ListView listusertask;
    Button  btnAddNew;
    EditText edtValue;

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    private List<String> DiscrTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.listusertask= findViewById(R.id.discr_for_task);
        this.btnAddNew = findViewById(R.id.addnew);
        this.edtValue = findViewById(R.id.value);

        myRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        final FirebaseUser user = mAuth.getCurrentUser();
        Log.i("denLog", "onCreate");


       /* FirebaseListOptions<String> options = new FirebaseListOptions.Builder<String>()
                .setQuery(myRef.child(user.getUid())
                        .child("Tasks"), String.class).setLayout(android.R.layout.simple_list_item_1)
                .build();
        ListAdapter adapter =new FirebaseListAdapter<String>(options) {
            @Override
            protected void populateView(View v, String model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model);

            }

            @Override
            public void onError(@NonNull DatabaseError error) {
                super.onError(error);
                Log.i("denLog", error.getMessage());
                Log.i("denLog", error.getDetails());
            }
        };
        listusertask.setAdapter(adapter);*/


        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> t = new GenericTypeIndicator<Map<String, String>>(){  };
                //List<String> list= dataSnapshot.child("Tasks").getValue(t);
                DiscrTasks.clear();
                Map<String, String> valu= dataSnapshot.child("Tasks").getValue(t);
                for (Map.Entry item :
                        valu.entrySet()) {
                    DiscrTasks.add(String.valueOf(item.getValue()));
                }
               // DiscrTasks = dataSnapshot.child("Tasks").getValue(t);

                updateUi();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("denLog", databaseError.getDetails());
                Log.i("denLog", databaseError.getMessage());
            }
        });
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtValue.getText().toString().isEmpty())
                    myRef.child(user.getUid()).child("Tasks").push().setValue(edtValue.getText().toString());
            }
        });
    }

    private void updateUi() {
        ArrayAdapter adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_expandable_list_item_1, DiscrTasks);
        listusertask.setAdapter(adapter);
    }
}
