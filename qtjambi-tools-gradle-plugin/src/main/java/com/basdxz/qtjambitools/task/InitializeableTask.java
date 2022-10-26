package com.basdxz.qtjambitools.task;

import org.gradle.api.DefaultTask;

public abstract class InitializeableTask extends DefaultTask {
    public abstract void init();
}
