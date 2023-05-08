/*
 *
 *
 *  *
 *  * MIT License
 *  * Copyright (c) 2023 Surendiran Selvam
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *  *  ** Mar 05, 2023
 *  *  * @author Surendiran Selvam
 *  *  * @version 1.0
 *  *  * @since 1.0
 *  *
 *
 *
 */

package com.demo.utils;

import com.demo.constants.FrameworkConstants;
import com.demo.customexceptions.FrameworkException;
import com.demo.enums.ConfigProperties;
import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@UtilityClass
public  class PropertyUtils {

  private static Properties property;

  // Singleton design pattern
  public static Properties getInstance() {
    if (property == null) {
      property = new Properties();
    }
    return property;
  }

  static void loadProperties() {
    try (FileInputStream input = new FileInputStream(FrameworkConstants.CONFIG_PROPERTIES_PATH)) {
      getInstance().load(input);
    } catch (IOException e) {
      throw new FrameworkException("IOException occurred while loading Property file in the specified path");
    }
  }

  public static String getPropertyValue(ConfigProperties key) {
    loadProperties();
    if (Objects.isNull(property.getProperty(key.name().toLowerCase()))) {
      throw new FrameworkException("Property name - " + key + " is not found. Please check the config.properties");
    }
    return property.getProperty(key.name().toLowerCase());
  }
}
