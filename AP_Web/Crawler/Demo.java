import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Demo
{

    public static void main(String [] args ) throws SQLException {
        Queue<String>URLS_Queue=new  LinkedList<String>();
        Set<String>Compacted = new HashSet<String>();
        int Current_size=0;
        System.out.print("hello");
       /* URLS_Queue.add("https://stackoverflow.com/questions/12526979/jsoup-get-all-links-from-a-page#");
        URLS_Queue.add("https://www.youtube.com/watch?v=TCd8QIS-2KI");
        URLS_Queue.add("https://codinginflow.com/");
        URLS_Queue.add("https://stackoverflow.com/questions/12526979/jsoup-get-all-links-from-a-page#");
        URLS_Queue.add("https://www.w3schools.com/xml/ajax_xmlhttprequest_response.asp");
        URLS_Queue.add("https://en.wikipedia.org/?fbclid=IwAR1WEcp_LYFywH7-4DVnGBr1iDw3jv6JnddSrs_HLt_lrOcNaxJvSS0gN8I");
        URLS_Queue.add("https://en.wikipedia.org/wiki/Main_Page?fbclid=IwAR0ld83aydtRImfh4DuZ03-7TuWYOxqZ07sNChjtiFnbcfkctnKTMYzDXAA#searchInput");
        URLS_Queue.add("https://www.youtube.com/watch?v=hxFuaZL9pRY");
        URLS_Queue.add("https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/onreadystatechange");
        URLS_Queue.add("https://developer.mozilla.org/en-US/docs/Web/API/DOMString");
        URLS_Queue.add("https://developer.mozilla.org/en-US/docs/Web/API/Document/readystatechange_event");
        URLS_Queue.add("https://www.w3schools.com/xml/ajax_xmlhttprequest_response.asp");
        URLS_Queue.add("https://stackoverflow.com/questions/15145929/404-error-with-ajax-request");
        URLS_Queue.add("https://brantu.com/eg-en/yoxo-front-vent-jeans-dfrg-0035b1-meduim-wash-p?utm_source=facebook&utm_medium=cpc&utm_term=ROIHUNTER&utm_campaign=ROIH_Prospecting_CatalogSale_25Feb_ROAS--LAL-CompleteRegistration-TopCVR&utm_content=at1!25695435&utm_id=at1!25695435&fbclid=IwAR3dEMkpAQxUYvqN91WhqMnD9EfYZTOawxjKHWQ-ktXUgdrtqLoi3vNGCD4");
        URLS_Queue.add("https://www.facebook.com/mawaqifghariba0/");
        URLS_Queue.add("https://www.google.com/search?q=how+to+make+each+run+resume+the+last+one+in+java&sxsrf=ALeKk01j3NPE2BzDswEHpIjVZNabZ5EetQ%3A1622785191687&source=hp&ei=p7y5YN6KJ4ieUM2FkegL&iflsig=AINFCbYAAAAAYLnKtypSBPaCIIwPRzfI5Gyu_QZ6fC_0&oq=how+to+make+each+run+resume+the+last+one+&gs_lcp=Cgdnd3Mtd2l6EAMYADIFCCEQoAEyBQghEKABOgQIIxAnOgQIABBDOgUIABCRAjoECC4QQzoHCC4QsQMQQzoHCAAQsQMQQzoCCAA6BwgjELECECc6BAgAEAo6BggAEBYQHjoICCEQFhAdEB46BAghEAo6BwghEAoQoAE6BAghEBVQxQFYiYoCYLyWAmgEcAB4AIABiwSIAb08kgEKMC4zOC41LjUtMZgBAKABAaoBB2d3cy13aXo&sclient=gws-wiz");
        URLS_Queue.add("https://stackoverflow.com/questions/16758346/how-pause-and-then-resume-a-thread");
        URLS_Queue.add("https://www.youm7.com/");
        URLS_Queue.add("https://en.wikipedia.org/wiki/COVID-19");
        URLS_Queue.add("https://www.amnesty.org/en/");
        URLS_Queue.add("https://twitter.com/youm7");
        URLS_Queue.add("https://www.semrush.com/website/amazon.com/?utm_source=blog&utm_medium=lp&utm_campaign=en_top_100_websites_20201217");
        URLS_Queue.add("https://www.instagram.com/");
        URLS_Queue.add("https://www.linkedin.com/feed/");
        URLS_Queue.add("https://visualstudio.microsoft.com/thank-you-downloading-visual-studio/?sku=Community&rel=16");
        URLS_Queue.add("https://eg.indeed.com/?r=us");
        URLS_Queue.add("https://www.pinterest.com/");
        URLS_Queue.add("https://www.quora.com/");
        URLS_Queue.add("https://www.chase.com/");
        URLS_Queue.add("https://www.themuse.com/advice/every-basic-question-you-have-about-your-resume-skills-section-answered");
        //URL_Set.add("https://codinginflow.com/");*/
        ResultSet resultSet = null,rS =null;
        try {
            PreparedStatement preparedStatement = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crawlerdb","root", "1234");
            Statement st = conn.createStatement();
            preparedStatement = conn.prepareStatement( "SELECT * from seeds");
            resultSet = preparedStatement.executeQuery();
            preparedStatement = conn.prepareStatement("SELECT * from compacted");
            rS = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while(resultSet.next())
            URLS_Queue.add(resultSet.getString(1));

        for (String s:URLS_Queue) {
            System.out.println(s);
        }
        while(rS.next()) {
            Compacted.add(rS.getString(1));
            Current_size++;
        }

        Scanner myObj = new Scanner(System.in);
        int n =myObj.nextInt();
        crawler c = new crawler(URLS_Queue,Compacted,Current_size,n);
        Thread ths[] = new Thread[n];
        for(int i =0; i < n;i++)
            ths[i]=new Thread(c);

        for(int i =0; i < n;i++)
            ths[i].start();


    }
}

