People: Dan Sun & Chenning Yu

We extracted **duplicated lines in the UI** into a private method, thus eliminating detectable duplicated code.

Also, we refactored the code of Reader about **getBackgroundColor and getPenColor**, make them share the same piece of codes and extract the same code into **getColorProperty**, to avoid duplication.

We refactored this code because it is within the scope we worked on. Other refactor will be performed outside the discussion.

