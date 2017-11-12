package i.am.eipeks.projectcrypto._viewmodel;


import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import i.am.eipeks.projectcrypto._model.Country;
import i.am.eipeks.projectcrypto._model.Crypto;


public class Model extends ViewModel {

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

    private Crypto[] cryptoCurrencies = {new Crypto("Bitcoin", "BTC"),
            new Crypto("Ethereum", "ETH")};

    private List<Country> countriesData;
    private List<Crypto> cryptoCurrenciesData;

    public Model() {
        countriesData = new ArrayList<>();
        cryptoCurrenciesData = new ArrayList<>();
    }

    public List<Country> getCountries(){
        if (countriesData.size() == 0){
            setCountriesData();
        }
        return countriesData;
    }

    public List<Crypto> getCryptoCurrencies(){
        if (cryptoCurrenciesData != null){
            return cryptoCurrenciesData;
        }
        setCryptoCurrenciesData();
        return cryptoCurrenciesData;
    }

    private void setCountriesData(){
        countriesData.addAll(Arrays.asList(countries));
        Collections.sort(countriesData, new Comparator<Country>() {
            @Override
            public int compare(Country first, Country second) {
                return first.getCountryName().compareToIgnoreCase(second.getCountryName());
            }
        });
    }

    private void setCryptoCurrenciesData(){
        cryptoCurrenciesData.addAll(Arrays.asList(cryptoCurrencies));
    }
}
