package com.github.basdxz.qtjavelin.dependencies;

import lombok.*;
import org.gradle.api.Action;
import org.gradle.api.Project;

public interface DependencyAdder extends Action<Project> {
    @Override
    default void execute(@NonNull Project project) {
        addDependencies(project);
    }

    void addDependencies(@NonNull Project project);
}
