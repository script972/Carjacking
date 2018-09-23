package com.script972.carjacking.repository.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.script972.carjacking.callbacks.RoadCallbacks;
import com.script972.carjacking.model.Road;
import com.script972.carjacking.model.factory.RoadFactory;
import com.script972.carjacking.repository.RoadRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadRepositoryImpl implements RoadRepository {

    private DatabaseReference mDatabase;

    public RoadRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void subscibeToAllRoad(final RoadCallbacks roadCallbacks) {
        final List<Road> roads = new ArrayList<>();
        mDatabase.child("company").child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roads.clear();
                //users list
                for (DataSnapshot rootObject : dataSnapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot1 : rootObject.child("roads").getChildren()) {
                        roads.add(RoadFactory.getRoadFromDataSnapshot(dataSnapshot1.child("road")));
                    }
                }
                roadCallbacks.dataChanges(roads);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("denLog", "pssss");
            }
        });

    }
}
