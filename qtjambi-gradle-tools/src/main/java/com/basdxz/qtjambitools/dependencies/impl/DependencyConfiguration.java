package com.basdxz.qtjambitools.dependencies.impl;

import com.basdxz.qtjambitools.dependencies.IDependencyConfiguration;
import groovy.lang.Closure;
import lombok.*;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.UnknownConfigurationException;
import org.gradle.api.artifacts.dsl.DependencyHandler;

import java.util.Optional;

public class DependencyConfiguration implements IDependencyConfiguration {
    protected final DependencyHandler dependencyHandler;
    protected final String configurationName;

    public DependencyConfiguration(@NonNull Project project, @NonNull String configurationName) {
        this.dependencyHandler = project.getDependencies();
        this.configurationName = configurationName;
        ensureDependencyConfigurationExists(project);
    }

    @Override
    public Optional<Dependency> add(@NonNull Object dependencyNotation) {
        return Optional.ofNullable(dependencyHandler.add(configurationName, dependencyNotation));
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public Optional<Dependency> add(@NonNull Object dependencyNotation, @NonNull Closure<?> closure) {
        return Optional.ofNullable(dependencyHandler.add(configurationName, dependencyNotation, closure));
    }

    protected void ensureDependencyConfigurationExists(@NonNull Project project) {
        try {
            project.getConfigurations().getByName(configurationName).getDependencies();
        } catch (UnknownConfigurationException e) {
            if ("implementation".equals(configurationName))
                throw new RuntimeException("Are you missing the `java` plugin in your build.gradle?", e);
            throw e;
        }
    }
}
