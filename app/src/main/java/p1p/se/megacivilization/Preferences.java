package p1p.se.megacivilization;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class Preferences {
    public static final String PREFS_NAME = "MegaCivPrefs";
    public static final String OWNED_LIST = "Name of owned advancements";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    public Preferences(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
    }

    public void storeCredit(String color, int value){
        editor.putInt(color, value);
        editor.commit();
    }

    public int getCredit(String color){
        return settings.getInt(color, 0);
    }

    public void storeOwned(Set<String> owned){
        editor.putStringSet(OWNED_LIST,owned);
        editor.commit();
    }

    public Set<String> getOwned(){
        return settings.getStringSet(OWNED_LIST, new HashSet<String>());
    }

}

