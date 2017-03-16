package lx.base.apphall.weixin.model;

import lx.base.apphall.weixin.beans.WeixinBeans;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建时间 2017/2/7
 * Created by linxiao.
 */

public interface ReWeiService {
    @GET("weixin/query")
    Observable<WeixinBeans> getWei(@Query("key") String key,
                                   @Query("pno") int page,//当前页数
                                   @Query("ps") int pageSize,//每页条数，最大50，默认20
                                   @Query("dtype") String type);
}
