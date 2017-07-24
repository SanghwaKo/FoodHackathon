package greenhouse.hackathon.com.foodhackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ShowStepsActivity extends Activity implements View.OnTouchListener{
    private static final String TAG = "ShowStepsActivity";
    //Voice Recognition
    private static final int SPEECH_REQUEST_CODE = 0;
    private TextToSpeech mTTS;

    private Recipe mRecipe;
    private FoodApplication mApplication;
    private LinearLayout mLayoutStep;

    //UI Components
    private TextView mTxtInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_view);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.US);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        if(Debug.DEBUG){
                            Log.e(TAG, "Not supported language");
                        }
                    }

                }else{
                    if(Debug.DEBUG){
                        Log.e(TAG, "TTS was not initialized");
                    }
                }
            }
        });

        mTxtInstruction = (TextView)findViewById(R.id.instruction_of_step);
        mLayoutStep = (LinearLayout)findViewById(R.id.layout_step);
        mLayoutStep.setOnTouchListener(this);

        mApplication = FoodApplication.getInstance();

        int position = getIntent().getIntExtra(Constant.TAG_RECIPE, 0);
        mRecipe = mApplication.getRecipe(position);
        changeScene();
    }

    // Create an intent that can start the speech recognizer activity
    private void displaySpeechRecognizer(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // this callback is invoked when the Speech Recognizer returns
    // This is where you process the intent and extract the speech text from the intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK){
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText

            if(isPrevious(spokenText)){
                onBack();
            }
            if(isNext(spokenText)){
                onNext();
            }

            if(Debug.DEBUG){
                Log.d(TAG, "spoken... " + spokenText);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBack(View view){
        onBack();
    }

    private void onBack(){
        if(mRecipe.getCurrentStep() > 0){
            mRecipe.decreaseStep();
            changeScene();
        }
    }

    public void onNext(View view){
        onNext();
    }

    private void onNext(){

        if(mRecipe.getCurrentStep() < mRecipe.getSteps().size() - 1){
            mRecipe.increaseStep();
            changeScene();
        }
    }

    private void changeScene(){
        String instruction = mRecipe.getStep(mRecipe.getCurrentStep()).getInstruction();  // To show and to tell via TTS
        mTxtInstruction.setText(instruction + "\n" +
                getResources().getString(R.string.steps).replace("%%CC", "" + (mRecipe.getCurrentStep() + 1))
                        .replace("%%TT", mRecipe.getSteps().size() + ""));

        speak(instruction);
        // TTS
    }



    private boolean isPrevious(String command){
        for(int i=0; i<Constant.ARR_BACK.length; i++){
            if(Constant.ARR_BACK[i].contains(command.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    private boolean isNext(String command){
        for(int i=0; i<Constant.ARR_NEXT.length; i++){
            if(Constant.ARR_NEXT[i].contains(command.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                displaySpeechRecognizer();
                break;
        }
        return false;
    }

    private void speak(String msg){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            mTTS.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            mTTS.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
