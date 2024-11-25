package com.example.tictacktoe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyAdapter adapter;
    String []num;
    char currentTurn = 'X';
    Button btn;
    int totalCount;
    boolean win = false;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        num = new String[]{"-","-","-","-","-","-","-","-","-"};
        adapter = new MyAdapter(this, num);
        gridView.setAdapter(adapter);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(num);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    toast.cancel();
                } catch (Exception e) {

                }
                TextView textView = view.findViewById(R.id.textView);
                String text = textView.getText().toString().trim();

                if(!text.equals("-") || win){
                    if(win){
                        toast = Toast.makeText(MainActivity.this, currentTurn+" Already wins", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }else {
                        toast = Toast.makeText(MainActivity.this, "Already Edited", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    }else{
                    btn.setVisibility(View.INVISIBLE);
                    textView.setText("   "+currentTurn+"   ");
                    num[position] = String.valueOf(currentTurn);
                }
                win = isWin(String.valueOf(currentTurn));
                if(win){
                    toast = Toast.makeText(MainActivity.this, currentTurn+" win", Toast.LENGTH_SHORT);
                    toast.show();
                    btn.setVisibility(View.VISIBLE);

                    return;
                }
                currentTurn = currentTurn=='X'?'O':'X';
                totalCount++;
                if(totalCount==9){
                    toast = Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_SHORT);
                    toast.show();
                    btn.setVisibility(View.VISIBLE);
                }
            }
        });







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void reset(String[] num) {
        for(int i=0; i<9; i++){
            num[i]="-";
        }
        win = false;
        totalCount = 0;
        adapter.notifyDataSetChanged();
    }

    public boolean isWin(String str) {
        for(int i=0; i<3; i++) {
            int count = 0;
            for(int j=0; j<3; j++) {
                if(num[3*i+j].equals(str)){
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
            count = 0;
            for(int j=0; j<3; j++) {
                if(num[i+3*j].equals(str)){
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
            count = 0;
            if(i==0)
                for(int j=0; j<3; j++) {
                    if(num[4*j].equals(str)){
                        count++;
                        if (count == 3) {
                            return true;
                        }
                    }
                }
            count = 0;
            if(i==2)
                for(int j=0; j<3; j++) {
                    if(num[2*(j+1)].equals(str)){
                        count++;
                        if (count == 3) {
                            return true;
                        }
                    }

                }
        }
        return false;
    }

}