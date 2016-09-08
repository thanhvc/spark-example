/*
 * Copyright (C) 2009-2016 Eway Company.
 * 
 * All rights reserved.
 *
 */
package groovy.compile;

import java.lang.reflect.Constructor;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;

/**
 * Created by The Eway Company
 * Author : Eway
 *          eway@eway.vn
 * Aug 30, 2016  
 */
public class TestGroovyShell {
    
    public static void main(String[] args) {
        testGroovy2();
    }
    
    public static void testGroovy1() throws CompilationFailedException {
        Binding binding = new Binding();
        binding.setVariable("foo", new Integer(2));
        GroovyShell shell = new GroovyShell(binding);

        Object value = shell.evaluate("println 'Hello World!'; x = 123; return foo * 10");
        assert value.equals(new Integer(20));
        assert binding.getVariable("x").equals(new Integer(123));
    }
    
    public static void testGroovy2() throws CompilationFailedException {
        try {
            Class<?> groovyClass = GroovyScriptTools.classLoad("src/main/resources/groovy/Foo.groovy");
            Constructor<?> constructor = groovyClass.getConstructor(String.class, Boolean.class, Boolean.class);
            IFoo foo = (IFoo) constructor.newInstance("offer001", false, true);
            System.out.println(foo.run(new Integer(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
