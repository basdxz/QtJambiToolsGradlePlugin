package com.github.basdxz.qtjavelin.task;

import org.gradle.api.DefaultTask;

public abstract class InitializeableTask extends DefaultTask {
    public abstract void init();
}
