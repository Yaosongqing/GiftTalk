package com.test.yxj.gifttalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.yxj.gifttalk.R;
import com.test.yxj.gifttalk.bean.GuideGiftBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GuideOthersListAdapter extends BaseAdapter {

    private List<GuideGiftBean.DataBean.ItemsBean> datas;
    private Context context;
    private LayoutInflater layoutInflater;

    public GuideOthersListAdapter(Context context,List<GuideGiftBean.DataBean.ItemsBean> datas) {
        this.context = context;
        this.datas = datas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null){
            view = layoutInflater.inflate(R.layout.main_selected_child_item_view,parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        GuideGiftBean.DataBean.ItemsBean itemsBean = datas.get(position);
        String cover_image_url = itemsBean.getCover_image_url();
        String title = itemsBean.getTitle();
        Picasso.with(context).load(cover_image_url).into(viewHolder.imageIV);
        viewHolder.titleTxt.setText(title);
        return view;

    }

    class ViewHolder {
        @BindView(R.id.guide_selected_list_iv)
        ImageView imageIV;
        @BindView(R.id.guide_selected_list_title_txt)
        TextView titleTxt;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
