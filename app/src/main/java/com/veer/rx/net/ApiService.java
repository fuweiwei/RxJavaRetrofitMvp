package com.veer.rx.net;


import com.veer.rx.base.BaseResult;
import com.veer.rx.model.OtherProductModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 请求数据接口
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public interface ApiService {

    /**
     * 广告banner
     * @return
     */
    @GET("/ace-app/bannerInfo/{id}")
    Observable<BaseResult<List<OtherProductModel>>> getBannerInfo(@Path("id") String id);
}
