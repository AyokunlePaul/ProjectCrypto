package i.am.eipeks.projectcrypto._viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import i.am.eipeks.projectcrypto._model.Country;


public class Model extends ViewModel {

    private MutableLiveData<List<Country>> countryList;

    public MutableLiveData<List<Country>> getCountryList(){
        if (countryList == null){
            getMutableLiveData();
        }
        return countryList;
    }

    private void getMutableLiveData(){
//        countryList.setValue();
    }

}
