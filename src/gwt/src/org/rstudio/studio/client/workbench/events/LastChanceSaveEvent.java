/*
 * LastChanceSaveEvent.java
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
package org.rstudio.studio.client.workbench.events;

import com.google.gwt.event.shared.GwtEvent;
import org.rstudio.core.client.Barrier;
import org.rstudio.core.client.Barrier.Token;

/**
 * This event provides components a chance to save their state before the
 * session is terminated. It applies only for Desktop mode, since we can't
 * stop web browser windows from closing.
 *
 * To use, subscribe to this event on the global event bus, and when it
 * fires call acquire() to get a token. The process will not quit until
 * all acquired tokens are released by calling release().
 *
 * IMPORTANT NOTE: You MUST call release on the token eventually--this
 * mechanism is not intended to provide quit cancellation functionality,
 * but only to momentarily delay quitting while state is saved! 
 */
public class LastChanceSaveEvent extends GwtEvent<LastChanceSaveHandler>
{
   public static final Type<LastChanceSaveHandler> TYPE = new Type<LastChanceSaveHandler>();

   public LastChanceSaveEvent(Barrier barrier)
   {
      barrier_ = barrier;
   }

   /**
    * Delay quitting until the returned barrier token is released.
    */
   public Token acquire()
   {
      return barrier_.acquire();
   }

   @Override
   public Type<LastChanceSaveHandler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(LastChanceSaveHandler handler)
   {
      handler.onLastChanceSave(this);
   }

   private final Barrier barrier_;
}
