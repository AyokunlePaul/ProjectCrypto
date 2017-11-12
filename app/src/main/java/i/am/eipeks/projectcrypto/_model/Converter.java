package i.am.eipeks.projectcrypto._model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Converter {

    @GET("?fsym=BTC&tsyms={currency}")
    Call<CountryResponse> convertBTC(@Path("currency") String currency);

    @GET("?fsym=ETH&tsyms={currency}")
    Call<CountryResponse> convertETH(@Path("currency") String currency);

}
