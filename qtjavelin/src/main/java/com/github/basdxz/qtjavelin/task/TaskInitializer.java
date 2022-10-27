package com.github.basdxz.qtjavelin.task;

import lombok.*;
import org.gradle.api.Action;
import org.gradle.api.Project;

public interface TaskInitializer extends Action<Project> {
    @Override
    default void execute(@NonNull Project project) {
        initTasks(project);
    }

    void initTasks(@NonNull Project project);
}
