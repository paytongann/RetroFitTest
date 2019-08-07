package com.example.retrofittest;

import android.os.Parcel;
import android.os.Parcelable;

public class BookItem implements Parcelable {
    VolumeInfo volumeInfo;

    protected BookItem(Parcel in) {
    }

    public static final Creator<BookItem> CREATOR = new Creator<BookItem>() {
        @Override
        public BookItem createFromParcel(Parcel in) {
            return new BookItem(in);
        }

        @Override
        public BookItem[] newArray(int size) {
            return new BookItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
