package i.am.eipeks.projectcrypto._utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import i.am.eipeks.projectcrypto.Constants;

public class SessionUtils {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SessionUtils(Context context){
        preferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void addCard(String cardJSON){
        editor.putString(Constants.ADD_CARD_JSON, cardJSON);
        editor.commit();
    }

    public static String getCards(){
        return preferences.getString(Constants.ADD_CARD_JSON, null);
    }

}
