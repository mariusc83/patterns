package org.mariusconstantin.patterns.network;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by MConstantin on 9/28/2016.
 */

public interface IApiService {
    String BASE_API_URL="https://api.deezer.com";

    @GET("/playlist/{playlistId}/tracks")
    Observable<JSPlaylist> getTracks(@Path("playlistId") long playlistId);
}