package arend.arendvandormalen__pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static android.R.attr.id;

/**
 * Created by Arend on 2016-11-08.
 * This activity creates the story based on user input and transfers it to the next screen.
 */

public class fillInScreen extends AppCompatActivity{

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_screen);

        InputStream is;

        // Try to open a random file. File not found will create an empty story.
        try {
            Random rand = new Random();
            int value = rand.nextInt(4);
            switch (value) {
                case 0: is = getAssets().open("madlib1_tarzan.txt");
                    break;
                case 1: is = getAssets().open("madlib2_university.txt");
                    break;
                case 2: is = getAssets().open("madlib3_clothes.txt");
                    break;
                case 3: is = getAssets().open("madlib4_dance.txt");
                    break;
                default: is = getAssets().open("madlib0_simple.txt");
                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
            is = null;
        }

        if (is != null) {
            story = new Story(is);
            // Set new view
            setContentView(R.layout.activity_fill_in_screen);

            // Get initial number of placeholders
            int placeholderNum = story.getPlaceholderCount();
            TextView textView = (TextView) findViewById(R.id.numLeft);
            String number = Integer.toString(placeholderNum);
            textView.setText(number + " word(s) left");

            // Get type of first placeholder
            TextView hintView = (TextView) findViewById(R.id.fillInBox);
            hintView.setHint(story.getNextPlaceholder());

        }

    }

    public void submitWord(View view){

        // Select the text box
        EditText contentEdit = (EditText) findViewById(R.id.fillInBox);

        // Load input word and clear input box
        String word = contentEdit.getText().toString();

        // error message if word is empty
        TextView warning = (TextView) findViewById(R.id.empty_input);
        if("".equals(word)){
            warning.setText(R.string.warning_message);
            return;
        } else {
            warning.setText("");
        }

        story.fillInPlaceholder(word);
        contentEdit.setText("");

        // Update number of placeholders that is left
        int placeholdersLeft = story.getPlaceholderRemainingCount();
        TextView textView = (TextView) findViewById(R.id.numLeft);
        Log.d("placeholders left is", Integer.toString(placeholdersLeft));
        String number = Integer.toString(placeholdersLeft);
        textView.setText(number + " word(s) left");

        // Update displayed hint or placeholder type
        contentEdit.setHint(story.getNextPlaceholder());

        if(story.isFilledIn()) {

            Log.d("Story complete", "ga naar volgend scherm");

            // Pass the story to next activity and go to next screen
            Intent getThirdScreen = new Intent(this, resultScreen.class);
            String storyText = story.toString();
            getThirdScreen.putExtra("storyText", storyText);
            startActivity(getThirdScreen);

        }

        // End submitWord
        }




}
