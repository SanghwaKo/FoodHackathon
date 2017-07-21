package greenhouse.hackathon.com.foodhackathon;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by KSH on 2017-07-21.
 */

public class StepFragment extends Fragment {
    private Step mCurrentStep;

    private ImageView mImg;
    private TextView mInstruction;

    public void setStep(Step step){
        mCurrentStep = step;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_step, container, false);
    }

    private void setUI(Step step){
        mInstruction.setText(step.getInstruction());
        Glide.with(this).load(step.getImgUrl()).into(mImg);
    }
}
