package com.example.youthcareapplication.community;

import android.os.Parcel;
import android.os.Parcelable;

public class CommunityItem implements Parcelable {
        private String mText1;
        private String mText2;
        private String mText3;
        private String mText4;

        public CommunityItem(String text1, String text2, String text3, String text4){
            mText1 = text1;
            mText2 = text2;
            mText3 = text3;
            mText4 = text4;
        }

    protected CommunityItem(Parcel in) {
        mText1 = in.readString();
        mText2 = in.readString();
        mText3 = in.readString();
        mText4 = in.readString();
    }

    public static final Creator<CommunityItem> CREATOR = new Creator<CommunityItem>() {
        @Override
        public CommunityItem createFromParcel(Parcel in) {
            return new CommunityItem(in);
        }

        @Override
        public CommunityItem[] newArray(int size) {
            return new CommunityItem[size];
        }
    };

    public String getmText1() { return mText1;
        }

        public String getmText2() {
            return mText2;
        }

        public String getmText3() {
            return mText3;
        }

        public String getmText4() { return mText4; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mText1);
        parcel.writeString(mText2);
        parcel.writeString(mText3);
        parcel.writeString(mText4);
    }
}


