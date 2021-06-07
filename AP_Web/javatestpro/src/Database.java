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
    String password = "1234";
    Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println("Oops, Error!");
        }
    }

    public void updateURLs(int i, String title, String description) {

        // FileReader fr = new FileReader("URLs.txt");
        // BufferedReader br = new BufferedReader(fr); // creates a buffering character
        // input stream
        // StringBuffer sb = new StringBuffer(); // constructs a string buffer with no
        // characters
        // String line;
        String line;
        try (Stream<String> lines = Files.lines(Paths.get("URLs.txt"))) {
            line = lines.skip(i).findFirst().get();
            try {
                i++;
                String query = "update urls set title= ? ,descriptions=? where noOfDocument=?;";
                PreparedStatement stat = connection.prepareStatement(query);
                stat.setString(1,title);
                stat.setString(2, description);
                stat.setInt(3, i);
                stat.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }



    public void insertURLs() {
        try {
            try {

            } catch (Exception e) {
                System.out.println(e);
            }
            FileReader fr = new FileReader("URLs.txt");
            BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
            StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                try {PreparedStatement stat = connection.prepareStatement("insert into URLs values (" + i + ",'" + line
                   + "','TITLE','DESCRIPTION');");
                    stat.executeUpdate();
                    i++;
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

    public void deleteURLs() {
        try {

            PreparedStatement stat = connection.prepareStatement("delete from URLs ;");
            stat.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

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