package greenhouse.hackathon.com.foodhackathon;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ShowStepsActivity extends FragmentActivity{
    //Voice Recognition
    private static final int SPEECH_REQUEST_CODE = 0;
    private Recipe mRecipe;

    //UI Components
    private StepFragment mStepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displaySpeechRecognizer();

        if(findViewById(R.id.fragment_step) != null){
            if(savedInstanceState != null){
                return;
            }
        }
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

            Toast.makeText(getApplicationContext(), "Spoken " + spokenText, Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBack(View view){
        onBack();
    }

    private void onBack(){
        if(mRecipe.getCurrentStep() > 0){
            mRecipe.decreaseStep();
            changeScene(mRecipe.getCurrentStep());
        }
    }

    public void onNext(View view){
        onNext();
    }

    private void onNext(){
        if(mRecipe.getCurrentStep() < mRecipe.getSteps().size() - 1){
            mRecipe.increaseStep();
            changeScene(mRecipe.getCurrentStep());
        }
    }

    private void changeScene(int currentScene){

    }
}
