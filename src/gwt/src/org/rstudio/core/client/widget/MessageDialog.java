/*
 * MessageDialog.java
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
package org.rstudio.core.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.rstudio.core.client.theme.res.ThemeResources;
import org.rstudio.core.client.widget.images.MessageDialogImages;

public class MessageDialog extends ModalDialogBase
{
   public final static int INFO = 1;
   public final static int WARNING = 2;
   public final static int ERROR = 3;
   public final static int QUESTION = 4;
   public final static int POPUP_BLOCKED = 0;

   public MessageDialog(int type, String caption, String message)
   {
      this(type, caption, labelForMessage(message));
   }
   
   public MessageDialog(int type, String caption, Widget message)
   {
      type_ = type;
      setText(caption);
      messageWidget_ = message;
      setButtonAlignment(HasHorizontalAlignment.ALIGN_CENTER);
   }

   public ThemedButton addButton(String label,
                                 final Operation operation,
                                 boolean isDefault,
                                 boolean isCancel)
   {
      ThemedButton button = new ThemedButton(label, new ClickHandler()
      {
         public void onClick(ClickEvent event)
         {
            if (operation != null)
               operation.execute();
            closeDialog();
         }
      });

      addButton(button, isDefault, isCancel);

      return button;
   }

   public ThemedButton addButton(String label,
                                 final ProgressOperation operation,
                                 boolean isDefault,
                                 boolean isCancel)
   {
      if (operation != null && progress_ == null)
         progress_ = addProgressIndicator();

      ThemedButton button = new ThemedButton(label, new ClickHandler()
      {
         public void onClick(ClickEvent event)
         {
            if (operation != null)
               operation.execute(progress_);
            else
               closeDialog();
         }
      });

      addButton(button, isDefault, isCancel);

      return button;
   }

   private void addButton(ThemedButton button,
                          boolean isDefault,
                          boolean isCancel)
   {
      if (isDefault)
         addOkButton(button);
      else if (isCancel)
         addCancelButton(button);
      else
         addButton(button);
   }

   @Override
   protected Widget createMainWidget()
   {
      HorizontalPanel horizontalPanel = new HorizontalPanel();
      horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

      // add image
      MessageDialogImages images = MessageDialogImages.INSTANCE;
      Image image = null;
      switch(type_)
      {
      case INFO:
         image = new Image(images.dialog_info());
         break;
      case WARNING:
         image = new Image(images.dialog_warning());
         break;
      case ERROR:
         image = new Image(images.dialog_error());
         break;
      case QUESTION:
         image = new Image(images.dialog_question());
         break;
      case POPUP_BLOCKED:
         image = new Image(images.dialog_popup_blocked());
         break;
      }
      horizontalPanel.add(image);

      // add message widget
      horizontalPanel.add(messageWidget_);
      
      return horizontalPanel;
   }
   
   public static Label labelForMessage(String message)
   {
      Label label = new MultiLineLabel(message);
      label.setStylePrimaryName(
                     ThemeResources.INSTANCE.themeStyles().dialogMessage());
      return label;
   }
    
   private int type_ ;
   private Widget messageWidget_ ;
   private ProgressIndicator progress_ ;
}
