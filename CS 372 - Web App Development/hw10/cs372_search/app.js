// Required libraries
// Also required is ejs
const express = require("express");
const bodyParser = require("body-parser");
const request = require("request");
const mongoose = require('mongoose')

const app = express();

// MongoDB setup
const searchSchema = mongoose.Schema({userSearch: String, 
                                      date: String})
mongoose.connect('mongodb://127.0.0.1:27017/CS372_HW10', { useNewUrlParser: true, useUnifiedTopology: true })

let UserSearch = mongoose.model('UserSearch', searchSchema)

// Constants
//const apiKey = "INPUT Open Weather API key here";
const googleURL = "https://www.google.com/search?q=";
const port = 3000;

// App configurations
app.set("port", process.env.PORT || 8080);
app.set("view engine", "ejs");
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", (req, res) => 
{
  console.log("Rendering page");
  res.render("cs372_search", { weather: null, error: null, history: null, numberOfSearches: null });
});

app.post("/", function (req, res) 
{
  // Get button values for logic
  let getSearchButton = req.body.searchButton;
  let getHistoryButton = req.body.historyButton;

  let currentDate = new Date().toLocaleString();

  // Get search query and format it to be usable in API call
  let searchQuery = req.body.searchBox;
  const stringArray = searchQuery.split(/(\s)/);
  let cityArray = stringArray.slice(2);
  let city = cityArray.join("");

  console.log("\nSearch is: " + searchQuery);
  console.log("First term is: " + stringArray[0]);
  console.log("City is: " + city + "\n");

 /* If SEARCH was clicked */
  if (getSearchButton == "search")
  {
    // Save search query to database
    let userSearch = new UserSearch({ userSearch: searchQuery, 
                                      date: currentDate }) 
    userSearch.save((err, results) => 
    {
      if (err) 
      {
        console.error(err)
        process.exit(1)
      } 

      else 
      { console.log('Saved query: ' + '"' + results.userSearch + '"') }
    })

    // Logic for weather query or google redirect
    let url = `http://api.openweathermap.org/data/2.5/weather?q=${city}&units=imperial&appid=${apiKey}`;

    request(url, function (err, response, body) 
    {
      // Unexpected error occurred
      if (err) 
      {
        res.render("cs372_search", {
          weather: null,
          error: "Error, please try again",
          history: null,
          numberOfSearches: null
        });
      } 

      else 
      {
        // If first element of search is "Temp:"
        if (stringArray[0] == "Temp:") 
        {
          // Get Weather API data
          let weather = JSON.parse(body);
          if (weather.main == undefined) 
          {
            res.render("cs372_search", {
              weather: null,
              error: "City not found, please try again",
              history: null,
              numberOfSearches: null
            });
          }
        
          // Print current weather data
          else 
          {
            let weatherText = `It's ${weather.main.temp} degrees in ${weather.name}`;
            res.render("cs372_search", {
               weather: weatherText, 
               error: null,
               history: null,
               numberOfSearches: null
              });
          }
        }
      
        // First element of search is NOT "Temp:"
        else 
        {
          // Google Search
          res.redirect(googleURL + searchQuery);
        }
      }
    });

 } // End SEARCH button logic

 /** If HISTORY was clicked */
 else if (getHistoryButton == "history")
 {
    weather = null;
    error = null;

    // User input a search query
    // if blank searchQuery = "" which searches all items
    let query = UserSearch.find({userSearch: {$regex: ".*" + searchQuery + ".*", $options: "<i>"}});
    query.exec(function(err, searches)
    {
      let displaySearchHistory = "";
      let numberOfSearches = 0;
      
      if(err)
      { return console.log(err); }
    
      else
      {
        searches.forEach(function(search)
        {
          console.log("Query: " + search.userSearch);
          displaySearchHistory += "Query: " + search.userSearch + ", Date: " + search.date + "\n";
          numberOfSearches++;
        })

        res.render("cs372_search", {
            history: displaySearchHistory,
            numberOfSearches: numberOfSearches
        });
      }
    })
 }

 /** Neither button clicked */
 else
 { console.log("Some error happened") }

}); // End POST logic

app.get("/history", function (req, res){
  console.log("Rendering history page");
  res.render("cs372_search", { weather: null, error: null, history: null, numberOfSearches: null });
})

// Delete with DELETE request
app.delete("/history", function (req, res)
{
  UserSearch.deleteMany({}, function (err) {
    if(err) 
      {console.log(err);}

    console.log("History successfully deleted");
  });

  res.end();

}); // End DELETE logic

// Delete with button
app.post("/history", function (req, res)
{
  UserSearch.deleteMany({}, function (err) {
    if(err) 
      {console.log(err);}

    console.log("History successfully deleted");
  });

  res.render("cs372_search", {
    weather: null, 
    error: "History has been deleted!", 
    history: null, 
    numberOfSearches: null 
  });

}); // End DELETE logic

app.listen(port);
console.log("Listening on port " + port);
