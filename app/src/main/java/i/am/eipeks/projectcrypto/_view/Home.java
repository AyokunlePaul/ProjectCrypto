package i.am.eipeks.projectcrypto._view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import i.am.eipeks.projectcrypto.R;
import i.am.eipeks.projectcrypto._model.Converter;
import i.am.eipeks.projectcrypto._model.Country;
import i.am.eipeks.projectcrypto._model.CountryResponse;
import i.am.eipeks.projectcrypto._model.RetrofitClient;
import i.am.eipeks.projectcrypto._viewmodel.Model;
import i.am.eipeks.projectcrypto._adapter.RecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements
        View.OnClickListener,
        DialogInterface.OnClickListener, AdapterView.OnItemSelectedListener, RecyclerAdapter.CardViewClickListener {

    @BindView(R.id.add_new_card) FloatingActionButton addCard;
    @BindView(R.id.display_cards) RecyclerView recyclerView;
    private View view;
    private Spinner countrySpinner;

    private Model model;
    private Country currentCountry;
    private Converter converter;

    private List<Country> countries = new ArrayList<>();

    private Country[] country = {new Country("Nigeria", "NGN"),
            new Country("USA", "USD"),
            new Country("Great Britain", "PSD"),
            new Country("Argentina", "AXD")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        model = ViewModelProviders.of(this).get(Model.class);

        converter = RetrofitClient.getConverterClient();

        addCard.setOnClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new RecyclerAdapter(countries, this));
        recyclerView.addItemDecoration(new GridSpacing(2, dpToPx(10), true));
        ((RecyclerAdapter) recyclerView.getAdapter()).setOnCardViewClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_new_card:
                initializeAdapters();
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setView(view)
                        .setCancelable(false)
                        .setTitle(String.format(Locale.ENGLISH, "%s", "New card"))
                        .setPositiveButton("Add Card", this)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
                break;
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    private void initializeAdapters(){
        view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);

        countrySpinner = view.findViewById(R.id.country_spinner);
        countrySpinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, model.getCountries()));
        countrySpinner.setOnItemSelectedListener(this);
    }

    private void convert(){
        converter.convertBTC(currentCountry.getCountryCode()).enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(@NonNull Call<CountryResponse> call, @NonNull Response<CountryResponse> response) {
                if (response.isSuccessful()){
                    switch (response.code()){
                        case 200:
                            currentCountry.setBtcValue(Double.valueOf(response.body().getValue()));
                            convertETH();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CountryResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void convertETH(){
        converter.convertETH(currentCountry.getCountryCode()).enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(@NonNull Call<CountryResponse> call, @NonNull Response<CountryResponse> response) {
                if (response.isSuccessful()){
                    switch (response.code()){
                        case 200:
                            currentCountry.setEthValue(Double.valueOf(response.body().getValue()));
                            break;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CountryResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Button positiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.country_spinner:
                currentCountry = (Country) countrySpinner.getSelectedItem();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCardViewClicked(int position) {

    }

    private class GridSpacing extends RecyclerView.ItemDecoration{

        private int spanCount, spacing;
        private boolean includeEdge;

        GridSpacing(int spanCount, int spacing, boolean includeEdge){
            this.spanCount = spanCount;
            this.includeEdge = includeEdge;
            this.spacing = spacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge){
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    @SuppressWarnings("SameParameterValue")
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void makeToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}