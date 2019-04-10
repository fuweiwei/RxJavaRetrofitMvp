package com.veer.rx.ui.banner;


import com.veer.rx.base.BasePresenter;
import com.veer.rx.model.BookModel;
import com.veer.rx.net.RetrofitHelper;
import com.veer.rx.net.RxSchedulers;
import com.veer.rx.net.VObserver;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class BannerPresenter extends BasePresenter<BannerContract.View> implements BannerContract.Presenter{

    public BannerPresenter(BannerContract.View view) {
        super(view);
    }

    @Override
    public void getBanner(String id) {
        RetrofitHelper.getInstance().getServer()
                .getBannerInfo(id)
                .compose(RxSchedulers.applySchedulers())
                .as(bindToLife())
                .subscribe(new VObserver<String>() {
                    @Override
                    protected void onSuccess(String s) {

                    }
                });
    }

}
