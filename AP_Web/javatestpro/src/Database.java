import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;
import opennlp.tools.stemmer.PorterStemmer;

public class Database {
    String url = "jdbc:mysql://localhost:3306/project";
    String username = "root";
    String password = "1234";                               // Enter your MySQL server Password
    Connection connection;

    // Configuring Database Connection
    public Database() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println("Oops, Error!");
        }
    }
    // Inserting URLs into the Database
    public void insertURLs(int i,int end, String title, String description) {

        String line;
        try (Stream<String> lines = Files.lines(Paths.get("C/URLs.txt"))) {
            line = lines.skip(i).findFirst().get();
            try {
                i++;
                String query = "insert into urls values (?,?,?,?);";
                PreparedStatement stat = connection.prepareStatement(query);
                stat.setInt(1, i);
                stat.setString(2,line);
                stat.setString(3,title);
                stat.setString(4,description);
                stat.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        
        } catch (IOException e) {
            System.out.println(e);
        }

    }



    public void uptadeURLs(int i,String title,String description) {
        try {
            FileReader fr = new FileReader("C/URLs.txt");
            BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
            StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                try {
                    i++;
                    String query = "insert into urls values (?,?,?,?);";
                    PreparedStatement stat = connection.prepareStatement(query);
                    stat.setInt(1, i);
                    stat.setString(2,line);
                    stat.setString(3,title);
                    stat.setString(4,description);
                    stat.executeUpdate();
                    
                } catch (Exception e) {
                    System.out.println(e);
                }
                sb.append("\n");
            }
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Inserting Each word with its noOfDocument and TF
    public void insertFreqs(String word, int noOfDocument, int TF) {

        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            PreparedStatement stat = connection.prepareStatement(
                    "insert into Frequencies values ('" + word + "'," + noOfDocument + "," + TF + ");");
            stat.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Deleting all the URLs stored in Database
    public void deleteURLs() {
        try {

            PreparedStatement stat = connection.prepareStatement("delete from URLs ;");
            stat.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Deleting all the Words stored in Database
    public void deleteFrequencies() {
        try {

            PreparedStatement stat = connection.prepareStatement("delete from Frequencies ;");
            stat.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getSpecificURL(String word) {
        try {

            PreparedStatement stat = connection.prepareStatement(
                    "select URL from URLs inner join frequencies ON frequencies.noOfDocument = URLs.noOfDocument  and word = '"
                            + word + "';");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("URL"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}