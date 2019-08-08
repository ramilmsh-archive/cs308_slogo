
## What changes that have been made to the APIs? 

The change of Interpreter API: a new method of **changeLanguage(String)** has beeen added to the API.

The change of Controller API: for the APIs shown to the front-end, there added **runCommand(String command)** and **changeLanguage(String language)**; for the APIs shown to the back-end, there added **updateUserMethods(Map<String, String> methods)** and **updateVariables(Map<String, String> variables)**.

The change of Executor API: two methods have been added to the Executor class: **addObserver(Observer obs)** and **removeObserver(Observer obs)**. This is because multiple turtles mean that observation has to change every time turtles are added or removed. To make this easier we created an intermediary observer class that handles all of the other observers, and the Executor class controls that class. Also, the getter methods in the Observer subsection of the Executor API were removed in favor of passing back a map of changes. 

The change of MainUI API: two methods has been added to API: **updateVariables(Map<String, Double> variables)** and **updateUserMethods(String[] methods)**.

## If those changes are major or minor (justify your distinction based on how much they affected your team mate's code)

For now, the changes are minor, because the team has just implemented the basic requirement of implementation, and these APIs are the extended details of the initial designed structure of codes. As a result, these APIs did not disobey the initial design, which means they are minor changes.

On the whole, only minor method changes were made; the overall structure of the code remained true to the original API.

## If those changes are for better or worse (if for the worse, is there a way to improve it or was the original API overly optimistic)

For now, these changes are sticked with the original design, but they are just implementation details to the original design, so it cannot be judged by worse or better, because there is no other design decisions to compare.

## If your foresee any significant changes coming in the next few days as you finish the project (based on your experience and the fact that you now know all the features to be implemented)

There will be APIs for MainUI to set the pen color and turtle graph, and APIs for Interpreter to load variables and user-defined methods from workspace, if the design is not changed.