package com.basdxz.qtjambitoolsgradleplugin;

import com.basdxz.qtjambitoolsgradleplugin.dependencies.impl.QTJambiDependencyAdder;
import lombok.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.SourceSetContainer;

import java.io.File;

import static com.basdxz.qtjambitoolsgradleplugin.configuration.impl.QtJambiExtension.addQTJambiExtension;
import static com.basdxz.qtjambitoolsgradleplugin.source.impl.SourceType.MAIN_GENERATED;
import static com.basdxz.qtjambitoolsgradleplugin.util.GradleUtil.addToIdeaSources;
import static com.basdxz.qtjambitoolsgradleplugin.util.GradleUtil.newTask;

public class GradlePluginTemplate implements Plugin<Project> {
    @Override
    public void apply(@NonNull Project project) {


        addQTJambiExtension(project);
        QTJambiDependencyAdder.addQTJambiDependencies(project);


        val task = newTask(project, "hello", TestTask.class);

        task.theWord = "farting!!";


        SourceSetContainer sourceSets = (SourceSetContainer)
                project.getProperties().get("sourceSets");
//        sourceSets.getByName("main").getResources()
//                  .srcDir("");


//        System.out.println();

        val flipper = new File(project.getBuildDir().getAbsolutePath() + "/generated/sources/qtjambi/java");

//        flipper.mkdirs();
//        System.out.println(flipper);
//        System.out.println(flipper.exists());
//        System.out.println(flipper.isDirectory());
        sourceSets.getByName("main").getJava().srcDir(flipper);

        System.out.println(project.getPlugins().hasPlugin("java"));

        addToIdeaSources(project, flipper, MAIN_GENERATED);
    }
}