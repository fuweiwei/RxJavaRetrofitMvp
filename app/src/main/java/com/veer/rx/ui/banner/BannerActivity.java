package com.veer.rx.ui.banner;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.veer.rx.R;
import com.veer.rx.base.BaseActivity;
import com.veer.rx.common.ActivityContracts;
import com.veer.rx.model.OtherProductModel;
import com.veer.rx.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */
@Route(path= ActivityContracts.ACTIVITY_BANNER)
public class BannerActivity extends BaseActivity<BannerPresenter> implements BannerContract.View{
    @BindView(R.id.listView)
    RecyclerView mRvBanner;

    private List<OtherProductModel> mList;
    private BannerAdapter mAdapter;
    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mRvBanner.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new BannerAdapter();
        mRvBanner.setAdapter(mAdapter);
        mPresenter.getBanner("2");
    }
    @Override
    protected BannerPresenter initPresenter() {
        return new BannerPresenter(this);
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_banner;
    }
    @Override
    public void setBanner(List<OtherProductModel> list) {
        mList = list;
        mAdapter.notifyDataSetChanged();
    }

    private class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder>{


        @Override
        public BannerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            BannerAdapter.ViewHolder holder = new BannerAdapter.ViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.listitem_other_product, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(BannerAdapter.ViewHolder holder, int position) {
            if(mList!=null&&mList.size()>0){
                final OtherProductModel model = mList.get(position);
                if(model!=null){
                    String url = model.imageUrl;
                    ImageLoader.getInstance().displayImage(mContext,url,holder.imageView);
                    holder.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            return null == mList ? 0 : mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.iv_pic);
            }
        }
    }
}