package com.qht.blog2.OtherActivity.slide_Left.qqlogin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.OtherActivity.slide_Left.data.QQLogin_PersonInfoEvent;
import com.qht.blog2.R;
import com.qht.blog2.Util.SharePreferenceUtil;
import com.qht.blog2.Util.ToastUtil;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by QHT on 2017-04-26.
 */
public class QQLogin {

    public Tencent mTencent;
    private String APP_ID = "1106047893";
    public String SCOPE = "all";
    public IUiListener loginListener;
    public IUiListener userInfoListener;
    public IUiListener shareListener;
    private Context context;

    public QQLogin(Context context){
        this.context=context;
        Login();
    }

    private  void Login() {
        //   http://blog.csdn.net/u013451048/article/details/52352810
        mTencent =  Tencent.createInstance(APP_ID, context);

        //创建QQ登录回调接口
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后回调该方法
                JSONObject jo = (JSONObject) o;
                ToastUtil.showToastShort("登录成功");
                Log.e("COMPLETE:", jo.toString());
                String openID;
                try {
                    openID = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    SharePreferenceUtil.setStringSP("access_token",accessToken);
                    SharePreferenceUtil.setStringSP("openid",openID);
                    SharePreferenceUtil.setStringSP("expires_in",expires);
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败后回调该方法
                ToastUtil.showToastShort("登录失败");
                Log.e("LoginError:", uiError.toString());
            }

            @Override
            public void onCancel() {
                //取消登录后回调该方法
                ToastUtil.showToastShort("取消登录");
            }
        };


        userInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if(o == null){
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) o;
                    Log.e("JO:",jo.toString());
                    int ret = jo.getInt("ret");
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");
                    String figureurl_qq_2 = jo.getString("figureurl_qq_2");
                    String city = jo.getString("city");
                    ToastUtil.showToastShort("你好，" + nickName);
                    SharePreferenceUtil.setStringSP("nickname",nickName);
                    SharePreferenceUtil.setStringSP("gender",gender);
                    SharePreferenceUtil.setStringSP("figureurl_qq_2",figureurl_qq_2);
                    SharePreferenceUtil.setStringSP("city",city);
                    // To MainActivity 发送登录获取的信息
                    EventBusUtil.postSync(new QQLogin_PersonInfoEvent(nickName,gender,city,figureurl_qq_2,this));
                } catch (Exception e) {
                }
            }
            @Override
            public void onError(UiError uiError) {
            }
            @Override
            public void onCancel() {
            }
        };
        //qq分享的回调接口
        shareListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //分享成功后回调
                ToastUtil.showToastShort("分享成功");
            }

            @Override
            public void onError(UiError uiError) {
                //分享失败后回调
            }

            @Override
            public void onCancel() {
                //取消分享后回调
            }
        };
    }

    public void loginout(){
        mTencent.logout(context);
    }

    public  void share(){
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, context.getResources().getString(R.string.share_title));
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,context.getResources().getString(R.string.share_summary));
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,context.getResources().getString(R.string.share_target_url));
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,context.getResources().getString(R.string.share_image_url));
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, context.getResources().getString(R.string.share_appname));
        mTencent.shareToQQ((Activity) context, params, shareListener);
    }
}
