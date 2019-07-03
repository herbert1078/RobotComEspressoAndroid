package com.example.heitorcolangelo.espressotests.TesteComMock;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.heitorcolangelo.espressotests.R;
import com.example.heitorcolangelo.espressotests.mocks.Mocks;
import com.example.heitorcolangelo.espressotests.network.Api;
import com.example.heitorcolangelo.espressotests.network.UsersApi;
import com.example.heitorcolangelo.espressotests.ui.activity.MainActivity;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class TesteComMock1 {

    private MockWebServer server;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        setupServerUrl();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    private void setupServerUrl() {
        String url = server.url("/").toString();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final UsersApi usersApi = UsersApi.getInstance();

        final Api api = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(UsersApi.GSON))
                .client(client)
                .build()
                .create(Api.class);

        setField(usersApi, "api", api);
    }

    private void setField(Object target, String fieldName, Object value) {
        new Mirror()
                .on(target)
                .set()
                .field(fieldName)
                .withValue(value);
    }

    @Test
    public void mostrarTodosUsuariosEvalidarTela() throws InterruptedException {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(Mocks.SUCESSO));
        mActivityRule.launchActivity(new Intent());
        Thread.sleep(3000);
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void FalharOTesteRquisicaoErrada() throws InterruptedException {
        server.enqueue(new MockResponse().setResponseCode(400).setBody(Mocks.ERRO));
        mActivityRule.launchActivity(new Intent());
        Thread.sleep(3000);
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void SoUmElemento() throws InterruptedException {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(Mocks.SoUm));
        mActivityRule.launchActivity(new Intent());
        Thread.sleep(3000);
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }

}
