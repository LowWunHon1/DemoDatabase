package sg.edu.rp.c346.id21012050.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    EditText etDate;
    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayList<String> allist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);

        allist = new ArrayList<>();
        ArrayAdapter<String> aalist = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, allist);
        lv.setAdapter(aalist);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                String mainTask = etTask.getText().toString();
                String mainDate = etDate.getText().toString();

                db.insertTask(mainTask, mainDate);

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                String mainTask = etTask.getText().toString();
                String mainDate = etDate.getText().toString();

                Collections.sort(allist);

                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                    aalist.add(String.format("%d \n %s \n %s",allist.size()+1, mainTask, mainDate));
                }

                tvResults.setText(txt);

            }
        });

    }
}