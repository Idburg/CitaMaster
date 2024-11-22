package com.example.citamaster;

import android.os.Bundle;
import android.os.Handler;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String textHora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Calendar fechaHoy = Calendar.getInstance();
        Locale spanish = new Locale("es", "ES");

        TextView fecha = findViewById(R.id.fecha);
        SimpleDateFormat fechaFormato = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' yyyy", spanish);
        String textFecha = "Hoy es: "+ fechaFormato.format(fechaHoy.getTime());
        fecha.setText(textFecha);

        TextView hora = findViewById(R.id.hora);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                textHora = "Hora: ";
                Calendar fechaActualizable = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", spanish);
                String currentTime = simpleDateFormat.format(fechaActualizable.getTime());
                textHora += currentTime;
                hora.setText(textHora);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

        CalendarView calendario = findViewById(R.id.calendario);
        TextView resultado = findViewById(R.id.resultado);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String fechaCalendario = dayOfMonth + "de " + (month+1) + "del "+ year;
            }
        });

    }
}