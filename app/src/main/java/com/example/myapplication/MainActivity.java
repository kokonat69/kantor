package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextText3;
    private TextView editTextText4;
    private Spinner spinner1;
    private Spinner spinner2;
    private Map<String, Double> kursyWalutowe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Znajdowanie Spinnerów o odpowiednich id
        spinner1 = findViewById(R.id.lista1);
        spinner2 = findViewById(R.id.lista2);
        editTextText3 = findViewById(R.id.editTextText3);
        editTextText4 = findViewById(R.id.editTextText4);
        Button button = findViewById(R.id.button);

        // Tworzenie adaptera ArrayAdapter dla pierwszego Spinnnera
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.list1, android.R.layout.simple_spinner_item);

        // Tworzenie adaptera ArrayAdapter dla drugiego Spinnnera
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.list2, android.R.layout.simple_spinner_item);

        // Określenie stylu rozwijanej listy dla obu adapterów
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Ustawienie adapterów dla odpowiednich Spinnerów
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        // Inicjalizacja kursów walutowych
        initKursyWalutowe();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void initKursyWalutowe() {
        // Inicjalizacja kursów walutowych
        kursyWalutowe = new HashMap<>();

        // Dodawanie kursów walutowych dla poszczególnych par PLN do innych walut
        kursyWalutowe.put("PLNPLN", 1.0); // Kurs PLN do PLN
        kursyWalutowe.put("PLNEUR", 0.23); // Kurs PLN do EUR
        kursyWalutowe.put("PLNUSD", 0.26); // Kurs PLN do USD
        kursyWalutowe.put("PLNJPY", 29.43); // Kurs PLN do JPY
        kursyWalutowe.put("PLNGBP", 0.19); // Kurs PLN do GBP
        kursyWalutowe.put("PLNCZK", 6.48); // Kurs PLN do CZK

        // Dodawanie przeliczeń z innych walut do PLN
        kursyWalutowe.put("EURPLN", 1 / kursyWalutowe.get("PLNEUR"));
        kursyWalutowe.put("USDPLN", 1 / kursyWalutowe.get("PLNUSD"));
        kursyWalutowe.put("JPYPLN", 1 / kursyWalutowe.get("PLNJPY"));
        kursyWalutowe.put("GBPPLN", 1 / kursyWalutowe.get("PLNGBP"));
        kursyWalutowe.put("CZKPLN", 1 / kursyWalutowe.get("PLNCZK"));

        // Dodawanie przeliczeń z innych walut do CZK
        kursyWalutowe.put("EURCZK", kursyWalutowe.get("PLNCZK") / kursyWalutowe.get("PLNEUR"));
        kursyWalutowe.put("USDCZK", kursyWalutowe.get("PLNCZK") / kursyWalutowe.get("PLNUSD"));
        kursyWalutowe.put("JPYCZK", kursyWalutowe.get("PLNCZK") / kursyWalutowe.get("PLNJPY"));
        kursyWalutowe.put("GBPCZK", kursyWalutowe.get("PLNCZK") / kursyWalutowe.get("PLNGBP"));

        kursyWalutowe.put("CZKEUR", 1 / kursyWalutowe.get("EURCZK"));
        kursyWalutowe.put("CZKUSD", 1 / kursyWalutowe.get("USDCZK"));
        kursyWalutowe.put("CZKJPY", 1 / kursyWalutowe.get("JPYCZK"));
        kursyWalutowe.put("CZKGBP", 1 / kursyWalutowe.get("GBPCZK"));
    }

    private void calculateResult() {
        // Pobieranie wartości wybranej pozycji z pierwszego Spinnera
        String selectedItemSpinner1 = spinner1.getSelectedItem().toString();

        // Pobieranie wartości wybranej pozycji z drugiego Spinnera
        String selectedItemSpinner2 = spinner2.getSelectedItem().toString();

        // Pobieranie wartości z pola tekstowego
        String valueText = editTextText3.getText().toString();

        // Sprawdzenie, czy pole tekstowe nie jest puste
        if (!valueText.isEmpty()) {
            // Parsowanie wartości z pola tekstowego na liczbę
            double value = Double.parseDouble(valueText);

            // Wykonywanie operacji na wybranych elementach
            double result = performOperation(selectedItemSpinner1 + selectedItemSpinner2, value);

            // Formatowanie wyniku za pomocą DecimalFormat
            DecimalFormat df = new DecimalFormat("#.##");
            String formattedResult = df.format(result);

            // Wyświetlanie wyniku w polu tekstowym
            editTextText4.setText(formattedResult + " " + selectedItemSpinner2);
        }
    }

    private double performOperation(String item, double value) {
        // Sprawdzenie, czy istnieje kurs walutowy dla danego połączenia walut
        if (kursyWalutowe.containsKey(item)) {
            // Pobranie kursu walutowego dla danego połączenia walut
            double kurs = kursyWalutowe.get(item);
            // Przeliczenie wartości
            return value * kurs;
        } else {
            // Domyślna operacja (jeśli nie ma odpowiedniego kursu walutowego)
            return value; // Zwracamy wartość bez przeliczeń
        }
    }
}
