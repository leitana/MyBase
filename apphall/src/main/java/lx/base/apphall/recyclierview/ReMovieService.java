package lx.base.apphall.recyclierview;

import lx.base.apphall.beans.JockerResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建时间 2016/10/14
 * Created by linxiao.
 * http://japi.juhe.cn/joke/content/text.from
 */

public interface ReMovieService {
    @GET("text.from")
//    @Headers("key" + "6ca96a33f79dd59f6e66cfd507c6352b")
    Observable<JockerResponse> getJock(@Query("key") String key, @Query("page") int page, @Query("pagesize") int nowPage);
}
