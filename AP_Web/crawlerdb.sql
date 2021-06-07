create database crawlerdb
use crawlerdb ;
create table seeds
( 
url varchar(1000)
);

drop table seeds;
create table compacted
( 
word varchar(16383)
);
 insert into seeds values 
("https://www.google.com/search?q=how+to+make+each+run+resume+the+last+one+in+java"),
("https://www.quora.com/"),
("https://stackoverflow.com/questions/16758346/how-pause-and-then-resume-a-thread"),
  ("https://www.youm7.com/"),
   ("https://en.wikipedia.org/wiki/COVID-19"),
    ("https://www.amnesty.org/en/"),
   ("https://twitter.com"),
   ('https://www.islamweb.net/ar/'),
   ("https://www.semrush.com/website/amazon.com/?utm_source=blog&utm_medium=lp&utm_campaign=en_top_100_websites_20201217"),
 ("https://www.instagram.com/"),
("https://www.linkedin.com/feed/"),
("https://visualstudio.microsoft.com/thank-you-downloading-visual-studio/?sku=Community&rel=16"),
("https://eg.indeed.com/?r=us"),
("https://www.pinterest.com/"),
  ("https://www.quora.com/"),
("https://www.chase.com/"),
('https://www.similarweb.com/website/cimalight.io/'),
('https://maktoob.yahoo.com/?p=us'),
('https://www.islamweb.net/ar/'),
('https://www.javatpoint.com/'),
('https://www.vogella.com/tutorials/MySQLJava/article.html');