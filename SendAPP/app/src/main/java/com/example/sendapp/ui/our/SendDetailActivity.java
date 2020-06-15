package com.example.sendapp.ui.our;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sendapp.R;
import com.example.sendapp.ui.entity.send.SendDetail;
import com.example.sendapp.ui.tablefixheaders.TableFixHeaders;
import com.example.sendapp.ui.tablefixheaders.adapters.BaseTableAdapter;
import com.example.sendapp.ui.util.QRCodeUtil;

import java.util.ArrayList;
import java.util.List;


public class SendDetailActivity extends AppCompatActivity {
    private List<SendDetail> list ;
    private SendDetailAdapter mTableAdapter;
    private String m_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_detail);

        Intent it = getIntent();
        m_id = it.getStringExtra("id");
        System.out.println(m_id);
        ImageView mImageView = (ImageView) findViewById(R.id.qr_code);
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(m_id, 500, 500);
        mImageView.setImageBitmap(mBitmap);
        //表格
        TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        mTableAdapter = new SendDetailAdapter(this);
        tableFixHeaders.setAdapter(mTableAdapter);
        list=new ArrayList<SendDetail>();
        list.clear();
        getList("");
        refreshTable();
    }


    /**
     * 获取待确认清单
     */
    public void getList(String  mId) {
        // Do something in response to button
        //todo  获取不同状态的送货清单
        SendDetail sendDetail = new SendDetail();
        sendDetail.setCav_no("3-1");
        sendDetail.setMaterial_id("28u49345");
        sendDetail.setMaterial_name("测试");
        sendDetail.setQty(204);
        sendDetail.setSendId(mId);
        sendDetail.setSite("F01");
        sendDetail.setTrace_no("20200613");
        list.add(sendDetail);
    }

    private void refreshTable() {
        //根据ID获取送货明细
        getList(m_id);
    }

    public class SendDetailAdapter extends BaseTableAdapter {
        private final Context context;
        private final LayoutInflater inflater;
        private final int width;
        private final int height;

        public SendDetailAdapter(Context context) {
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
            return 5;
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
            //批次、数量、型腔号
            if (row==-1 && column==0) {
                return setHeaderTitle(converView, "产品名称");
            } else if (row==-1 && column==1) {
                return setHeaderTitle(converView, "型腔号");
            } else if (row==-1 && column==2) {
                return setHeaderTitle(converView, "库位");
            } else if (row==-1 && column==3) {
                return setHeaderTitle(converView, "批次号");
            } else if (row==-1 && column==4) {
                return setHeaderTitle(converView, "数量");
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
            }
            return width;
        }
        private String getCellString(int row, int column) {
            if (column==0) {
                return list.get(row).getMaterial_name();
            } else if (column==1) {
                return list.get(row).getCav_no();
            } else if (column==2) {
                return list.get(row).getSite();
            } else if (column==3) {
                return list.get(row).getTrace_no();
            } else if (column==4) {
                return list.get(row).getQty()+"";
            }
            return "Lorem (" + row + ", " + column + ")";
        }
        private void setText(View view, String text) {
            ((TextView) view.findViewById(android.R.id.text1)).setText(text);
        }
    }

}
