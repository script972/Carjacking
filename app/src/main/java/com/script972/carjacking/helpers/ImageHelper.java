package com.script972.carjacking.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class ImageHelper {

    public static BitmapDescriptor bitmapSizeByScall(Context context, int bitm, float scallZeroTo){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bitm);
        Bitmap bitmapOut = Bitmap.createScaledBitmap(bitmap,
                Math.round(bitmap.getWidth() * scallZeroTo),
                Math.round(bitmap.getHeight() * scallZeroTo), false);
        return BitmapDescriptorFactory.fromBitmap(bitmapOut);
    }

}
