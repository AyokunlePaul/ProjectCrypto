package i.am.eipeks.projectcrypto._model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName(value = "NGN",
            alternate = {"USD", "ARS", "ZAR", "DZD", "BRL", "GBP", "CNY", "XAF", "COP", "DKK", "ETB", "EUR",
            "INR", "ILS", "JPY", "MYR", "MXN", "QAR", "SAR"})
    @Expose
    private String countryCode;

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

    public String getCountryCode(){
        return this.countryCode;
    }
}
