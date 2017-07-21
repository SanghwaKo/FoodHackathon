package greenhouse.hackathon.com.foodhackathon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KSH on 2017-07-21.
 */

public class Step implements Parcelable{
    private String instruction;
    private String imgUrl;

    public int describeContents(){
        return  0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeString(instruction);
        out.writeString(imgUrl);
    }

    public static final Parcelable.Creator<Step> CREATOR =
            new Parcelable.Creator<Step>(){
        public Step createFromParcel(Parcel in){
            return new Step(in);
        }

        public Step[] newArray(int size){
            return new Step[size];
        }
    };

    private Step(Parcel in){
        instruction = in.readString();
        imgUrl = in.readString();
    }
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
