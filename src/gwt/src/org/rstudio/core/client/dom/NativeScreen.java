/*
 * NativeScreen.java
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

package org.rstudio.core.client.dom;

import com.google.gwt.core.client.JavaScriptObject;

public class NativeScreen extends JavaScriptObject
{
   protected NativeScreen() {}
   
   public static native NativeScreen get() /*-{
      return $wnd.screen;
   }-*/;
   
   public final native int getAvailHeight() /*-{
      return this.availHeight;
   }-*/;

   public final native int getAvailWidth() /*-{
      return this.availWidth;
   }-*/;
}
