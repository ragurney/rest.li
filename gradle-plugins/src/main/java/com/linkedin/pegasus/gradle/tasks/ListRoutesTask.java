package com.linkedin.pegasus.gradle.tasks;

import com.linkedin.pegasus.gradle.PathingJarUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;
//import com.linkedin.restli.tools.clientgen.RestSpecParser;


@CacheableTask
public class ListRoutesTask extends DefaultTask {

  @TaskAction
  public void routes() {

    executeListRoutes();
  }

  private void executeListRoutes()
  {
    getProject().javaexec(javaExecSpec ->
    {
      javaExecSpec.setMain("com.linkedin.restli.tools.utils.ListRoutesTask");
      javaExecSpec.systemProperty("scala.usejavacp", "true");
    });
  }
}


