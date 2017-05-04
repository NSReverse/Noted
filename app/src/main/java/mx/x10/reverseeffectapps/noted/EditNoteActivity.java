package mx.x10.reverseeffectapps.noted;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        String filePath = getIntent().getStringExtra("FILE_PATH");
        noteEditText = (EditText)findViewById(R.id.edit_text_edit_note);

        try {
            File currentFile = new File(filePath);
            FileInputStream inputStream = new FileInputStream(currentFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            StringBuilder builder = new StringBuilder("");

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            noteEditText.setText(builder.toString());
        }
        catch (IOException ex) {
            System.out.println(EditNoteActivity.class.getSimpleName() + " > IOException: " + ex.getMessage());
        }
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
            String savePath = getIntent().getStringExtra("FILE_PATH");

            try {
                FileOutputStream outputStream = new FileOutputStream(new File(savePath));
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write(noteEditText.getText().toString());
                writer.flush();
                writer.close();

                Toast.makeText(EditNoteActivity.this, "Note has been updated.", Toast.LENGTH_SHORT).show();
            }
            catch (IOException ex) {
                System.out.println(AddNoteActivity.class.getSimpleName() + " > IOException: " + ex.getMessage());
            }

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
