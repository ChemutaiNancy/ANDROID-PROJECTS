package com.chemutai.funfacts;

import android.graphics.Color;

import java.util.Random;

public class ColorWheel {

//fields (member variables) - properties about the object

    private String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };



    //methods - actions the objects can take
    public int getColor(){
        //the button was clicked, so update the did you know text view with the new Fact Fun
//                String fact = "Ostriches can run faster than horses";

        String color;
        //Randomly select a fact
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
//                fact = randomNumber + "";
        color = mColors[randomNumber];

        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

}
