package com.github.basdxz.qtjavelin.source.impl;

import com.github.basdxz.qtjavelin.source.ISourceType;
import com.github.basdxz.qtjavelin.source.SourceSetWrapper;
import lombok.*;
import org.gradle.api.Project;
import org.gradle.api.file.SourceDirectorySet;

import java.util.HashMap;
import java.util.Map;

@Getter
public class JavaSourceSetWrapper implements SourceSetWrapper {
    protected final static Map<Project, Map<ISourceType, SourceSetWrapper>> sourceSetWrappers = new HashMap<>();

    protected final Project project;
    protected final ISourceType sourceType;
    protected final SourceDirectorySet sourceDirectorySet;

    protected JavaSourceSetWrapper(@NonNull Project project, @NonNull ISourceType sourceType) {
        this.project = project;
        this.sourceType = sourceType;
        sourceDirectorySet = initSourceDirectorySet();
    }

    @Override
    public SourceDirectorySet initSourceDirectorySet() {
        return initSourceSet().getJava();
    }

    public static SourceSetWrapper javaSourceSetWrapper(@NonNull Project project, @NonNull ISourceType sourceType) {
        return sourceSetWrappers.computeIfAbsent(project, x -> newSourceSetWrapperMap())
                                .computeIfAbsent(sourceType, x -> newJavaSourceSetWrapper(project, sourceType));
    }

    protected static Map<ISourceType, SourceSetWrapper> newSourceSetWrapperMap() {
        return new HashMap<>();
    }

    protected static SourceSetWrapper newJavaSourceSetWrapper(@NonNull Project project,
                                                              @NonNull ISourceType sourceType) {
        return new JavaSourceSetWrapper(project, sourceType);
    }
}
