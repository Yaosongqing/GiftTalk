package com.test.yxj.gifttalk.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.yxj.gifttalk.R;
import com.test.yxj.gifttalk.bean.GuideGiftBean;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GuideSelectedListAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<String> dateList;
    private Map<String,List<GuideGiftBean.DataBean.ItemsBean>> map;
    private LayoutInflater layoutInflater;

    public GuideSelectedListAdapter(Context context, List<String> dateList, Map<String, List<GuideGiftBean.DataBean.ItemsBean>> map) {
        this.context = context;
        this.dateList = dateList;
        this.map = map;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return dateList == null ? 0 : dateList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String date = dateList.get(groupPosition);
        List<GuideGiftBean.DataBean.ItemsBean> itemsBeanList = map.get(date);
        return itemsBeanList == null ? 0 : itemsBeanList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView dateTxt = (TextView) convertView;
        if (dateTxt == null){
            dateTxt = new TextView(context);
        }
        String date = dateList.get(groupPosition);
        dateTxt.setText(date);
        dateTxt.setGravity(Gravity.CENTER_VERTICAL);
        dateTxt.setPadding(10,10,10,0);
        return dateTxt;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null){
            view = layoutInflater.inflate(R.layout.main_selected_child_item_view,parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        String date = dateList.get(groupPosition);
        List<GuideGiftBean.DataBean.ItemsBean> itemsBeanList = map.get(date);
        GuideGiftBean.DataBean.ItemsBean itemsBean = itemsBeanList.get(childPosition);
        String cover_image_url = itemsBean.getCover_image_url();
        String title = itemsBean.getTitle();
        ImageView imageView = viewHolder.imageIV;
        Picasso.with(context).load(cover_image_url).into(imageView);
        viewHolder.titleTxt.setText(title);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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
