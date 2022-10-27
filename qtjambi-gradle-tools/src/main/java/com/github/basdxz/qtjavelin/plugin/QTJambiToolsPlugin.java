package com.github.basdxz.qtjavelin.plugin;

import com.github.basdxz.qtjavelin.task.impl.QTJavaCompilationTaskInitializer;
import com.github.basdxz.qtjavelin.util.Constants;
import lombok.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static com.github.basdxz.qtjavelin.dependencies.impl.QTJambiDependencyAdder.qtJambiDependencyAdder;
import static com.github.basdxz.qtjavelin.extension.impl.QtJambiExtension.qtJambiExtension;

public class QTJambiToolsPlugin implements Plugin<Project> {
    @Override
    public void apply(@NonNull Project project) {
        ensureJavaPluginPresent(project);
        initExtension(project);
        addListeners(project);
    }

    protected void ensureJavaPluginPresent(@NonNull Project project) {
        if (!project.getPlugins().hasPlugin(Constants.JAVA_PLUGIN_ID))
            throw new IllegalStateException("Java Plugin is missing, which qtjambitools requires to work.");
    }

    protected void initExtension(@NonNull Project project) {
        qtJambiExtension(project);
    }

    protected void addListeners(@NonNull Project project) {
        val gradle = project.getGradle();
        gradle.addListener(qtJambiDependencyAdder());
        gradle.addListener(QTJavaCompilationTaskInitializer.qtJavaCompilationTaskInitializer());
    }
}