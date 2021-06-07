1- Clone the repository
	•git clone https://github.com/nadeenay/APT_Project.git
2- Ecxuete Database
	• Install MySQL
	• Execute Project.sql Script on MySQL Workbench        

3- Change Databse Connection
    String url = "jdbc:mysql://localhost:3306/project";
    String username = "root";
    String password = "1234";                               // Enter your MySQL server Password
    Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println("Oops, Error!");
        }
    }

4- Running       
	•run Demo.java then Enter the number of Threads you want
	•run Index.java 
	•npm start
	•Go to http://localhost:1212/ in your browser  