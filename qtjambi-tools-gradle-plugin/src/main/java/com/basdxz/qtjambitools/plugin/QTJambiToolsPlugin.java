package com.basdxz.qtjambitools.plugin;

import com.basdxz.qtjambitools.task.QTJavaCompilationTask;
import lombok.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.internal.GradleInternal;
import org.gradle.initialization.ModelConfigurationListener;

import static com.basdxz.qtjambitools.dependencies.impl.QTJambiDependencyAdder.addQTJambiDependencies;
import static com.basdxz.qtjambitools.extension.impl.QtJambiExtension.qtJambiExtension;
import static com.basdxz.qtjambitools.util.Constants.JAVA_PLUGIN_ID;

public class QTJambiToolsPlugin implements Plugin<Project>, ModelConfigurationListener {
    @Override
    public void apply(@NonNull Project project) {
        ensureJavaPluginPresent(project);
        qtJambiExtension(project);
        addQTJambiDependencies(project);
        project.getGradle().addListener(this);
    }

    public void ensureJavaPluginPresent(@NonNull Project project) {
        if (!project.getPlugins().hasPlugin(JAVA_PLUGIN_ID))
            throw new IllegalStateException("Java Plugin is missing, which qtjambitools requires to work.");
    }

    @Override
    public void onConfigure(GradleInternal model) {
        model.getRootProject().getTasks().stream()
             .filter(task -> task instanceof QTJavaCompilationTask)
             .map(task -> (QTJavaCompilationTask) task)
             .forEach(QTJavaCompilationTask::init);
    }
}