package org.mariusconstantin.patterns.network;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariusconstantin.patterns.BuildConfig;
import org.mariusconstantin.patterns.di.ApplicationComponent;
import org.mariusconstantin.patterns.di.ApplicationModule;
import org.mariusconstantin.patterns.di.DaggerApplicationComponent;
import org.mariusconstantin.patterns.utils.TestUtils;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import rx.Observable;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MConstantin on 10/3/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ApiServiceTest {

    @Test
    public void testGetPlaylist() throws Exception {
        // given
        final long playlistId=121312L;
        final MockWebServer mockWebServer = new MockWebServer();
        final String jsonExample = TestUtils.fetchJson("jsons/playlist.json");
        assertThat(jsonExample).isNotNull();
        mockWebServer.enqueue(new MockResponse().setBody(jsonExample));;
        mockWebServer.start();
        final ApplicationComponent applicationComponent =
                DaggerApplicationComponent.builder().applicationModule(new
                        ApplicationModule(RuntimeEnvironment.application)).networkModule(new
                        NetworkModule(mockWebServer.url("/album/"+playlistId+"/tracks").toString())).build();

        final IApiService apiService = applicationComponent.getApiService();

        // when
        final Observable<JSPlaylist> observable = apiService.getTracks(playlistId);
        final JSPlaylist playlist = observable.toBlocking().first();
        mockWebServer.shutdown();

        // then
        assertThat(playlist).isNotNull();
        assertThat(playlist.data).isNotNull();
        assertThat(playlist.data.length).isEqualTo(2);
        assertThat(playlist.data[0]).isNotNull();
        assertThat(playlist.data[1]).isNotNull();
    }

}