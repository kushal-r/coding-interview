# SOLID Design Principles

SOLID design principles tell us how to arrange our data structures and functions
into classes, and how those classes should be interconnected. Any collection of
functions and data can be classified as a class.

The main goal of the SOLID principles is to build a software that can:
- Tolerate Change
- Readable
- Are basis of components that can be used in multiple software.

#### SOLID

---
**S**RP - **Single Responsibility** states that a class should have only one reason to change.
Like functions should be responsible for one and only responsibility. SRP says that the class should 
interact only with one and only actor using that particular class.<br/>
Example: Employee class has three methods calculatePay(), reportHours(), and save()
- The calculatePay() method is specified by the accounting department, which reports to the CFO
- The reportHours() method is specified and used by the human resources department, which
  reports to the COO.
- The save() method is specified by the database administrators (DBAs), who report to the CTO.

By putting all the three methods in a single class the developers coupled the three actors with a single class.
Now suppose two of these methods are using the same method to calculate overtime. Changing the overtime logic will be affecting
both these actors. <br/>
In case if multiple developers are working on the same core logic, merging the code changes are error prone.<br/>
The obvious solution to overcome all this is to keep the Employee data separate and let the actors access and transform the data through some facade.

---
**O**CP - **Open Closed Principle** states that systems should be designed in such a way that new code should be added to change the existing behavior 
rather than modifying existing code.
Single Responsibility and Inversion of Control plays a major role in achieving this.<br/>
Example: Imagine, that we have a system that displays a financial summary on a web page. The
data on the page is scrollable, and negative numbers are rendered in red.
Now imagine that the stakeholders ask that this same information be turned into a report to be printed
on a black-and-white printer. The report should be properly paginated, with appropriate page
headers, page footers, and column labels. Negative numbers should be surrounded by parentheses.<br/>
To achieve OCP, we have to split the functionality into minute components following Single Responsibility.
Keeping the changes minimal we add the newer functionality, using IoC.

---
**L**SP - **Liskov's Substitution Principle**  The principle states that derived classes can substitute the 
parent or base class. LSP is a guide to use object inheritance.
---
**I**SP - **Interface Segregation Principle** states that the system should never enforce users to implement interfaces that 
are not relevant to him.<br/>
Example: Say we have a class OPS with methods [op1,op2,op3], suppose User1 -> op1 ,User2 -> op2, now suppose 
OPS class op2 function implementation changes, and hence the User1 has also have to be compiled and re deployed
as User1 is inadvertently dependent on op2.
This can be easily solved by using splitting the OPS methods into multiple interfaces. And let User1, User2 implement individual interfaces.
---
**D**IP - **Dependency Inversion Principle** tells us that most flexible systems are those in which 
source code dependencies refer only to abstractions rather than implementations.
In Java it means that the use, import, and include statements
should refer only to source modules containing interfaces, abstract classes, or some other kind of
abstract declaration. Nothing concrete should be depended on.<br/>
Every change in an abstract interface corresponds to a change to its concrete implementation. But the reverse is not
always true.
  








 

