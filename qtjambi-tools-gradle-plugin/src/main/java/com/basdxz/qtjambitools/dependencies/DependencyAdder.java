package com.basdxz.qtjambitools.dependencies;

import lombok.*;
import org.gradle.api.Project;
import org.gradle.api.internal.GradleInternal;
import org.gradle.initialization.ModelConfigurationListener;

public interface DependencyAdder extends ModelConfigurationListener {
    @Override
    default void onConfigure(@NonNull GradleInternal model) {
        addDependencies(model.getRootProject());
    }

    void addDependencies(@NonNull Project project);
}
