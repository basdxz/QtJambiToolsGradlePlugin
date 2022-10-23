package com.basdxz.qtjambitoolsgradleplugin.util;

import lombok.*;
import lombok.experimental.*;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.util.HashMap;

import static com.basdxz.qtjambitoolsgradleplugin.util.Constants.JAVA_PLUGIN_ID;

@UtilityClass
public final class GradleUtil {
    public static void ensureJavaPluginPresent(@NonNull Project project) {
        if (!isPluginPresent(project, JAVA_PLUGIN_ID))
            throw new IllegalStateException("Java Plugin is missing, which qtjambitools requires to work.");
    }

    public static boolean isPluginPresent(@NonNull Project project, @NonNull String id) {
        return project.getPlugins().hasPlugin(id);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Task> T newTask(@NonNull Project project, @NonNull String name, @NonNull Class<T> type) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("type", type);
        return (T) project.task(map, name);
    }
}
