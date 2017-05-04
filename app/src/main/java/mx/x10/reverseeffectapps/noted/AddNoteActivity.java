package mx.x10.reverseeffectapps.noted;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteEditText = (EditText)findViewById(R.id.edit_text_add_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_cancel_note) {
            finish();
        }
        else if (item.getItemId() == R.id.menu_item_save_note) {
            // Save code
            File saveDir = getFilesDir();
            Date currentDate = new Date();

            String savePath = saveDir.getAbsolutePath().concat("/" + currentDate.toString() + ".txt");
            savePath = savePath.replace(":", "-").replace(" ", "_");


            try {
                FileOutputStream outputStream = new FileOutputStream(new File(savePath));
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(noteEditText.getText().toString());
                writer.flush();
                writer.close();

                Toast.makeText(AddNoteActivity.this, "New note has been made.", Toast.LENGTH_SHORT).show();
            }
            catch (IOException ex) {
                System.out.println(AddNoteActivity.class.getSimpleName() + " > IOException: " + ex.getMessage());
            }

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
