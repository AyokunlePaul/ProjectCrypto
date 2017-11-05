package i.am.eipeks.projectcrypto._model;


import i.am.eipeks.projectcrypto._utils.RetrofitUtils;

public class RetrofitClient {

    private Converter converter;
    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/price/";

    public static Converter getConverterClient(){
        return RetrofitUtils.getRetrofitClient(BASE_URL).create(Converter.class);
    }

}
