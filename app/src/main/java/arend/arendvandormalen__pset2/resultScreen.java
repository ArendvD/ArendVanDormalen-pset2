package arend.arendvandormalen__pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Arend on 2016-11-11.
 * This screen displays the resulting story based on user input in the previous screen
 */

public class resultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        // Retrieve text from story
        Bundle bundle = getIntent().getExtras();
        String story = bundle.getString("storyText");

        // Add text to xml
        TextView results = (TextView)findViewById(R.id.story_output);
        results.setText(story);

    }

    // Restart button
    public void restartStory(View view){
        Intent getFirstScreen = new Intent(this, homeScreen.class);
        startActivity(getFirstScreen);
    }

}
