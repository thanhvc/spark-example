/*
 * Copyright (C) 2009-2016 Eway Company.
 * 
 * All rights reserved.
 *
 */
package json;

import java.util.Map;

/**
 * Created by The Eway Company
 * Author : Eway
 *          eway@eway.vn
 * Sep 7, 2016  
 */
public class Deserializer {
  /**
   * Resolve the variables of type ${my.var} for the current context which is composed of the system
   * properties and the portal container settings
   * 
   * @param input the input value
   * @return the resolve value
   */
  public static String resolveVariables(String input) {
    return resolveVariables(input, null);
  }

  /**
   * Resolve the variables of type ${my.var} for the current context which is composed of the system
   * properties, the portal container settings and the given settings
   * 
   * @param input the input value
   * @param props a set of parameters to add for the variable resolution
   * @return the resolve value
   */
  public static String resolveVariables(String input, Map<String, Object> props) {
    final int NORMAL = 0;
    final int SEEN_DOLLAR = 1;
    final int IN_BRACKET = 2;
    if (input == null)
      return input;
    
    char[] chars = input.toCharArray();
    StringBuilder buffer = new StringBuilder();
    boolean properties = false;
    int state = NORMAL;
    int start = 0;
    for (int i = 0; i < chars.length; ++i) {
      char c = chars[i];
      if (c == '$' && state != IN_BRACKET)
        state = SEEN_DOLLAR;
      else if (c == '{' && state == SEEN_DOLLAR) {
        buffer.append(input.substring(start, i - 1));
        state = IN_BRACKET;
        start = i - 1;
      } else if (state == SEEN_DOLLAR)
        state = NORMAL;
      else if (c == '}' && state == IN_BRACKET) {
        if (start + 2 == i) {
          buffer.append("${}");
        } else {
          String value = null;
          String key = input.substring(start + 2, i);
          String defaultValue = null;
          int index = key.indexOf(':');
          if (index > -1) {
            defaultValue = key.substring(index + 1);
            key = key.substring(0, index);
          }

          if (props != null) {
            // Some parameters have been given thus we need to check inside first
            Object oValue = props.get(key);
            value = oValue == null ? null : oValue.toString();
          }
          if (value == null) {
            // No value could be found so far, thus we try to get it from the
            // system properties
            value = System.getProperty(key);
          }
          if (value == null && defaultValue != null) {
            value = defaultValue;
          }
          if (value != null) {
            properties = true;
            buffer.append(value);
          }
        }
        start = i + 1;
        state = NORMAL;
      }
    }
    if (properties == false)
      return input;
    if (start != chars.length)
      buffer.append(input.substring(start, chars.length));
    return buffer.toString();

  }
  
}
