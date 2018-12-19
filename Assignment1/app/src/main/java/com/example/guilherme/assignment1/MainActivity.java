package com.example.guilherme.assignment1;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

private class MyColor{
    int colorId;
    String colorText;

    public MyColor(int colorId, String colorText) {
        this.colorId = colorId;
        this.colorText = colorText;
    }

    public int getColorId() {
        return colorId;
    }

    public String getColorText() {
        return colorText;
    }
};


    private Button mLeft_Button;
    private Button mRight_Button;
    private TextView mColorToGuess;
    private TextView mCount_text;

    private int mcount;


    private ArrayList<MyColor> colors = new ArrayList<>();


    int randColor1;
    int randColor2;
    int textColor;

    boolean hard_mode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLeft_Button = findViewById(R.id.left_button);
        mRight_Button = findViewById(R.id.right_button);
        mColorToGuess = findViewById(R.id.colorToGuess);
        mCount_text = findViewById(R.id.count_text);



        colors.add(new MyColor(Color.rgb(0,0,0),"Black"));
        colors.add(new MyColor(Color.rgb(170,110,40),"Brown"));
        colors.add(new MyColor(Color.rgb(0,0,128),"Navy"));
        colors.add(new MyColor(Color.rgb(230,25,75),"Red"));
        colors.add(new MyColor(Color.rgb(245,130,48),"Orange"));
        colors.add(new MyColor(Color.rgb(255,255,25),"Yellow"));
        colors.add(new MyColor(Color.rgb(60,180,75),"Green"));
        colors.add(new MyColor(Color.rgb(70,240,240),"Cyan"));
        colors.add(new MyColor(Color.rgb(0,130,200),"Blue"));
        colors.add(new MyColor(Color.rgb(145,30,180),"Purple"));
        colors.add(new MyColor(Color.rgb(240,50,230),"Magenta"));
        colors.add(new MyColor(Color.rgb(128,128,128),"Grey"));



        generateColors();

    }

    public void buttonClicked(View view) {
        if (view.getId() == mLeft_Button.getId()) {
            if (randColor1 == textColor) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                mcount++;
            }else {
                Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
                mcount--;
            }
        } else {
            if (randColor2== textColor){
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                mcount++;
            }else {
                Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
                mcount--;
            }
        }
        if (mcount == 10 && (hard_mode == false)){
            initializeHardMode();
        }

        mCount_text.setText("Score: "+mcount);
        generateColors();
    }

    private void generateColors(){
        randColor1 = (int) (Math.random() * colors.size());

        do {
            randColor2 = (int) (Math.random() * colors.size());
        } while (randColor1 == randColor2);

        mLeft_Button.setBackgroundColor(colors.get(randColor1).getColorId());

        mRight_Button.setBackgroundColor(colors.get(randColor2).getColorId());


        if (Math.random() < 0.5) {
            mColorToGuess.setText(colors.get(randColor1).getColorText());
            textColor = randColor1;
        }else {
            mColorToGuess.setText(colors.get(randColor2).getColorText());
            textColor = randColor2;
        }
        

    }

    private void initializeHardMode(){
        hard_mode = true;
        colors.clear();
        colors.add(new MyColor(Color.rgb(227,37,107),"Razzmatazz"));
        colors.add(new MyColor(Color.rgb(11,218,81),"Malachite"));
        colors.add(new MyColor(Color.rgb(228,155,15),"Gamboge"));
        colors.add(new MyColor(Color.rgb(255,56,0),"Coquelicot"));
        colors.add(new MyColor(Color.rgb(255,255,49),"Daffodil"));
        colors.add(new MyColor(Color.rgb(224,176,255),"Mauve"));
        colors.add(new MyColor(Color.rgb(223,255,0),"Chartreuse"));
        colors.add(new MyColor(Color.rgb(0,191,255),"Capri"));
        colors.add(new MyColor(Color.rgb(255,0,255),"Fuchsia"));
        colors.add(new MyColor(Color.rgb(255,32,82),"Awesome"));
        colors.add(new MyColor(Color.rgb(223,0,255),"Phlox"));
        colors.add(new MyColor(Color.rgb(113,110,97),"Flint"));
        colors.add(new MyColor(Color.rgb(102,2,60),"Tyrian"));
        colors.add(new MyColor(Color.rgb(42,82,190),"Cerulean"));
        colors.add(new MyColor(Color.rgb(169,186,157),"Laurel"));
        colors.add(new MyColor(Color.rgb(209,173,82),"Sable"));
        colors.add(new MyColor(Color.rgb(236,88,0),"Persimmon"));
        colors.add(new MyColor(Color.rgb(227,66,52),"Vermilion"));
        colors.add(new MyColor(Color.rgb(112,66,20),"Sepia"));
        colors.add(new MyColor(Color.rgb(83,70,66),"Hickory"));
        colors.add(new MyColor(Color.rgb(52,54,55),"Obsidian"));
        colors.add(new MyColor(Color.rgb(232,214,209),"Calamine"));
        colors.add(new MyColor(Color.rgb(216,191,216),"Thistle"));
        colors.add(new MyColor(Color.rgb(223,115,255),"Heliotrope"));
    }
}
