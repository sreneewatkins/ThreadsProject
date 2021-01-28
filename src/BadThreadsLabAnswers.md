####1. Compile and run BadThreads.java:     
        DONE

####2. The application should print out "Mares do eat oats."

- Is it guaranteed to always do this?
   Yes

- If not, why not?
  I think as long as the sleep for corrector thread < sleep for the main thread
  that Mares DO EAT will print.


####3. Would it help to change the parameters of the two invocations of Sleep?

    If you change the sleep parameters so that corrector thread
    sleep > main sleep thread then Mared DO NOT EAT will 
    print to console.

####4. How would you guarantee that all changes to message will be visible in the main thread?

    Using synchronized methods and join