package com.basdxz.qtjambitoolsgradleplugin.util;

import com.basdxz.qtjambitoolsgradleplugin.source.ISourceType;
import lombok.*;
import lombok.experimental.*;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.plugins.ide.idea.GenerateIdeaModule;
import org.gradle.plugins.ide.idea.model.IdeaModel;

import java.io.File;
import java.util.HashMap;

@UtilityClass
public final class GradleUtil {
    private static final String JAVA_PLUGIN_ID = "java";
    private static final String IDEA_PLUGIN_ID = "idea";

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

    public static void addToIdeaSources(@NonNull Project project, @NonNull File file, @NonNull ISourceType sourceType) {
        project.getPlugins().withId(IDEA_PLUGIN_ID, plugin -> {
            val model = project.getExtensions().findByType(IdeaModel.class);
            if (model == null)
                return;
            val module = model.getModule();
            if (sourceType.test()) {
                module.getTestSourceDirs().add(file);
            } else {
                module.getSourceDirs().add(file);
            }
            if (sourceType.generated())
                module.getGeneratedSourceDirs().add(file);
            project.getTasks()
                   .withType(GenerateIdeaModule.class)
                   .forEach(generateIdeaModule -> generateIdeaModule.doFirst(task -> file.mkdirs()));
        });
    }
}
