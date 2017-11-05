package i.am.eipeks.projectcrypto._view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import i.am.eipeks.projectcrypto.R;
import i.am.eipeks.projectcrypto._model.Converter;
import i.am.eipeks.projectcrypto._model.Country;
import i.am.eipeks.projectcrypto._model.Crypto;
import i.am.eipeks.projectcrypto._model.RetrofitClient;

public class Home extends AppCompatActivity implements
        View.OnClickListener,
        DialogInterface.OnClickListener{

    @BindView(R.id.add_new_card)
    private FloatingActionButton addCard;

    private Converter converter;

    private View view;
    private Spinner cryptoSpinner, countrySpinner;

    private AlertDialog dialog;

    private Crypto[] cryptoCurrencies = {new Crypto("Bitcoin", "BTC"),
            new Crypto("Ethereum", "ETH")};

    private Country[] countries = {
            new Country("Nigeria", "NGN"),
            new Country("United States", "USD"),
            new Country("Argentina", "ARS"),
            new Country("South Africa", "ZAR"),
            new Country("Algeria", "DZD"),
            new Country("Brazil", "BRL"),
            new Country("United Kingdom", "GBP"),
            new Country("China", "CNY"),
            new Country("Cameroon", "XAF"),
            new Country("Columbia", "COP"),
            new Country("Denmark", "DKK"),
            new Country("Ethiopia", "ETB"),
            new Country("France", "EUR"),
            new Country("India", "INR"),
            new Country("Israel", "ILS"),
            new Country("Japan", "JPY"),
            new Country("Malaysia", "MYR"),
            new Country("Mexico", "MXN"),
            new Country("Qatar", "QAR"),
            new Country("Saudi Arabia", "SAR"),
    };

    private ArrayAdapter<Crypto> cryptoAdapter;
    private ArrayAdapter<Country> countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeAdapters();

        converter = RetrofitClient.getConverterClient();

        addCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_new_card:
                dialog = new AlertDialog.Builder(this)
                        .setView(view)
                        .setTitle(String.format(Locale.ENGLISH, "%s", "New card"))
                        .setPositiveButton("Add Card", this)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                break;
            case AlertDialog.BUTTON_POSITIVE:

                break;
        }
    }

    @SuppressLint("InflateParams")
    private void initializeAdapters(){
        view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        cryptoSpinner = view.findViewById(R.id.crypto_spinner);
        countrySpinner = view.findViewById(R.id.country_spinner);

        cryptoAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, cryptoCurrencies);
        countryAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, countries);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Button positiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(this);
    }
}