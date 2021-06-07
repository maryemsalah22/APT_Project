const route = require('express').Router;
app.get('/demo1',(req,res)=>
{
    const pageNum = req.params.page.valueOf() ;
    var q = "SELECT DISTINCT URL , descriptions ,title " +
    " FROM Frequencies  f,URLs u" +
    " WHERE word = '" + userquery + "' AND u.noOfDocument = f.noOfDocument " +
    " ORDER BY TF DESC ;";
    // conn.query(q, function(err, result, fields) {
    //     console.log(result);
    //     if (err) console.log("there is an error" + err);
    //     result = result.slice(10,20);
        console.log("here");
        res.render('demo1');
  //  });

});

exports.module ={route} ;