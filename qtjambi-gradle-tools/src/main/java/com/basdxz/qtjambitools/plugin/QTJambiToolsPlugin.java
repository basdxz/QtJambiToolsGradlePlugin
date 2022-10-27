package com.basdxz.qtjambitools.plugin;

import lombok.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static com.basdxz.qtjambitools.dependencies.impl.QTJambiDependencyAdder.qtJambiDependencyAdder;
import static com.basdxz.qtjambitools.extension.impl.QtJambiExtension.qtJambiExtension;
import static com.basdxz.qtjambitools.task.impl.QTJavaCompilationTaskInitializer.qtJavaCompilationTaskInitializer;
import static com.basdxz.qtjambitools.util.Constants.JAVA_PLUGIN_ID;

public class QTJambiToolsPlugin implements Plugin<Project> {
    @Override
    public void apply(@NonNull Project project) {
        ensureJavaPluginPresent(project);
        initExtension(project);
        addListeners(project);
    }

    protected void ensureJavaPluginPresent(@NonNull Project project) {
        if (!project.getPlugins().hasPlugin(JAVA_PLUGIN_ID))
            throw new IllegalStateException("Java Plugin is missing, which qtjambitools requires to work.");
    }

    protected void initExtension(@NonNull Project project) {
        qtJambiExtension(project);
    }

    protected void addListeners(@NonNull Project project) {
        val gradle = project.getGradle();
        gradle.addListener(qtJambiDependencyAdder());
        gradle.addListener(qtJavaCompilationTaskInitializer());
    }
}