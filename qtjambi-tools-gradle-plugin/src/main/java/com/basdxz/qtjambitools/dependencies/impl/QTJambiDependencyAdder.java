package com.basdxz.qtjambitools.dependencies.impl;

import com.basdxz.qtjambitools.dependencies.DependencyAdder;
import com.basdxz.qtjambitools.dependencies.IDependencyConfiguration;
import com.basdxz.qtjambitools.extension.IQtJambiExtension;
import com.basdxz.qtjambitools.extension.impl.QtJambiExtension;
import lombok.*;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;
import static org.gradle.api.plugins.JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME;

@NoArgsConstructor(access = PROTECTED)
public class QTJambiDependencyAdder implements DependencyAdder {
    protected static final String QT_JAMBI_GROUP = "io.qtjambi";
    protected static final String QT_JAMBI = "qtjambi";
    protected static final String QT_JAMBI_NATIVE = "qtjambi-native";
    protected static final String QT_UIC = "qtjambi-uic";
    protected static final String QT_UIC_NATIVE = "qtjambi-uic-native";
    protected static final DependencyAdder INSTANCE = new QTJambiDependencyAdder();

    @Override
    public void addDependencies(@NonNull Project project) {
        dependencies(QtJambiExtension.qtJambiExtension(project)).forEach(newDependencyConfiguration(project)::add);
    }

    protected List<String> dependencies(@NonNull IQtJambiExtension qtJambiExtension) {
        val version = qtJambiExtension.getVersion();
        val osQualifier = qtJambiExtension.getOsQualifier();
        val dependencies = new ArrayList<String>();
        dependencies.add("%s:%s:%s".formatted(QT_JAMBI_GROUP, QT_JAMBI, version));
        dependencies.add("%s:%s-%s:%s".formatted(QT_JAMBI_GROUP, QT_JAMBI_NATIVE, osQualifier, version));
        dependencies.add("%s:%s:%s".formatted(QT_JAMBI_GROUP, QT_UIC, version));
        dependencies.add("%s:%s-%s:%s".formatted(QT_JAMBI_GROUP, QT_UIC_NATIVE, osQualifier, version));
        return dependencies;
    }

    protected IDependencyConfiguration newDependencyConfiguration(@NonNull Project project) {
        return new DependencyConfiguration(project, IMPLEMENTATION_CONFIGURATION_NAME);
    }

    public static DependencyAdder qtJambiDependencyAdder() {
        return INSTANCE;
    }
}
