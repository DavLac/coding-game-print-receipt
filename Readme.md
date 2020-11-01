# Unattended Programming Test

## Scenario

The application emulates a cash register that accepts a list of items purchased as arguments and prints a receipt to the standard output. An inexperienced software developer has written the application. Please refactor it into more maintainable, testable and object-oriented code.

The cash register currently prints an individual line on the receipt for every item purchased.  There is a requirement that it should be modified to print only a single line for each type of product purchased. This line should display the number of items, the type of product and the total cost of the items.

So the register currently outputs the following receipt for the input ``SERVICING VALET VALET``:
```
1 SERVICING : £100.00
1 VALET : £25.00
1 VALET : £25.00
GRAND TOTAL : £150.00
```
The application should be modified to output this: 
```
1 SERVICING @ £100.00 : £100.00
2 VALET @ £25.00 : £50.00
GRAND TOTAL : £150.00 
```
Please bear in mind all the best practices you would normally employ when producing "done" production code: 

- A well factored object oriented domain model
- Testing
- Refactoring
- Clean code

Try not to get caught up too much in any one of the above to the exclusion of the others.  There is no perfect solution, so try to show us what you can do in all areas to produce a high quality piece of code.

You may use ANY classes available in the standard Java J2SE library, but you should not use any other library apart from JUnit.  The project is set up as a simple Maven project, which may get yoentryu up and running faster, though feel free to ignore it entirely if you don't care for Maven - so long as JUnit 4 and the src/main/java and src/test/java folders are on your classpath everything should be fine. 
