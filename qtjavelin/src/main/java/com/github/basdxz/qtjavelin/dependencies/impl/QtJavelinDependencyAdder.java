package com.github.basdxz.qtjavelin.dependencies.impl;

import com.github.basdxz.qtjavelin.dependencies.DependencyAdder;
import com.github.basdxz.qtjavelin.dependencies.IDependencyConfiguration;
import com.github.basdxz.qtjavelin.extension.IQtJavelinExtension;
import com.github.basdxz.qtjavelin.extension.impl.QtJavelinExtension;
import lombok.*;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;
import static org.gradle.api.plugins.JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME;

@NoArgsConstructor(access = PROTECTED)
public class QtJavelinDependencyAdder implements DependencyAdder {
    protected static final String QT_JAMBI_GROUP = "io.qtjambi";
    protected static final String QT_JAMBI = "qtjambi";
    protected static final String QT_JAMBI_NATIVE = "qtjambi-native";
    protected static final String QT_UIC = "qtjambi-uic";
    protected static final String QT_UIC_NATIVE = "qtjambi-uic-native";
    protected static final DependencyAdder INSTANCE = new QtJavelinDependencyAdder();

    @Override
    public void addDependencies(@NonNull Project project) {
        dependencies(QtJavelinExtension.qtJavelinExtension(project)).forEach(newDependencyConfiguration(project)::add);
    }

    protected List<String> dependencies(@NonNull IQtJavelinExtension qtjavelinExtension) {
        val version = qtjavelinExtension.getVersion();
        val osQualifier = qtjavelinExtension.getOsQualifier();
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

    public static DependencyAdder qtJavelinDependencyAdder() {
        return INSTANCE;
    }
}
