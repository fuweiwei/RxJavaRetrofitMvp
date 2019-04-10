package com.veer.rx.ui.book;


import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.veer.rx.R;
import com.veer.rx.base.BaseActivity;
import com.veer.rx.common.ActivityContracts;
import com.veer.rx.model.BookModel;
import com.veer.rx.widget.FrameLayout4Loading;

import butterknife.BindView;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */
@Route(path= ActivityContracts.ACTIVITY_BOOK)
public class BookActivity extends BaseActivity<BookPresenter> implements BookContract.View{
    @BindView(R.id.tv_book)
    TextView mTvBook;
    @BindView(R.id.loading)
    FrameLayout4Loading mFrameLayout4Loading;

    @Override
    public void setBook(BookModel model) {
        mTvBook.setText(model.getBooks().get(0).getCatalog());
    }

    @Override
    protected void initView() {
        mFrameLayout4Loading.setRefreashClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getBook(mFrameLayout4Loading,"三国演义","","0","1");
            }
        });
        mPresenter.getBook(mFrameLayout4Loading,"三国演义","","0","1");
    }

    @Override
    protected  BookPresenter initPresenter() {
        return new BookPresenter(this);
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_book;
    }

}