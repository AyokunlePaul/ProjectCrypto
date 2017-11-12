package i.am.eipeks.projectcrypto._model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName(value = "NGN",
            alternate = {"USD", "ARS", "ZAR", "DZD", "BRL", "GBP", "CNY", "XAF", "COP", "DKK", "ETB", "EUR",
            "INR", "ILS", "JPY", "MYR", "MXN", "QAR", "SAR"})
    @Expose
    private String value;

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
