package com.basdxz.qtjambitoolsgradleplugin.dependencies;

import groovy.lang.Closure;
import lombok.*;
import org.gradle.api.artifacts.Dependency;

import java.util.Optional;

public interface IDependencyConfiguration {
    Optional<Dependency> add(@NonNull Object dependencyNotation);

    Optional<Dependency> add(@NonNull Object dependencyNotation, @NonNull Closure<?> closure);
}
