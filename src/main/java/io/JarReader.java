package io;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarReader {

    private static void parseJar() throws IOException {
        String jarName = "/tmp/test.jar";
        JarFile jarFile = new JarFile(jarName);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String fileName = entry.getName();

            System.out.println("==============");
            System.out.println(fileName);
            if (!fileName.endsWith("/")) {
                System.out.println(IOUtils.toString(jarFile.getInputStream(entry)));
            }
            System.out.println("==============");
        }
    }

    private static void extractJar() throws Exception {
        String jarName = "/opt/wsadmin/apps/hello-app.ear";
        JarFile jarFile = new JarFile(jarName);
        JarEntry entry = jarFile.getJarEntry("hello-app.war");
        InputStream is = jarFile.getInputStream(entry);
        String outputFile = "/tmp/testtest/hello-app-001.war";
        File file = new File(outputFile);
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new Exception();
        }
        IOUtils.copy(is, new FileOutputStream(outputFile));
    }

    public static void main(String[] args) throws Exception {
        extractJar();
        new File("").deleteOnExit();
    }
}
