package mx.x10.reverseeffectapps.noted;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class ViewStoredNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stored_notes);

        reloadData();
    }

    private void reloadData() {
        File documentsDirectory = new File(ApplicationDelegate.documentsDirectory);
        final File[] contents = documentsDirectory.listFiles();

        for (File currentFile : contents) {
            System.out.println(currentFile.getAbsolutePath());
        }

        ArrayAdapter<File> adapter = new ArrayAdapter<File>(this, R.layout.activity_view_stored_notes, contents) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_view_notes_activity_row, null);
                }

                final File currentFile = contents[position];
                String[] pathComponents = currentFile.getAbsolutePath().split("/");

                TextView titleTextView = (TextView)convertView.findViewById(R.id.text_view_title);
                titleTextView.setText(pathComponents[pathComponents.length - 1]);

                final ImageView moreImageView = (ImageView)convertView.findViewById(R.id.image_view_more);
                moreImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu menu = new PopupMenu(ViewStoredNotesActivity.this, moreImageView);
                        menu.inflate(R.menu.menu_more_note_options);
                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                if (item.getItemId() == R.id.menu_item_note_delete) {
                                    deleteNote(currentFile);

                                    return true;
                                }

                                return false;
                            }
                        });
                        menu.show();
                    }
                });

                return convertView;
            }
        };

        ListView notesListView = (ListView)findViewById(R.id.list_view_view_stored_notes);
        notesListView.setAdapter(adapter);
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewStoredNotesActivity.this, EditNoteActivity.class);
                intent.putExtra("FILE_PATH", contents[position].getAbsolutePath());
                startActivity(intent);
            }
        });
    }

    private void deleteNote(File file) {
        boolean deleted = file.delete();

        if (deleted) {
            reloadData();
        }
        else {
            System.out.println(ViewStoredNotesActivity.class.getSimpleName() + " > Unable to delete.");
        }
    }
}
