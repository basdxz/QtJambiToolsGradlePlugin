package com.basdxz.qtjambitoolsgradleplugin.dependencies.impl;

import lombok.*;
import lombok.experimental.*;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;

import static com.basdxz.qtjambitoolsgradleplugin.configuration.impl.QtJambiExtension.qtJambiExtension;

@UtilityClass
public final class QTJambiDependencyAdder {
    private static final String IMPLEMENTATION_CONFIGURATION_NAME = "implementation";
    private static final String QT_JAMBI_GROUP = "io.qtjambi";
    private static final String QT_JAMBI = "qtjambi";
    private static final String QT_JAMBI_NATIVE = "qtjambi-native";
    private static final String QT_UIC = "qtjambi-uic";
    private static final String QT_UIC_NATIVE = "qtjambi-uic-native";

    public static void addQTJambiDependencies(@NonNull Project project) {
        val implementationConfiguration = new DependencyConfiguration(project, IMPLEMENTATION_CONFIGURATION_NAME);
        dependencies().forEach(implementationConfiguration::add);
    }

    private static List<String> dependencies() {
        val configuration = qtJambiExtension();
        val version = configuration.version();
        val osQualifier = configuration.osQualifier();
        val dependencies = new ArrayList<String>();
        dependencies.add("%s:%s:%s".formatted(QT_JAMBI_GROUP, QT_JAMBI, version));
        dependencies.add("%s:%s-%s:%s".formatted(QT_JAMBI_GROUP, QT_JAMBI_NATIVE, osQualifier, version));
        dependencies.add("%s:%s:%s".formatted(QT_JAMBI_GROUP, QT_UIC, version));
        dependencies.add("%s:%s-%s:%s".formatted(QT_JAMBI_GROUP, QT_UIC_NATIVE, osQualifier, version));
        return dependencies;
    }
}
