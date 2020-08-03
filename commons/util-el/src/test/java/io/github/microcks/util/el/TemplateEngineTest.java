/*
 * Licensed to Laurent Broudoux (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.github.microcks.util.el;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is a test case for TemplateEngine class.
 * @author laurent
 */
public class TemplateEngineTest {

   @Test
   public void testSimpleTemplate() {
      // Prepare a string representing now().
      Calendar currentDate = Calendar.getInstance();// Assert formatting.
      int day = currentDate.get(Calendar.DAY_OF_MONTH);
      int month = currentDate.get(Calendar.MONTH);
      int year = currentDate.get(Calendar.YEAR);
      String dateString = (day < 10 ? "0" + day : day) + "/"
            + (++month < 10 ? "0" + month : month) + "/" + year;

      // Execute simple template calling now() and request.body function.
      EvaluableRequest request = new EvaluableRequest("hello world!", new String[]{"name", "Laurent"});

      TemplateEngine engine = TemplateEngineFactory.getTemplateEngine();
      engine.getContext().setVariable("request", request);

      String result = engine.getValue("Today is {{ now(dd/MM/yyyy) }} and {{ request.body }}");

      assertEquals("Today is " + dateString + " and hello world!", result);
   }

   @Test
   public void testContextlessTemplate() {
      String template = "{\"signedAt\": \"{{ now() }}\", \"fullName\": \"Laurent Broudoux\", \"email\": \"laurent@microcks.io\", \"age\": 41} \n";

      TemplateEngine engine = TemplateEngineFactory.getTemplateEngine();

      String content = null;
      try {
         content = engine.getValue(template);
         System.err.println("Content: " + content);
      } catch (Throwable t) {
         fail("Contextless template should not fail.");
      }
      assertTrue(content.startsWith("{\"signedAt\": \"1"));
   }
}
