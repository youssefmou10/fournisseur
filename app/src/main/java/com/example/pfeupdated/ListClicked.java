package com.example.pfeupdated;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListClicked extends AppCompatActivity {

    ListView list ;
    String items[] = {"apple","banane","fraise"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clicked);

        list = findViewById(R.id.listclicked);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                {
                    Toast.makeText(ListClicked.this, "Item 1", Toast.LENGTH_SHORT).show();
                }
                if (i == 1)
                {
                    Toast.makeText(ListClicked.this, "Item 2", Toast.LENGTH_SHORT).show();
                }
                if (i == 2)
                {
                    Toast.makeText(ListClicked.this, "Item 3", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
