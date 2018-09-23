package com.script972.carjacking.model.factory;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.script972.carjacking.helpers.ConvertNumberHelper;
import com.script972.carjacking.model.Road;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadFactory {

    public static Road getRoadFromDataSnapshot(DataSnapshot input){
        Road output = new Road();
        output.setActual(ConvertNumberHelper.longToBollean(Long.valueOf(String.valueOf(input.child("actual").getValue()))));
        output.setGeneralPrice(Double.valueOf(String.valueOf(input.child("general_price").getValue())));
        output.setJustForFuel(ConvertNumberHelper.longToBollean(Long.valueOf(String.valueOf(input.child("just_for_fuel").getValue()))));
        output.setPriceWithEvery(Double.valueOf(String.valueOf(input.child("price_with_every").getValue())));
        output.setLatLngs(getRouteLatLng(input));
        return output;
    }

    private static List<LatLng> getRouteLatLng(DataSnapshot input){
        List<LatLng> resultList = new ArrayList<>();
        Iterator<DataSnapshot> geoIter = input.child("route").getChildren().iterator();
        while (geoIter.hasNext()){
            DataSnapshot result = geoIter.next();
            String value = String.valueOf(result.getValue());
            resultList.add(new LatLng(Double.valueOf(value.split(",")[0].trim()),
                    Double.valueOf(value.split(",")[1].trim())));
        }
        return resultList;
    }



}
