package org.china.dailiyun.lpsolve;

import java.awt.EventQueue;

abstract class SwingWorkerTask implements Runnable
{
   /**
      Place your task in this method. Be sure to call doUpdate(), not update(), to show the 
      update after each unit of work.
   */
   public abstract void work() throws InterruptedException;

   /**
      Override this method for UI operations before work commences.
   */
   public void init() {}
   /**
      Override this method for UI operations after each unit or work.
   */
   public void update() {}
   /**
      Override this method for UI operations after work is completed.
   */
   public void finish() {}

   private void doInit()
   {
      EventQueue.invokeLater(new
         Runnable()
         {
            public void run() { init(); }
         });    
   }

   /**
      Call this method from work() to show the update after each unit of work.
   */
   protected final void doUpdate()
   {
      if (done) return;
      EventQueue.invokeLater(new
         Runnable()
         {
            public void run() { update(); }
         });      
   }

   private void doFinish()
   {
      EventQueue.invokeLater(new
         Runnable()
         {
            public void run() { finish(); }
         });
   }

   public final void run()
   {      
      doInit();
      try
      {
         done = false;
         work();
      }
      catch (InterruptedException ex)
      {
      }
      finally
      {
         done = true;
         doFinish();
      }
   }

   private boolean done;
}
