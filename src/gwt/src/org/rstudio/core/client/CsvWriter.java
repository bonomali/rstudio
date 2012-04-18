/*
 * CsvWriter.java
 *
 * Copyright (C) 2009-12 by RStudio, Inc.
 *
 * This program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.core.client;

public class CsvWriter
{
   public void writeValue(String value)
   {
      onValueBegin();

      if (!value.contains("\n") && !value.contains(","))
         sw.append(value);
      else
         sw.append('"' + value.replace("\"", "\"\"") + '"');
   }

   private void onValueBegin()
   {
      if (firstValue)
         firstValue = false;
      else
         sw.append(',');
   }

   public void endLine()
   {
      sw.append("\n");
      firstValue = true;
   }

   public String getValue()
   {
      return sw.toString();
   }

   @Override
   public String toString()
   {
      return getValue();
   }

   private boolean firstValue = true;
   private final StringBuffer sw = new StringBuffer();
}
