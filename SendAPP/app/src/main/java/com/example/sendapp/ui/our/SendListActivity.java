package com.example.sendapp.ui.our;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sendapp.R;
import com.example.sendapp.ui.entity.SendInfo;
import com.example.sendapp.ui.tablefixheaders.TableFixHeaders;
import com.example.sendapp.ui.tablefixheaders.adapters.BaseTableAdapter;
import com.example.sendapp.ui.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SendListActivity extends AppCompatActivity {
    private static final String[] statusSend={"待确认","已确认","已出厂","已送达"};
    private GridView m_gview; // 送货单信息主表
    private Spinner spinner;
    private String m_status;
    private ArrayAdapter<String> adapter;
    private List<SendInfo> list ;
    private SendAdapter mTableAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_list);
        spinner = (Spinner) findViewById(R.id.tv_status);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,statusSend);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        spinner.setVisibility(View.VISIBLE);

        //表格
        TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        mTableAdapter = new SendAdapter(this);
        tableFixHeaders.setAdapter(mTableAdapter);
        list=new ArrayList<SendInfo>();
        list.clear();
        getList("");
        refreshTable();


    }
    /**
     * 获取待确认清单
     */
    public void getList(String status) {
        // Do something in response to button
        //todo  获取不同状态的送货清单
        SendInfo info = new SendInfo();
        info.setSendNo("S000001");
        info.setBasketQty(7);
        info.setSumQty(300);
        info.setDriver("张三");
        info.setCarNo("浙B888888");
        info.setStartPlace("#1");
        info.setDestination("#2");
        list.add(info);
    }


    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            m_status = statusSend[arg2];
           getList(statusSend[arg2]);
            refreshTable();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    private void refreshTable() {
        if (StringUtils.isEmpty(m_status)) {
//            DialogUtils.showError(this,"请选择状态信息",null);
            return;
        }
        mTableAdapter.notifyDataSetChanged();
    }

    public class SendAdapter extends BaseTableAdapter {
        private final Context context;
        private final LayoutInflater inflater;
        private final int width;
        private final int height;

        public SendAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            Resources resources = context.getResources();

            width = resources.getDimensionPixelSize(R.dimen.table_width);
            height = resources.getDimensionPixelSize(R.dimen.table_height);
        }

        public Context getContext() {
            return context;
        }

        public LayoutInflater getInflater() {
            return inflater;
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public int getHeight(int row) {
            return height;
        }

        @Override
        public int getItemViewType(int row, int column) {
            if (row < 0) {
                return 0;
            } else if (row>=0) {
                return 1;
            } else {
                return 0;
            }
        }

        private int getLayoutResource(int row, int column) {
            final int layoutResource;
            switch (getItemViewType(row, column)) {
                case 0:
                    layoutResource = R.layout.item_table_ikd_title;
                    break;
                case 1:
                    layoutResource = R.layout.item_table_ikd_content;
                    break;
                default:
                    throw new RuntimeException("wtf?");
            }
            return layoutResource;
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public View getView(final int row, int column, View converView, ViewGroup parent) {
            if (converView == null) {
                converView = inflater.inflate(getLayoutResource(row, column), parent, false);
            }
            //批次、数量、型腔号、时间
            if (row==-1 && column==0) {
                return setHeaderTitle(converView, "送货单号");
            } else if (row==-1 && column==1) {
                return setHeaderTitle(converView, "车牌号");
            } else if (row==-1 && column==2) {
                return setHeaderTitle(converView, "出发地");
            } else if (row==-1 && column==3) {
                return setHeaderTitle(converView, "目的地");
            } else if (row==-1 && column==4) {
                return setHeaderTitle(converView, "装箱数");
            }
            else if (row==-1 && column==5) {
                return setHeaderTitle(converView, "司机");
            }
            else if (row==-1 && column==6) {
                return setHeaderTitle(converView, "送货总数");
            }
            converView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mTableAdapter.notifyDataSetChanged();
                }
            });

            setText(converView, getCellString(row, column));
            return converView;
        }

        private View setHeaderTitle(View converView, String text) {
            setText(converView, text);
            TextView tv = ((TextView) converView.findViewById(android.R.id.text1));
            TextPaint paint = tv.getPaint();
            paint.setFakeBoldText(true);
            return converView;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getWidth(int column) {
            if(column==-1) {
                return 0;
			} else if (column==0) {
                return 200;
            } else if (column==1) {
                return 200;
            } else if (column==2) {
                return 100;
            } else if (column==3) {
                return 100;
            } else if (column==4) {
                return 100;
            } else if (column==5) {
                return 100;
            } else if (column==6) {
                return 300;
            }
            return width;
        }
        private String getCellString(int row, int column) {
            if (column==0) {
                return list.get(row).getSendNo();
            } else if (column==1) {
                return list.get(row).getCarNo();
            } else if (column==2) {
                return list.get(row).getStartPlace();
            } else if (column==3) {
                return list.get(row).getDestination();
            } else if (column==4) {
                return list.get(row).getBasketQty()+"";
            } else if (column==5) {
                return list.get(row).getDriver();
            }else if (column==6) {
                return list.get(row).getSumQty()+"";
            }
            return "Lorem (" + row + ", " + column + ")";
        }
        private void setText(View view, String text) {
            ((TextView) view.findViewById(android.R.id.text1)).setText(text);
        }
    }

}
