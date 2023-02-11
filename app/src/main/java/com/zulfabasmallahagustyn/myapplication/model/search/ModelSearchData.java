package com.zulfabasmallahagustyn.myapplication.model.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ModelSearchData implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("url")
    private String url;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ModelSearchData() {
    }

    protected ModelSearchData(Parcel in) {
        this.avatarUrl = in.readString();
        this.login = in.readString();
        this.url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.avatarUrl);
        parcel.writeString(this.login);
        parcel.writeString(this.url);
    }

    public static final Parcelable.Creator<ModelSearchData> CREATOR = new Parcelable.Creator<ModelSearchData>() {
        @Override
        public ModelSearchData createFromParcel(Parcel in) {
            return new ModelSearchData(in);
        }

        @Override
        public ModelSearchData[] newArray(int size) {
            return new ModelSearchData[size];
        }
    };

}