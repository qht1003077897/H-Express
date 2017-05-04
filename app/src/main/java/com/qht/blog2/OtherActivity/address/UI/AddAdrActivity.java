package com.qht.blog2.OtherActivity.address.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bigkoo.pickerview.OptionsPickerView;
import com.qht.blog2.BaseActivity.ToolBarActivity;
import com.qht.blog2.BaseBean.AddressBean;
import com.qht.blog2.BaseBean.CityPickerBean;
import com.qht.blog2.R;
import com.qht.blog2.Util.AssetUtil;
import com.qht.blog2.Util.GsonUtils;
import com.qht.blog2.Util.PhoneUtil;
import com.qht.blog2.Util.TextUtil;
import com.qht.blog2.Util.ToastUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAdrActivity extends ToolBarActivity {

    @BindView(R.id.et_activity_add_address_contacts)
    EditText etActivityAddAddressContacts;
    @BindView(R.id.et_activity_add_address_phone)
    EditText etActivityAddAddressPhone;
    @BindView(R.id.et_activity_add_address_city)
    EditText etActivityAddAddressCity;
    @BindView(R.id.et_activity_add_address_area)
    EditText etActivityAddAddressArea;
    @BindView(R.id.et_activity_add_address_postcode)
    EditText etActivityAddAddressPostcode;
    @BindView(R.id.btn_activity_add_address_save)
    Button   btnActivityAddAddressSave;

    private List<CityPickerBean>               options1Items = new ArrayList<>();
    private List<ArrayList<String>>            options2Items = new ArrayList<>();
    private List<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("添加地址");
        initView();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_add_address;
    }


    private void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 写子线程中的操作,解析省市区数据
                initJsonData();
            }
        }).start();
    }

    @OnClick(R.id.et_activity_add_address_city)
    public void onClickcity() {
        PhoneUtil.hideInputWindow(AddAdrActivity.this,etActivityAddAddressCity);
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddAdrActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                etActivityAddAddressCity.setText(tx);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new AssetUtil().getJson(AddAdrActivity.this, "province.json");//获取assets目录下的json文件数据

        List<CityPickerBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public List<CityPickerBean> parseData(String result) {//Gson 解析
//        List<CityPickerBean> detail = new ArrayList<>();
        List<CityPickerBean> detail = GsonUtils.jsonToList(result, CityPickerBean.class);
        return detail;
    }

    @OnClick(R.id.btn_activity_add_address_save)
    public void onClicksave() {
        if(TextUtil.isEmpty(etActivityAddAddressContacts.getText().toString()) ||
           TextUtil.isEmpty(etActivityAddAddressPhone.getText().toString()) ||
           TextUtil.isEmpty(etActivityAddAddressCity.getText().toString()) ||
           TextUtil.isEmpty(etActivityAddAddressArea.getText().toString()) ||
           TextUtil.isEmpty(etActivityAddAddressPostcode.getText().toString()))
        {
            ToastUtil.showToastShort("请填写完整信息");
        }else{
            List<AddressBean> List = DataSupport
                    .where("address = ? ", etActivityAddAddressArea.getText().toString()).find(AddressBean.class);
            if(List.size()>0){
                ToastUtil.showToastShort("已存在相同地址");
                return;
            }
            AddressBean bean=new AddressBean();
            bean.setName(etActivityAddAddressContacts.getText().toString());
            bean.setPhone(etActivityAddAddressPhone.getText().toString());
            bean.setCity(etActivityAddAddressCity.getText().toString());
            bean.setAddress(etActivityAddAddressArea.getText().toString());
            bean.setPostcode(etActivityAddAddressPostcode.getText().toString());
            bean.save();
            ToastUtil.showToastShort("保存成功");
            finish();
        }
    }
}
