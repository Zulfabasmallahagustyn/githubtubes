package com.zulfabasmallahagustyn.myapplication.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zulfabasmallahagustyn.myapplication.model.follow.ModelFollow;
import com.zulfabasmallahagustyn.myapplication.model.search.ModelSearch;
import com.zulfabasmallahagustyn.myapplication.model.search.ModelSearchData;
import com.zulfabasmallahagustyn.myapplication.model.user.ModelUser;
import com.zulfabasmallahagustyn.myapplication.networking.ApiClient;
import com.zulfabasmallahagustyn.myapplication.networking.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<ModelSearchData>> modelSearchMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<ModelFollow>> modelFollowersMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<ModelFollow>> modelFollowingMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<ModelUser> modelUserMutableLiveData = new MutableLiveData<>();
    public static String strApiKey = "mau API KEY? Tonton video tutorialnya ya^^";

    //method search user
    public void setSearchUser(String query) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelSearch> call = apiService.searchUser(strApiKey, query);
        call.enqueue(new Callback<ModelSearch>() {
            @Override
            public void onResponse(@NonNull Call<ModelSearch> call, @NonNull Response<ModelSearch> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    ArrayList<ModelSearchData> items = new ArrayList<>(response.body().getModelSearchData());
                    modelSearchMutableLiveData.setValue(items);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelSearch> call, @NonNull Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method view detail user
    public void setUserDetail(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelUser> call = apiService.detailUser(strUsername);
        call.enqueue(new Callback<ModelUser>() {

            @Override
            public void onResponse(@NonNull Call<ModelUser> call, @NonNull Response<ModelUser> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelUserMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelUser> call, @NonNull Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method get followers
    public void setFollowersUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followersUser(strApiKey, strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ModelFollow>> call, @NonNull Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowersMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ModelFollow>> call, @NonNull Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method get following
    public void setFollowingUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followingUser(strApiKey, strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ModelFollow>> call, @NonNull Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowingMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ModelFollow>> call, @NonNull Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<ModelSearchData>> getResultList() {
        return modelSearchMutableLiveData;
    }

    public LiveData<ModelUser> getUserList() {
        return modelUserMutableLiveData;
    }

    public LiveData<ArrayList<ModelFollow>> getFollowersUser() {
        return modelFollowersMutableLiveData;
    }

    public LiveData<ArrayList<ModelFollow>> getFollowingUser() {
        return modelFollowingMutableLiveData;
    }

}