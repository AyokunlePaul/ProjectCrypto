package i.am.eipeks.projectcrypto._model;


public class Country {

    private String countryName, countryCode;
    private double  ethValue, btcValue;

    public Country(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.ethValue = 0.0;
        this.btcValue = 0.0;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getEthValue() {
        return ethValue;
    }

    public double getBtcValue() {
        return btcValue;
    }

    public void setEthValue(double ethValue) {
        this.ethValue = ethValue;
    }

    public void setBtcValue(double btcValue) {
        this.btcValue = btcValue;
    }
}
