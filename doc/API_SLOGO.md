1. Parsing needs to take place to convert user input into Java code so that it can be executed. We a variable scope, to store all referenced variables, interpreter, set of valid commands.
2. Result of parsing is set of commands to be executed by the simulation. So the simulator is the destination
3. All errors are runtime, so they are simply displayed, when execution is impossible. This is necessary, in order to make learning curve shallow
4. They receive data, when they are created, the data is passed into them after being calculated by the executioner,  which takes in user inpput and combines it with variables, available in a given scope
5. They read in state changes and apply them to the objects and visualize it.


for i->10
    for j->10
        fd 5
        fd 2