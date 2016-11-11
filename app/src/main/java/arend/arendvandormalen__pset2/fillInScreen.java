package arend.arendvandormalen__pset2;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Arend on 2016-11-08.
 */

public class fillInScreen extends AppCompatActivity{

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_screen);
        InputStream is;
        try {
            is = getAssets().open("madlib0_simple.txt");
        } catch (IOException e){
            e.printStackTrace();
            is = null;
        }

        if (is != null) {
            story = new Story(is);
            setContentView(R.layout.activity_fill_in_screen);
            int placeholderNum = story.getPlaceholderCount();
            TextView textView = (TextView) findViewById(R.id.numLeft);
            Log.d("placeholders left is", Integer.toString(placeholderNum));
            textView.setText(Integer.toString(placeholderNum));
            TextView hintView = (TextView) findViewById(R.id.fillInBox);
            hintView.setHint(story.getNextPlaceholder());

        }

    }

    public void submitWord(View view){

        if(story.isFilledIn()) {

            Log.d("Story complete","ga naar volgend scherm");
        } else{
            // Select the text box
            EditText contentEdit = (EditText) findViewById(R.id.fillInBox);

            // Load input word
            String word = contentEdit.getText().toString();
            Log.d("chosen word is", word);
            story.fillInPlaceholder(word);
            contentEdit.setText("");

            // update number of placeholders that is left
            int placeholdersLeft = story.getPlaceholderRemainingCount();
            TextView textView = (TextView) findViewById(R.id.numLeft);
            Log.d("placeholders left is", Integer.toString(placeholdersLeft));
            textView.setText(Integer.toString(placeholdersLeft));

            // update displayed hint or placeholder type
            contentEdit.setHint(story.getNextPlaceholder());
        }




    }




}
