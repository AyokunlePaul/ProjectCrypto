package i.am.eipeks.projectcrypto._model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName(value = "NGN", alternate = {"USD", "ARS", "ZAR", "DZD", "BRL", "GBP", "CNY"})
    @Expose
    private String countryName;



}
