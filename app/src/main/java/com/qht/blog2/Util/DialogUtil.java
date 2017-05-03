package com.qht.blog2.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qht.blog2.R;
import com.qht.blog2.View.MyProgressDialog;

/**
 * Created by QHT on 2017-02-27.
 */
public class DialogUtil {


    public static MyProgressDialog showProgressDialog;
    /**
     *普通提示对话框
     */

    public static void showAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listenerSure,
                                       DialogInterface.OnClickListener listenerCancel) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", listenerSure);
        builder.setNegativeButton("取消", listenerCancel);
        builder.create().show();
    }


    /**
     *纯文本显示对话框
     */
    public static void showAlertDialogText(Context context, String title,String text) {
        LayoutInflater factory = LayoutInflater.from(context);//提示框
        final View view = factory.inflate(R.layout.activity_about_licensedialog, null);
        final TextView tv=(TextView)view.findViewById(R.id.tv_activity_about_licenses_dialog);
        tv.setText(text);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

    /**
     *可编辑对话框
     */
    public static void showAlertDialogEdit(Context context, String title,  DialogInterface.OnClickListener listenerSure,
                                       DialogInterface.OnClickListener listenerCancel) {
        LayoutInflater factory = LayoutInflater.from(context);//提示框
        final View view = factory.inflate(R.layout.editdialog_layout, null);//这里必须是final的
        final EditText edit=(EditText)view.findViewById(R.id.editdialog_editText);//获得输入框对象

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton("确定", listenerSure);
        builder.setNegativeButton("取消", listenerCancel);
        builder.create().show();
    }

    /**
     @param context
      无参默认点击外部不可取消
     */
    public static void showProgressDialog(Context context){
        if(null==context){
            return;
        }
        if(null==showProgressDialog){
            showProgressDialog =new MyProgressDialog(context);
        }
        if(!showProgressDialog.isShowing()){
            showProgressDialog.setCancelable(false);
            showProgressDialog.show();
        }
    }
    /**
     @param context
     @param isCancel 点击返回键是否取消
     */
    public static void showProgressDialog(Context context,boolean isCancel){
        if(null==context){
            return;
        }
        try {
            if(null==showProgressDialog){
                showProgressDialog =new MyProgressDialog(context);
            }
            if(!showProgressDialog.isShowing()){
                showProgressDialog.setCancelable(isCancel);
                showProgressDialog.show();
            }
        }catch (Exception e){
        }
    }

    public static void hideProgressDialog(){
        if(showProgressDialog!=null && showProgressDialog.isShowing()){
            showProgressDialog.dismiss();
        }
    }
}
