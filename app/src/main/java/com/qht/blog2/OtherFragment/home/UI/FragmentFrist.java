package com.qht.blog2.OtherFragment.home.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.qht.blog2.BaseBean.OrderInfoBean;
import com.qht.blog2.BaseEventBus.EventBusUtil;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.Net.MyStringCallBack;
import com.qht.blog2.Net.Ok_Request;
import com.qht.blog2.OtherActivity.orderdetail.UI.OrderDetailActivity;
import com.qht.blog2.OtherActivity.orderdetail.data.OrderDetailEvent;
import com.qht.blog2.OtherFragment.home.data.OrderSave2Litepal;
import com.qht.blog2.R;
import com.qht.blog2.Util.CompanyUtil;
import com.qht.blog2.Util.ConstantUtil;
import com.qht.blog2.Util.DialogUtil;
import com.qht.blog2.Util.PhoneUtil;
import com.qht.blog2.Util.RegexUtil;
import com.qht.blog2.Util.TextUtil;
import com.qht.blog2.Util.ToastUtil;
import com.qht.blog2.Util.UrlUtil;
import com.qht.blog2.View.EmptyViewLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;
import okhttp3.Call;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFrist extends BaseFragment {

    @BindView(R.id.kuaidinum)
    EditText kuaidinum;
    @BindView(R.id.kuaidinuminput)
    TextInputLayout kuaidinuminput;
    @BindView(R.id.kuaidicompany)
    EditText kuaidicompany;
    @BindView(R.id.kuaidicompanyinput)
    TextInputLayout kuaidicompanyinput;
    @BindView(R.id.scan)
    ImageView scan;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.querybutton)
    Button querybutton;
    @BindView(R.id.content)
    RelativeLayout content;
    @BindView(R.id.emptyView)
    EmptyViewLayout emptyView;


    private Activity mActivity;
    /**
     * 设置根布局资源id
     *
     * @return
     */
    @Override
    public int getContentViewId() {
        return R.layout.fragment_first;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        emptyView.bindView(content);// 绑定要显示的View
        emptyView.buttonClick(this, "query");// 加载失败后点击的时候执行onload方法
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.scan, R.id.arrow, R.id.querybutton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan:
                Intent intent = new Intent(mActivity, CaptureActivity.class);
                startActivityForResult(intent, ConstantUtil.REQUEST_QR_CODE);
                break;
            case R.id.arrow:
                PhoneUtil.hideInputWindow(mActivity,view);
                initOptionsPickerView();
                break;
            case R.id.querybutton:
                query();
                break;
        }
    }

    private void initOptionsPickerView() {
        List<String> options1Items=new ArrayList<>();
        //条件选择器
        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String tx= CompanyUtil.getInstance().Companylist.get(options1).toString();
                kuaidicompany.setText(tx);
            }
        }).build();
        pvOptions.setPicker(CompanyUtil.getInstance().Companylist);
        pvOptions.show();
    }

    public void query() {
        if(TextUtil.isEmpty(kuaidinum.getText().toString()) || TextUtil.isEmpty(kuaidicompany.getText().toString())){
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("type", CompanyUtil.getInstance().Companymap.get(kuaidicompany.getText().toString()));//参数
        map.put("postid", kuaidinum.getText().toString());
        Ok_Request.getAsyncData(getActivity(), map, UrlUtil.GetKuaiDi, new MyStringCallBack() {
            /**
             * UI Thread
             */
            @Override
            public void onBefore(Request request, int id) {
                DialogUtil.showProgressDialog(getActivity(), true);
                emptyView.Loading();
            }

            @Override
            public void onAfter(int id) {
                DialogUtil.hideProgressDialog();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showToastLong(e.getMessage());
                emptyView.failure();
            }

            @Override
            public void onResponse(OrderInfoBean response, int id) {
                emptyView.succees();//加载成功
                if (response != null) {
                    EventBusUtil.postSticky(new OrderDetailEvent(response,mActivity));
                    OrderSave2Litepal.savequery(response);
                    gotoActivity(mActivity, OrderDetailActivity.class);
                }
            }
        });
    }

    /**
     * http://blog.csdn.net/ruancoder/article/details/53490500
     * 浅析Fragment中startActivityForResult()与getActivity().startActivityForResult()的异同
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** 二维码扫描 */
        if (resultCode == mActivity.RESULT_OK
                && requestCode == ConstantUtil.REQUEST_QR_CODE
                && data != null) {
            String result = data.getStringExtra("result");
            //正则匹配数字
            if(RegexUtil.matchNum(result)){
                kuaidinum.setText(result);
            }

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity=activity;
    }
}
