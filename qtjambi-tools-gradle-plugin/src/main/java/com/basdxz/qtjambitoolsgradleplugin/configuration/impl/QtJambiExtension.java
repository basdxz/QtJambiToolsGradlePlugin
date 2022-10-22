package com.basdxz.qtjambitoolsgradleplugin.configuration.impl;

import com.basdxz.qtjambitoolsgradleplugin.configuration.IQtJambiExtension;
import com.basdxz.qtjambitoolsgradleplugin.configuration.QTJambiOSQualifier;
import lombok.*;
import org.gradle.api.Project;

@Getter
public class QtJambiExtension implements IQtJambiExtension {
    protected static final String EXTENSION_NAME = "qtJambi";
    protected static final IQtJambiExtension INSTANCE = new QtJambiExtension();

    protected String version = "6.4.0";
    protected String osQualifier = QTJambiOSQualifier.currentQualifier().orElse(null);

    public static void addQTJambiExtension(@NonNull Project project) {
        project.getExtensions().add(EXTENSION_NAME, INSTANCE);
    }

    public static IQtJambiExtension qtJambiExtension() {
        return INSTANCE;
    }
}