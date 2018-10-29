package com.chemutai.funfacts;

import java.util.Random;

public class FactBook {

    //fields (member variables) - properties about the object

    private final String[] mFacts = new String[]{
            "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
            "Repudiandae sed dolores provident quo esse magni inventore sunt voluptatum aperiam.",
            "Amet velit tenetur aliquid voluptas eaque sequi deserunt labore inventore aspernatur.",
            "Lorem, ipsum dolor sit amet consectetur adipisicing elit.",
            "Repudiandae, numquam.",
            "Corporis sint eum pariatur? Maxime dignissimos quo accusamus.",
            "Vitae adipisci vel soluta, quasi sed, quis nostrum placeat minima a totam?"
    };



    //methods - actions the objects can take
    public String getFact(){
        //the button was clicked, so update the did you know text view with the new Fact Fun
//                String fact = "Ostriches can run faster than horses";

        String fact = "";
        //Randomly select a fact
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mFacts.length);
//                fact = randomNumber + "";
        fact = mFacts[randomNumber];

        return fact;
    }
}
