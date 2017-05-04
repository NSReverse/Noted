package mx.x10.reverseeffectapps.noted;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationDelegate.documentsDirectory = getFilesDir() + "/";

        final List<MainMenuOption> optionsList = new ArrayList<>();
        optionsList.add(new MainMenuOption("Add Note", "Create a new note on your device.", R.drawable.ic_border_color_black_24dp));
        optionsList.add(new MainMenuOption("View Notes", "View your notes stored on your device", R.drawable.ic_import_contacts_black_24dp));

        ArrayAdapter<MainMenuOption> adapter = new ArrayAdapter<MainMenuOption>(this,
                R.layout.activity_main, optionsList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_main_activity_row, null);
                }

                ImageView icon = (ImageView)convertView.findViewById(R.id.item_image_view_icon);
                icon.setImageResource(optionsList.get(position).getResId());

                TextView titleTextView = (TextView)convertView.findViewById(R.id.text_view_title);
                titleTextView.setText(optionsList.get(position).getTitle());

                TextView subtitleTextView = (TextView)convertView.findViewById(R.id.text_view_subtitle);
                subtitleTextView.setText(optionsList.get(position).getSubtitle());

                return convertView;
            }
        };

        ListView mainMenuListView = (ListView)findViewById(R.id.list_view_main_activity);
        mainMenuListView.setAdapter(adapter);
        mainMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ViewStoredNotesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
