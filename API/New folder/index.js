const express = require('express');
const path = require('path');
const app = express()
const port = 3005


// Static Files
app.use(express.static('public'));

// Example for other olders
// app.use('/css', express.static(__dirname + 'public/css'))

// sendFile will go here
app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname, '/html/index.html'));
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})
