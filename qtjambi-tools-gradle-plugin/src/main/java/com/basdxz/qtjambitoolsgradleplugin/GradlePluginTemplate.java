package com.basdxz.qtjambitoolsgradleplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.util.HashMap;

public class GradlePluginTemplate implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        GreetingPluginExtension extension = project.getExtensions()
                                                   .create("greeting", GreetingPluginExtension.class);
//        project.task("hello")
//               .doLast(task -> {
//                   System.out.println(
//                           "Hello, " + extension.greeter());
//                   System.out.println(
//                           "I have a message for You: " + extension.message());
//               });

        makeTask(project, "firstFunItem", TestTask.class);
    }

    @SuppressWarnings("unchecked")
    public <T extends Task> T makeTask(Project proj, String name, Class<T> type) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("type", type);
        return (T) proj.task(map, name);
    }
}