package com.roundarch.codetest.part3;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Part3Service extends Service {

    private final String TAG = this.getClass().getSimpleName();

    public static final String ACTION_SERVICE_DATA_UPDATED = "com.roundarch.codetest.ACTION_SERVICE_DATA_UPDATED";

    private List<Map<String,String>> data = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Part3ServiceBinder();
    }

    public void refreshData() {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                data = new ArrayList<>();
                List<Result> resultList = requestData();
                for(Result result: resultList) {
                    Map<String, String> resultValues = new HashMap<>();
                    resultValues.put("zipcode", result.getZipcode());
                    resultValues.put("zipclass", result.getZipClass());
                    data.add(resultValues);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                broadcastDataUpdated();
            }
        }.execute();
    }

    private void broadcastDataUpdated() {
        Intent intent = new Intent();
        intent.setAction(ACTION_SERVICE_DATA_UPDATED);
        intent.putParcelableArrayListExtra("results", (ArrayList)data);
        sendBroadcast(intent);
    }

    public final class Part3ServiceBinder extends Binder {
        public Part3Service getService(){
            return Part3Service.this;
        }
    }

    private List<Result> requestData(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request;
        Response response;
        Results results;

        try {
            request = new Request.Builder()
                    .url("http://gomashup.com/json.php?fds=geo/usa/zipcode/state/IL")
                    .header("Accept", "application/json")
                    .get()
                    .build();
            response = okHttpClient.newCall(request).execute();
            String body = response.body().string().replace("(", "").replace(")", "");
            results = new Gson().fromJson(body, Results.class);
            return results.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    interface IRequest{
        @GET("json.php?fds=geo/usa/zipcode/state/IL")
        Observable<Results> getResults();
    }

}
