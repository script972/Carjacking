package com.script972.carjacking.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.script972.carjacking.R;
import com.script972.carjacking.callbacks.RoadCallbacks;
import com.script972.carjacking.model.Road;
import com.script972.carjacking.repository.RoadRepository;
import com.script972.carjacking.repository.impl.RoadRepositoryImpl;
import com.script972.carjacking.ui.adapter.ContentAdapter;

import java.util.List;

public class CardContentFragment extends Fragment {
    private RecyclerView recyclerView;
    private  ContentAdapter adapter;
    private RoadRepository roadRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        roadRepository = new RoadRepositoryImpl();
        roadRepository.subscibeToAllRoad(roadCallbacks);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    //Callbacks
    private final RoadCallbacks roadCallbacks = new RoadCallbacks() {
        @Override
        public void dataChanges(List<Road> road) {
            if(adapter==null){
                adapter = new ContentAdapter(getContext(), getChildFragmentManager(), road);
                recyclerView.setAdapter(adapter);
            } else {
                adapter.setRoads(road);
                adapter.notifyDataSetChanged();
            }
        }
    };
}