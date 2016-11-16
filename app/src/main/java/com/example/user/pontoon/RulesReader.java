package com.example.user.pontoon;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by user on 16/11/2016.
 */

public class RulesReader {

    private String mText;
    private Scanner mFileReader;

    public RulesReader(InputStream file) {
        mText = "";
        mFileReader = new Scanner(file);
    }

    public String getRules() {
        while (mFileReader.hasNextLine()) {
            String lineOfText = mFileReader.nextLine();
            mText += lineOfText + "\n";
            mFileReader.hasNextLine();
        }
        return mText;
    }
}
