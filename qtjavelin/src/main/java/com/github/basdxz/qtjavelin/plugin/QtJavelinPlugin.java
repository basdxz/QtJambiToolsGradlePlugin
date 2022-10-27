package com.github.basdxz.qtjavelin.plugin;

import com.github.basdxz.qtjavelin.util.Constants;
import lombok.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static com.github.basdxz.qtjavelin.dependencies.impl.QtJavelinDependencyAdder.qtJavelinDependencyAdder;
import static com.github.basdxz.qtjavelin.extension.impl.QtJavelinExtension.qtJavelinExtension;
import static com.github.basdxz.qtjavelin.task.impl.QTJavaCompilationTaskInitializer.qtJavaCompilationTaskInitializer;

public class QtJavelinPlugin implements Plugin<Project> {
    @Override
    public void apply(@NonNull Project project) {
        ensureJavaPluginPresent(project);
        initExtension(project);
        afterEvaluation(project);
    }

    protected void ensureJavaPluginPresent(@NonNull Project project) {
        if (!project.getPlugins().hasPlugin(Constants.JAVA_PLUGIN_ID))
            throw new IllegalStateException("The Gradle java plugin is missing, which qtjavelin requires to work.");
    }

    protected void initExtension(@NonNull Project project) {
        qtJavelinExtension(project);
    }

    protected void afterEvaluation(@NonNull Project project) {
        project.afterEvaluate(qtJavelinDependencyAdder());
        project.afterEvaluate(qtJavaCompilationTaskInitializer());
    }
}