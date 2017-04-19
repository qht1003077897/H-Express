package com.qht.blog2.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferenceUtil {

	public static SharedPreferences sp;
	public static Context context;
	/*
      *在Application中进行初始化，设置context
      */
	public void initSharePreferenceUtil(Context context){
		this.context=context;
	}


	public static void LoginSuccess(){
		setBooleanSP("ISLogin",true);
	}

	public static boolean isLogin(){
		if(null==sp){
			sp=getSP();
		}
		return sp.getBoolean("isLogin", false);
	}
	
	public static SharedPreferences getSP(){
		return context.getSharedPreferences(ConstantUtil.APPLICATION_NAME, Context.MODE_PRIVATE);
	}
	
	public static void setStringSP(String key,String value){
		if(null==sp){
			sp=getSP();
		}
		Editor editor=sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public static void setStringsSP(String[] key,String[] value){
		Editor editor=sp.edit();
		for(int i=0;i<key.length;i++){
			editor.putString(key[i], value[i]);
		}
		editor.commit();
	}

	public static void setBooleanSP(String key,boolean value){
		Editor editor=sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static void removeStringSP(String key){
		Editor editor=sp.edit();
		editor.remove(key);
		editor.commit();
	}


	public static void removeStringsSP(String[] key){
		Editor editor=sp.edit();
		for(int i=0;i<key.length;i++){
			editor.remove(key[i]);
		}
		editor.commit();
	}

	public static void removeBooleanSP(String key){
		Editor editor=sp.edit();
		editor.remove(key);
		editor.commit();
	}
}
