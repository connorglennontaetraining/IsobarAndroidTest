package com.roundarch.codetest.part2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DataModel implements Serializable, Parcelable {
    /**
     * 
     */
    private static final long serialVersionUID = -702176057888480130L;

    private String text1;
    private String text2;
    private double text3;

    public DataModel(){

    }

    protected DataModel(Parcel in) {
        text1 = in.readString();
        text2 = in.readString();
        text3 = in.readDouble();
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public double getText3() {
        return text3;
    }

    public void setText3(double text3) {
        this.text3 = text3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text1);
        parcel.writeString(text2);
        parcel.writeDouble(text3);
    }
}
