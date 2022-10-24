package com.basdxz.qtjambitools.dependencies.impl;

import com.basdxz.qtjambitools.dependencies.IDependencyConfiguration;
import com.basdxz.qtjambitools.extension.IQtJambiExtension;
import com.basdxz.qtjambitools.extension.impl.QtJambiExtension;
import lombok.*;
import lombok.experimental.*;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;

import static org.gradle.api.plugins.JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME;

@UtilityClass
public final class QTJambiDependencyAdder {
    private static final String QT_JAMBI_GROUP = "io.qtjambi";
    private static final String QT_JAMBI = "qtjambi";
    private static final String QT_JAMBI_NATIVE = "qtjambi-native";
    private static final String QT_UIC = "qtjambi-uic";
    private static final String QT_UIC_NATIVE = "qtjambi-uic-native";

    public static void addQTJambiDependencies(@NonNull Project project) {
        dependencies(QtJambiExtension.qtJambiExtension(project)).forEach(newDependencyConfiguration(project)::add);
    }

    private static List<String> dependencies(@NonNull IQtJambiExtension qtJambiExtension) {
        val version = qtJambiExtension.version();
        val osQualifier = qtJambiExtension.osQualifier();
        val dependencies = new ArrayList<String>();
        dependencies.add("%s:%s:%s".formatted(QT_JAMBI_GROUP, QT_JAMBI, version));
        dependencies.add("%s:%s-%s:%s".formatted(QT_JAMBI_GROUP, QT_JAMBI_NATIVE, osQualifier, version));
        dependencies.add("%s:%s:%s".formatted(QT_JAMBI_GROUP, QT_UIC, version));
        dependencies.add("%s:%s-%s:%s".formatted(QT_JAMBI_GROUP, QT_UIC_NATIVE, osQualifier, version));
        return dependencies;
    }

    private static IDependencyConfiguration newDependencyConfiguration(@NonNull Project project) {
        return new DependencyConfiguration(project, IMPLEMENTATION_CONFIGURATION_NAME);
    }
}
