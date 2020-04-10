package com.example.AndroidProject.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    //로그인정보가 담길 SharedPreference의 키값 (userInfo)
    static final String PREF_LOGIN_INFO = "userInfo";

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //로그인정보담기
    public static void setUserInfo(Context context, String userInfo) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_LOGIN_INFO, userInfo);
        editor.commit();
    }

    //로그인정보가져오기
    public static String getUserInfo(Context context) {
        return getSharedPreferences(context).getString(PREF_LOGIN_INFO, "");
    }

    //로그아웃
    public static void clearUserInfio(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }


}
