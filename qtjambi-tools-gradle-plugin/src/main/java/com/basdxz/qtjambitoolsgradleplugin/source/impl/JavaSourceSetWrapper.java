package com.basdxz.qtjambitoolsgradleplugin.source.impl;

import com.basdxz.qtjambitoolsgradleplugin.source.ISourceType;
import com.basdxz.qtjambitoolsgradleplugin.source.SourceSetWrapper;
import lombok.*;
import org.gradle.api.Project;
import org.gradle.api.file.SourceDirectorySet;

@Getter
public class JavaSourceSetWrapper implements SourceSetWrapper {
    protected final Project project;
    protected final ISourceType sourceType;
    protected final SourceDirectorySet sourceDirectorySet;

    public JavaSourceSetWrapper(@NonNull Project project, @NonNull ISourceType sourceType) {
        this.project = project;
        this.sourceType = sourceType;
        sourceDirectorySet = initSourceDirectorySet();
    }

    @Override
    public SourceDirectorySet initSourceDirectorySet() {
        return initSourceSet().getJava();
    }
}
