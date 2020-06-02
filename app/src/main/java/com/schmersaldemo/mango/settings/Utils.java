package com.schmersaldemo.mango.settings;

import android.content.Context;
import android.content.Intent;
import com.schmersaldemo.MangoApplication;
import com.schmersaldemo.mango.view.AddLoginAccount;
import com.schmersaldemo.mango.view.Login;
import java.util.Arrays;
import java.util.List;

/***
 Author: Puneet Bahuguna
 ***/
public class Utils {

    //Method for setting the effects of new selected language
    public static boolean setNewLocale(Context context,String language, boolean restartProcess) {
        MangoApplication.localeManager.setNewLocale(context, language);
        if(context instanceof AddLoginAccount){
            context.startActivity(new Intent(context, AddLoginAccount.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }else{
            context.startActivity(new Intent(context, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        if (restartProcess) {
            System.exit(0);
        }
        return true;
    }

    public static List<String> getLanguageCode(){
        return Arrays.asList("ES-EN","PT-BR");
    }
}
