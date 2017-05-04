package com.qht.blog2.OtherFragment.send.sendNetSite.UI;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qht.blog2.BaseBean.CityPickerBean;
import com.qht.blog2.BaseBean.OrderNetSiteBean;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.OtherFragment.send.sendNetSite.adapter.Send_Nearby_Adapter;
import com.qht.blog2.OtherFragment.send.sendNetSite.data.City2Url;
import com.qht.blog2.R;
import com.qht.blog2.Util.AssetUtil;
import com.qht.blog2.Util.CompanyUtil;
import com.qht.blog2.Util.DialogUtil;
import com.qht.blog2.Util.GsonUtils;
import com.qht.blog2.Util.PhoneUtil;
import com.qht.blog2.Util.SystemUtil;
import com.qht.blog2.Util.UrlUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentSend_NearBy extends BaseFragment {

    @BindView(R.id.rv_fragment_send)
    RecyclerView rvFragmentSend;
    @BindView(R.id.ll_fragment_send_rv)
    LinearLayout llFragmentSendRv;
    @BindView(R.id.tv_fragment_send_start)
    EditText     tvFragmentSendStart;
    @BindView(R.id.tv_fragment_send_stop)
    EditText     tvFragmentSendStop;


    private  Handler handler = new Handler();
    private Send_Nearby_Adapter madapter;
    private Context             mActivity;
    private HashMap<String,String> datemap=new HashMap<String, String>();
    private List<OrderNetSiteBean>                  list     = new ArrayList<OrderNetSiteBean>();
    private List<CityPickerBean>               options1Items = new ArrayList<>();
    private List<ArrayList<String>>            options2Items = new ArrayList<>();
    private String pageurl;
    private String fromID,ToID;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_send__nearby;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        pageurl=UrlUtil.pageUrl;
        rvFragmentSend.setLayoutManager(new LinearLayoutManager(mActivity));
        madapter = new Send_Nearby_Adapter(list, mActivity);
        rvFragmentSend.setAdapter(madapter);
        madapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderNetSiteBean bean=(OrderNetSiteBean)adapter.getItem(position);
                String[] phones=bean.getPhone().substring(5).split("、|,|，|；|;");
                if(checkPermiss()){
                    Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                    phoneIntent.setData(Uri.parse("tel:"+phones[0]));
                    startActivity(phoneIntent);
                };
            }
        });
        queryData();
    }

    private boolean checkPermiss() {
        //针对即使获取了拨打电话的权限依然报错问题的解决方案

        // 检查是否获得了权限（Android6.0运行时权限）
        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
        // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.CALL_PHONE)) {
        // 返回值：
        //如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
        //如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
        //如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
        // 弹窗需要解释为何需要该权限，再次请求授权
                Toast.makeText(mActivity, "请授权！", Toast.LENGTH_LONG).show();
        // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", SystemUtil.getAppPackageName(mActivity), null);
                intent.setData(uri);
                startActivity(intent);
            }else{
        // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},1);
            }
        }else {
        // 已经获得授权，可以打电话
            return true;
        }
        return false;
    }

    private void queryData() {
            getProvince();
        if (list.size() <= 0) {
            getElementValue(UrlUtil.baseUrl+pageurl);
        }
    }

    private void getProvince() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 写子线程中的操作,解析省市区数据
                initJsonData();
            }
        }).start();
    }


    private void getElementValue(final String url) {
        DialogUtil.showProgressDialog(mActivity);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document = null;
                try {
                    document = Jsoup.connect(url)//连接url
                            .execute()
                            .parse();
                    Elements els = document.getElementsByClass("express-msg clearfix");
                    for (Element e : els) {
                        OrderNetSiteBean sitebean = new OrderNetSiteBean();
                        sitebean.setTitle(e.child(0).child(0).text());
                        sitebean.setBrand(e.child(0).child(1).text());
                        sitebean.setArea(e.child(0).child(2).text());
                        sitebean.setSite(e.child(0).child(3).text());
                        sitebean.setPhone(e.child(0).child(4).text());
                        sitebean.setTime(e.child(0).child(5).text());
                        list.add(sitebean);
                    }
                    final Document finalDocument = document;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Element pageel = finalDocument.getElementsByClass("next-page").first();
                            if (null != pageel) {
                                //切换夜间模式后pageUrl为最后一个没有数据的url，所以需要重置
                                pageurl = pageel.getElementsByTag("a").attr("href");
                                getElementValue(UrlUtil.baseUrl+pageurl);//读取下一页
                            } else {
                                DialogUtil.hideProgressDialog();
                                madapter.notifyDataSetChanged();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void ShowPickerView(final int flag) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2);
                if(flag==0){// 0为点击开始箭头
                    //转换为响应代号          类如:  http://www.kuaidihelp.com/wangdian-1899--xq-cy--/
                    fromID=String.valueOf(City2Url.getCityId(tx));
                    pageurl="/wangdian-"+fromID+"--xq-cy--/";
                    tvFragmentSendStart.setText(tx);
                    list.clear();
                    getElementValue(UrlUtil.baseUrl+pageurl);
                }else{// 1为点击结束箭头
                    ToID=String.valueOf(City2Url.getCityId(tx));
                    tvFragmentSendStop.setText(tx);
                    getSxValue(fromID,ToID);
                }

            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
         pvOptions.setPicker(options1Items, options2Items);//二级选择器
         pvOptions.show();
    }

    private void getSxValue(String fromID, String toID) {
        final String sxUrl=UrlUtil.SxQueryUrl+"&fromid="+fromID+"&toid="+toID;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document = null;
                try {
                    document = Jsoup.connect(sxUrl)//连接url
                            .execute()
                            .parse();
                    Elements els = document.getElementsByClass("clearfix liEntry");

                    for (Element e : els) {
                        String  c=e.getElementsByClass("fl-left pp-logo").first().getElementsByTag("i").text();
                        String  d=e.getElementsByClass("date").text();
                        datemap.put(c,d);
                    }
                    //往 list 中加入时效字段
                    for (OrderNetSiteBean bean:list) {
                        String sx= CompanyUtil.CaseSx(bean.getBrand().substring(6),datemap);
                            bean.setAtime(sx);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                                madapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new AssetUtil().getJson(mActivity,"province.json");//获取assets目录下的json文件数据

        List<CityPickerBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
        }
        }

    public List<CityPickerBean> parseData(String result) {//Gson 解析
//        List<CityPickerBean> detail = new ArrayList<>();
        List<CityPickerBean>  detail=GsonUtils.jsonToList(result,CityPickerBean.class);
        return detail;
    }

    @OnClick({R.id.tv_fragment_send_startarrow, R.id.tv_fragment_send_stoparrow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fragment_send_startarrow:
                PhoneUtil.hideInputWindow(mActivity,view);
                ShowPickerView(0);
                break;
            case R.id.tv_fragment_send_stoparrow:
                ShowPickerView(1);
                break;
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //切换夜间模式的时候recreate MainAcivity销毁重建,dialog 所持有的 Activity对象已经不是上一个 mActivity对象。所以dialog也需要销毁重建
        DialogUtil.showProgressDialog=null;
    }
}
