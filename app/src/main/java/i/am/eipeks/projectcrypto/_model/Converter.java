package i.am.eipeks.projectcrypto._model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Converter {

    @GET("?fsym={crypto}&tsyms={currency}")
    Call<CountryResponse> convert(@Path("crypto") String crypto, @Path("currency") String currency);

}
