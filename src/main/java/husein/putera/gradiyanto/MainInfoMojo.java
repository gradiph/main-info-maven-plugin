/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package husein.putera.gradiyanto;

import java.time.LocalDateTime;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 *
 * @author Gradi
 */
@Mojo(name = "init", defaultPhase = LifecyclePhase.PACKAGE)
public class MainInfoMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;
    
    @Parameter(property = "mainClass")
    String mainClass;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String mainInfo = getMainInfo();
        getLog().info(mainInfo);
        writeToMainClass(mainInfo);
    }

    protected String getMainInfo() {
        String packageName = project.getName();
        String version = project.getVersion();
        String buildDate = LocalDateTime.now().toString();
        return "Package: " + packageName + "\n"
                + "Version: " + version + "\n"
                + "Build Date: " + buildDate;
    }
    
    protected void writeToMainClass(String mainInfo) {
        setMainClass();
    }
    
    protected void setMainClass() {
        if (project.getProperties().getProperty(Constants.PROP_EXEC_MAIN_CLASS) != null) {
            mainClass = project.getProperties().getProperty(Constants.PROP_EXEC_MAIN_CLASS);
        }
        getLog().info("Main Class: " + mainClass);
    }
}
