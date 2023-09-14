import express from 'express';

const app = express();
const PORT = 3000;
app.get('/', (req, res) => {
   console.log(req.headers)
    res.status(200).send(`Hello\n`)
})
app.listen(PORT, () => console.log(`app is listening on port ${PORT}`));