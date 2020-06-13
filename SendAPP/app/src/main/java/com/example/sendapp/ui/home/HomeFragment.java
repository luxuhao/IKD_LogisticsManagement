package com.example.sendapp.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.sendapp.R;
import com.example.sendapp.ui.our.SendListActivity;
import com.example.sendapp.ui.util.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements
        GridView.OnItemClickListener{

    private GridView m_gview; // 首页的Grid类
    private List<Map<String, Object>> m_dataList;//菜单列表
    private MyAdapter  m_gridAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        m_gview = (GridView)root.findViewById(R.id.device_grid_view);
        m_gview.setOnItemClickListener(this);
        m_gview.setSelector(new ColorDrawable(Color.TRANSPARENT));

        LoadMainGrid();

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv =  view.findViewById(R.id.text);
        String sCommand = tv.getText().toString();
        if(sCommand.equals("入库1")) {
            startActivity(new Intent(getActivity(), SendListActivity.class));
        }
    }

    // 初始化Grid ，并且填充内容
    private void LoadMainGrid() {
        m_dataList = new ArrayList<Map<String, Object>>();
        if(null != m_dataList){
            m_dataList.clear();
        }
        // todo 获取获取用户菜单列表
        for(int i=0;i<6;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text","入库"+i);
            map.put("image", R.drawable.item_pic);
            m_dataList.add(map);
        }
        m_gridAdapter = new MyAdapter();
        m_gridAdapter.setData(m_dataList);
        // 配置适配器
        m_gview.setAdapter(m_gridAdapter);
    }

    class MyAdapter extends BaseAdapter {
        List<Map<String, Object>> curMenuList;

        public void setData(List<Map<String, Object>> curMenuList){
            this.curMenuList = curMenuList;
        }
        @Override
        public int getCount() {
            return curMenuList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.main_grid_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Map<String, Object> mMap = curMenuList.get(i);
            holder.text.setText(mMap.get("text").toString());
            holder.image.setImageResource(Integer.valueOf(mMap.get("image").toString()));

            if(mMap.containsKey("image_url")){
                String image_url = mMap.get("image_url").toString();
                if(!image_url.isEmpty()) {
                    ImageUtils.getInstance().loadImage(image_url, holder.image);
                }
            }
            return convertView;
        }

        public class ViewHolder{
            public ImageView image;
            public TextView text;
            public ViewHolder(View mView) {
                image = (ImageView)mView.findViewById(R.id.image);
                text = (TextView)mView.findViewById(R.id.text);
            }
        }
    }

}