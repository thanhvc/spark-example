/*
 * Copyright (C) 2009-2016 Eway Company.
 * 
 * All rights reserved.
 *
 */
package groovy.compile;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

/**
 * Created by The Eway Company
 * Author : Eway
 *          eway@eway.vn
 * Aug 30, 2016  
 */
public class GroovyScriptTools {
    private static final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    public static void clearGroovyScriptCache() {
        groovyClassLoader.clearCache();
    }

    public static Class classLoad(String scriptPath) throws Exception {
        Class groovyClz = groovyClassLoader.parseClass(new File(scriptPath));
        return groovyClz;
    }

    public static GroovyObject getGroovyObj(String scriptPath) throws Exception {
        Class groovyClz = groovyClassLoader.parseClass(new File(scriptPath));
        GroovyObject groovyObject = (GroovyObject) groovyClz.newInstance();
        return groovyObject;
    }

}
