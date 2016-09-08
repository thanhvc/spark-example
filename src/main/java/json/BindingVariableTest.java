/*
 * Copyright (C) 2009-2016 Eway Company.
 * 
 * All rights reserved.
 *
 */
package json;


/**
 * Created by The Eway Company
 * Author : Eway
 *          eway@eway.vn
 * Sep 7, 2016  
 */
public class BindingVariableTest {

  public static void main(String[] args) {

    //System.setProperty("x10.http.port", "1000");
    System.setProperty("x10.http.host", "www.vnexpress.net");

    String got = Deserializer.resolveVariables("done ${x10.http.port:2000} binding the value and host: ${x10.http.host:localhost}");
    
    System.out.println("Output = " + got);
  }

}
