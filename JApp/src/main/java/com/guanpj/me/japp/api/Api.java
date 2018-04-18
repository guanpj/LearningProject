package com.guanpj.me.japp.api;

import com.guanpj.me.japp.module.BaseModel;
import com.guanpj.me.japp.module.Benefit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Jie on 2017/3/21.
 */

public interface Api {
    @GET("api/data/福利/{pageCount}/{pageIndex}")
    Call<BaseModel<ArrayList<Benefit>>> defaultBenefits(
            @Path("pageCount") int pageCount,
            @Path("pageIndex") int pageIndex
    );

    @GET("api/data/福利/{pageCount}/{pageIndex}")
    Observable<BaseModel<ArrayList<Benefit>>> rxBenefits(
            @Path("pageCount") int pageCount,
            @Path("pageIndex") int pageIndex
    );
}
