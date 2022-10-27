package com.github.basdxz.qtjavelin.extension.impl;

import com.github.basdxz.qtjavelin.extension.IQtJambiExtension;
import com.github.basdxz.qtjavelin.extension.QTJambiOSQualifier;
import lombok.*;
import lombok.experimental.*;
import org.gradle.api.Project;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Accessors(fluent = false, chain = false)
public class QtJambiExtension implements IQtJambiExtension {
    public static final String DEFAULT_INPUT_DIRECTORY = "qt_widgets";
    public static final String DEFAULT_PACKAGE_PATH = "compiledgui";
    public static final String DEFAULT_OUTPUT_DIRECTORY = "qtjambi";

    protected static final Map<Project, IQtJambiExtension> EXTENSIONS = new HashMap<>();

    protected final Project project;

    protected String version = "6.4.0";
    protected String osQualifier = QTJambiOSQualifier.currentQualifier().orElse(null);
    protected String defaultInputDirectory;
    protected String defaultPackagePath;
    protected String defaultOutputDirectory;

    public QtJambiExtension(@NonNull Project project) {
        this.project = project;
        defaultInputDirectory = project.getProjectDir() + "/" + DEFAULT_INPUT_DIRECTORY;
        defaultPackagePath = DEFAULT_PACKAGE_PATH;
        defaultOutputDirectory = project.getBuildDir().getAbsolutePath() +
                                 "/generated/sources/" + DEFAULT_OUTPUT_DIRECTORY;
    }

    public static IQtJambiExtension qtJambiExtension(@NonNull Project project) {
        return EXTENSIONS.computeIfAbsent(project, QtJambiExtension::newQTJambiExtension);
    }

    protected static IQtJambiExtension newQTJambiExtension(@NonNull Project project) {
        return project.getExtensions().create(EXTENSION_NAME, QtJambiExtension.class, project);
    }
}