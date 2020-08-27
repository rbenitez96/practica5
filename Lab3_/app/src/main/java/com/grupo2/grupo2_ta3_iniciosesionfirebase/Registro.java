package com.grupo2.grupo2_ta3_iniciosesionfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registro extends AppCompatActivity {

    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Registros");
        leerRegistros();
    }

    public void leerRegistros() {
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    mostrarRegistroPorPantalla(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.toException());
            }
        });
    }

    public void mostrarRegistroPorPantalla(DataSnapshot snapshot){
        LinearLayout contTemp = (LinearLayout) findViewById(R.id.ContenedorTemp);
        LinearLayout contAxis = (LinearLayout) findViewById(R.id.ContenedorAxis);
        String tempVal = String.valueOf(snapshot.child("temp").getValue());
        String axisVal = String.valueOf(snapshot.child("axi").getValue());

        TextView temp = new TextView(getApplicationContext());
        temp.setText(tempVal+" °C");
        contTemp.addView(temp);

        TextView axis = new TextView(getApplicationContext());
        axis.setText(axisVal);
        contAxis.addView(axis);
    }
}