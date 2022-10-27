package com.github.basdxz.qtjavelin.util;

import lombok.*;
import lombok.experimental.*;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.util.HashMap;


@UtilityClass
public final class GradleUtil {
    @SuppressWarnings("unchecked")
    public static <T extends Task> T newTask(@NonNull Project project, @NonNull String name, @NonNull Class<T> type) {
        val map = new HashMap<String, Object>();
        map.put("type", type);
        return (T) project.task(map, name);
    }
}
