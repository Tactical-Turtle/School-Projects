// Required libraries
// Also required is ejs
const express = require("express");
const bodyParser = require("body-parser");
const request = require("request");

const app = express();

// Constants
//const apiKey = "INPUT Open Weather API key here";
const googleURL = "https://www.google.com/search?q=";
const port = 3000;

// App configurations
app.set("port", process.env.PORT || 8080);
app.set("view engine", "ejs");
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", (req, res) => {
  console.log("Rendering page");
  res.render("cs372_search", { weather: null, error: null });
});

app.post("/", function (req, res) {

  // Get search query and format it to be usable in API call
  let searchQuery = req.body.searchBox;
  const stringArray = searchQuery.split(/(\s)/);
  let cityArray = stringArray.slice(2);
  let city = cityArray.join("");

  console.log("\nSearch is: " + searchQuery);
  console.log("First term is: " + stringArray[0]);
  console.log("City is: " + city);

  let url = `http://api.openweathermap.org/data/2.5/weather?q=${city}&units=imperial&appid=${apiKey}`;

  request(url, function (err, response, body) {
    // Unexpected error occurred
    if (err) 
    {
      res.render("cs372_search", {
        weather: null,
        error: "Error, please try again",
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
          });
        }

        // Print current weather data
        else 
        {
          let weatherText = `It's ${weather.main.temp} degrees in ${weather.name}`;
          res.render("cs372_search", {
               weather: weatherText, error: null }
               );
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
});

app.listen(port);
console.log("Listening on port " + port);
