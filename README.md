# SpringMVCFilmCRUD
## A full-stack application using a MySQL database
<img src="http://clipart-library.com/images/6ip5kEGKT.jpg" alt="film reel"/>

### Description
This is an application that will allows a users to read, create, delete and update information from a database. Currently the application is designed to read and modify information about fictitious films stored in a personal database. With a few tweaks to the source code you can supply your own database and have this application do the same for your information.

---

## Technologies Used
* Spring Tool Suite 4 - We switched to a new IDE optimized for working with our Spring enabled project.
* JDBC - using Object Relational Maping (ORM) class entities were created for each of the tables being interacted with. Each of those classes matched the database column name to the Java object field name. 
* Gradle - Used to manage our project dependencies.
* JavaServer Pages (JSPs) - Used to create dynamic content to be displayed to the user based on the objects created by their queries. Our DispatcherServlet handled the routing to our Controller, which supplied details of methods being called and adding objects to the model.
* JSTL - We used the core tag library in conjection with JSP Expression language to utilize the JavaBean properties when displaying specific aspects of our objects.

---

### How to run
Run on server from main project folder and open source path in browser. Application will be launced on AWS during the workday on Monday (3/8/2021).

---

### Lessons Learned
We learned to be very careful about ensuring that our RequestMapping calls were being passed the objects necessary to run with our DAO Implementation methods. On the jsp view where users could see the selected or created movie, they were also able to edit or delete that movie. Initially when creating the paths, we did not feed the current film object into the request, which didn't allow the DAO Implementation to access that specific object. This was a new way to trouble shoot for us since we had mainly ensured that methods worked within the console environment. 