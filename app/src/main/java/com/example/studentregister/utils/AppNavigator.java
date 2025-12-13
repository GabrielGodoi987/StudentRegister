package com.example.studentregister.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AppNavigator {
    public static void goTo(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    public static void goToWithParams(Context context, Class<?> destination, String key, Long value) {
        if (value <= 0L) {
            Toast.makeText(context, "O id não pode ser um valor negativo ou igual a zero", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }
}
