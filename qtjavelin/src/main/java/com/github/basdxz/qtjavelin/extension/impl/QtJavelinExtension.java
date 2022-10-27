package com.github.basdxz.qtjavelin.extension.impl;

import com.github.basdxz.qtjavelin.extension.IQtJavelinExtension;
import com.github.basdxz.qtjavelin.extension.QtJavelinOSQualifier;
import lombok.*;
import lombok.experimental.*;
import org.gradle.api.Project;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Accessors(fluent = false, chain = false)
public class QtJavelinExtension implements IQtJavelinExtension {
    public static final String DEFAULT_INPUT_DIRECTORY = "qt_widgets";
    public static final String DEFAULT_PACKAGE_PATH = "compiledgui";
    public static final String DEFAULT_OUTPUT_DIRECTORY = "qtjavelin";

    protected static final Map<Project, IQtJavelinExtension> EXTENSIONS = new HashMap<>();

    protected final Project project;

    protected String version = "6.4.0";
    protected String osQualifier = QtJavelinOSQualifier.currentQualifier().orElse(null);
    protected String defaultInputDirectory;
    protected String defaultPackagePath;
    protected String defaultOutputDirectory;

    public QtJavelinExtension(@NonNull Project project) {
        this.project = project;
        defaultInputDirectory = project.getProjectDir() + "/" + DEFAULT_INPUT_DIRECTORY;
        defaultPackagePath = DEFAULT_PACKAGE_PATH;
        defaultOutputDirectory = project.getBuildDir().getAbsolutePath() +
                                 "/generated/sources/" + DEFAULT_OUTPUT_DIRECTORY;
    }

    public static IQtJavelinExtension qtJavelinExtension(@NonNull Project project) {
        return EXTENSIONS.computeIfAbsent(project, QtJavelinExtension::newQtJavelinExtension);
    }

    protected static IQtJavelinExtension newQtJavelinExtension(@NonNull Project project) {
        return project.getExtensions().create(EXTENSION_NAME, QtJavelinExtension.class, project);
    }
}