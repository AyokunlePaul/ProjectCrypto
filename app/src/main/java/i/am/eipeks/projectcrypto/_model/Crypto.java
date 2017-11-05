package i.am.eipeks.projectcrypto._model;


public class Crypto {
    private String name, code;

    public Crypto(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
