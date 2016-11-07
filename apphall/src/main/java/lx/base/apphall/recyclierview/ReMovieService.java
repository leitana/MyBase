package lx.base.apphall.recyclierview;

import lx.base.apphall.beans.JockerResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建时间 2016/10/14
 * Created by linxiao.
 */

public interface ReMovieService {
    @GET("joke_text")
    @Headers("apikey:" + "529cad5d92f049c0165be5fdfbf210b8")
    Observable<JockerResponse> getJock(@Query("page") int page);
}
