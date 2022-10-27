package com.github.basdxz.qtjavelin.source;

import lombok.*;
import org.gradle.api.Project;
import org.gradle.api.file.SourceDirectorySet;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.plugins.ide.idea.model.IdeaModel;

import java.io.File;
import java.io.IOException;

import static com.github.basdxz.qtjavelin.util.Constants.IDEA_PLUGIN_ID;
import static com.github.basdxz.qtjavelin.util.Constants.SOURCE_SET_PROPERTY;
import static com.github.basdxz.qtjavelin.util.FileUtil.createDirectory;

public interface SourceSetWrapper {
    Project project();

    ISourceType sourceType();

    SourceDirectorySet sourceDirectorySet();

    SourceDirectorySet initSourceDirectorySet();

    default SourceSet initSourceSet() {
        return initSourceSetContainer(project()).getByName(sourceType().sourceSet());
    }

    default SourceSetContainer initSourceSetContainer(@NonNull Project project) {
        return (SourceSetContainer) project.getProperties().get(SOURCE_SET_PROPERTY);
    }

    default void add(@NonNull File sourceDirectory) {
        createSourceDirectory(sourceDirectory);
        addToProject(sourceDirectory);
        addToIDEA(sourceDirectory);
    }

    default void createSourceDirectory(@NonNull File sourceDirectory) {
        try {
            createDirectory(sourceDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create source directory: ", e);
        }
    }

    default void addToProject(@NonNull File sourceDirectory) {
        sourceDirectorySet().srcDir(sourceDirectory);
    }

    default void addToIDEA(@NonNull File sourceDirectory) {
        val project = project();
        val sourceType = sourceType();
        project.getPlugins().withId(IDEA_PLUGIN_ID, plugin -> {
            val ideaModel = project.getExtensions().findByType(IdeaModel.class);
            if (ideaModel == null)
                return;
            val ideaModule = ideaModel.getModule();
            if (sourceType.test()) {
                ideaModule.getTestSourceDirs().add(sourceDirectory);
            } else {
                ideaModule.getSourceDirs().add(sourceDirectory);
            }
            if (sourceType.generated())
                ideaModule.getGeneratedSourceDirs().add(sourceDirectory);
        });
    }
}
