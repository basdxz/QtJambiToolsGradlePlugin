package com.basdxz.qtjambitools.task.impl;

import com.basdxz.qtjambitools.task.TaskInitializer;
import lombok.*;
import org.gradle.api.Project;

public class QTJavaCompilationTaskInitializer implements TaskInitializer {
    protected final static TaskInitializer INSTANCE = new QTJavaCompilationTaskInitializer();

    @Override
    public void initTasks(@NonNull Project project) {
        project.getTasks().stream()
               .filter(task -> task instanceof QTJavaCompilationTask)
               .map(task -> (QTJavaCompilationTask) task)
               .forEach(QTJavaCompilationTask::init);
    }

    public static TaskInitializer qtJavaCompilationTaskInitializer() {
        return INSTANCE;
    }
}
