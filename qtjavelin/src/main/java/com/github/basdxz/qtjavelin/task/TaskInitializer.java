package com.github.basdxz.qtjavelin.task;

import lombok.*;
import org.gradle.api.Project;
import org.gradle.api.internal.GradleInternal;
import org.gradle.initialization.ModelConfigurationListener;

public interface TaskInitializer extends ModelConfigurationListener {
    @Override
    default void onConfigure(@NonNull GradleInternal model) {
        initTasks(model.getRootProject());
    }

    void initTasks(@NonNull Project project);
}
