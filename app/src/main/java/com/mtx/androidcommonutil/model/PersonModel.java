package com.mtx.androidcommonutil.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonModel implements Parcelable {
    private String name;
    private String id;
    private boolean isMale;
    private int age;

    public PersonModel() {
    }

    public PersonModel(String name, String id, boolean isMale, int age) {
        this.name = name;
        this.id = id;
        this.isMale = isMale;
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeByte((byte) (isMale ? 1 : 0));
        parcel.writeInt(age);
    }

    public static final Creator<PersonModel> CREATOR = new Creator<PersonModel>() {
        @Override
        public PersonModel createFromParcel(Parcel parcel) {
            PersonModel personModel = new PersonModel();
            personModel.name = parcel.readString();
            personModel.id = parcel.readString();
            personModel.isMale = parcel.readByte() != 0;
            personModel.age = parcel.readInt();
            return personModel;
        }

        @Override
        public PersonModel[] newArray(int i) {
            return new PersonModel[0];
        }
    };

    @Override
    public String toString() {
        return "PersonModel{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", isMale=" + isMale +
                ", age=" + age +
                '}';
    }
}
