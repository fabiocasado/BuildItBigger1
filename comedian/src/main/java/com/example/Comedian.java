package com.example;

import java.util.Random;

public class Comedian {
    private String[] jokeList = {
        "A recent study has found that women who carry a little extra weight live longer than the men who mention it.",
        "Today a man knocked on my door and asked for a small donation towards the local swimming pool. I gave him a glass of water.",
        "I want to die peacefully in my sleep, like my grandfather.. Not screaming and yelling like the passengers in his car.",
        "If i had a dollar for every girl that found me unattractive, they would eventually find me attractive",
        "I find it ironic that the colors red, white, and blue stand for freedom until they are flashing behind you.",
        "I changed my password to \"incorrect\". So whenever I forget what it is the computer will say \"Your password is incorrect\".\n" +
                "\n",
    };

    public String getJoke() {
        return jokeList[new Random().nextInt(5)];
    }
}
