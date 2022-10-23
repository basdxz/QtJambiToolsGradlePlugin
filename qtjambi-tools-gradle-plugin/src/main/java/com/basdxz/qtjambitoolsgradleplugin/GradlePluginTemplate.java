package com.basdxz.qtjambitoolsgradleplugin;

import com.basdxz.qtjambitoolsgradleplugin.dependencies.impl.QTJambiDependencyAdder;
import com.basdxz.qtjambitoolsgradleplugin.source.impl.JavaSourceSetWrapper;
import lombok.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.SourceSetContainer;

import java.io.File;

import static com.basdxz.qtjambitoolsgradleplugin.configuration.impl.QtJambiExtension.addQTJambiExtension;
import static com.basdxz.qtjambitoolsgradleplugin.source.impl.SourceType.MAIN_GENERATED;
import static com.basdxz.qtjambitoolsgradleplugin.util.GradleUtil.newTask;

public class GradlePluginTemplate implements Plugin<Project> {
    @Override
    public void apply(@NonNull Project project) {
        addQTJambiExtension(project);
        QTJambiDependencyAdder.addQTJambiDependencies(project);

        val task = newTask(project, "hello", TestTask.class);

        //Source
        SourceSetContainer sourceSets = (SourceSetContainer)
                project.getProperties().get("sourceSets");

        val flipper = new File(project.getBuildDir().getAbsolutePath() + "/generated/sources/gaming/java");

        sourceSets.getByName("main").getJava().srcDir(flipper);

//        addToIdeaSources(project, flipper, MAIN_GENERATED);

        val mostWonder = new JavaSourceSetWrapper(project, MAIN_GENERATED);
        mostWonder.add(flipper);
    }
}