package com.pfh.app_mvvm.network;

import com.pfh.app_mvvm.model.Repository;
import com.pfh.app_mvvm.model.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 可以把所有api都放到一个接口下，如ApiStores
 * 可以按照功能划分，多些几个接口，如GithubService，XxxService,
 */

public interface ApiService {

    @GET("users/{username}/repos")
    Observable<List<Repository>> publicRepositories(@Path("username") String username);

    @GET
    Observable<User> userFromUrl(@Url String userUrl);

    // 如果对返回结果做过统一处理，可以采用这种方式
    @GET("users/{username}/repos")
    Observable<HttpResult<List<Repository>>> test(@Path("username") String username);

}
