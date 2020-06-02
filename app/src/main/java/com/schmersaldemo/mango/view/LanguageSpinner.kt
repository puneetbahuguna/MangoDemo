
package com.schmersaldemo.mango.view
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import com.schmersaldemo.mango.localization.LocaleManager
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.settings.Utils

/***
Author: Puneet Bahuguna
 ***/

class LanguageSpinner(context: Context, attrs: AttributeSet) : AppCompatSpinner(context, attrs) {

    init {
        initSpinner(context)
    }
       private fun initSpinner(context:Context){
        val langAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item,Utils.getLanguageCode())
        langAdapter.setDropDownViewResource(com.schmersaldemo.mango.R.layout.dropdown_item)
        adapter=langAdapter
        setSelection(Preferences.getselectedLang(context))
          onItemSelectedListener=object:OnItemSelectedListener{
              override fun onNothingSelected(parent: AdapterView<*>?) {
              }
              override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                  if(position!=Preferences.getselectedLang(context)){
                      Preferences.setselectedLang(context,position)
                      Preferences.setLangCode(context,selectedItem.toString())
                      if(selectedItem.toString().equals("ES-EN")){
                          Utils.setNewLocale(context, LocaleManager.LANGUAGE_ENGLISH,false)
                      }else{
                          Utils.setNewLocale(context, LocaleManager.LANGUAGE_PURTTEGESE,false)
                      }
                  }
              }
          }
    }
}

