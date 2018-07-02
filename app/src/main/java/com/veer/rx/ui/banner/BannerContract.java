package com.veer.rx.ui.banner;

import com.veer.rx.base.BaseContract;
import com.veer.rx.model.OtherProductModel;

import java.util.List;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public interface BannerContract {
    interface View extends BaseContract.BaseView{
        void setBanner(List<OtherProductModel> list);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getBanner(String id);
    }
}
