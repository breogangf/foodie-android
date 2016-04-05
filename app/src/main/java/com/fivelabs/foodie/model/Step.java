package com.fivelabs.foodie.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by breogangf on 5/4/16.
 */
public class Step implements Parcelable{

    int step;
    String description;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected Step(Parcel in) {
        step = in.readInt();
        description = in.readString();
    }

    @Override
    public String toString() {
        return "Step{" +
                "step=" + step +
                ", description='" + description + '\'' +
                '}';
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(step);
        dest.writeString(description);
    }
}
