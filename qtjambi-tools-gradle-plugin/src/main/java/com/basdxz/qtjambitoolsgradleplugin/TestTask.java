package com.basdxz.qtjambitoolsgradleplugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class TestTask extends DefaultTask {
    public String theWord = "parsing!!";

    @TaskAction
    private void parse() {
        System.out.println(theWord);
        System.out.println(theWord);
        System.out.println(theWord);
        System.out.println(theWord);
        System.out.println(theWord);
        System.out.println(theWord);
        System.out.println(theWord);
        System.out.println(theWord);
    }

//    private void parseJpr(file) {
//    }
}