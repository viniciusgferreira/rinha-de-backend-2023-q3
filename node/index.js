import express from 'express';
import pg from 'pg';
import { router } from './middleware.js';
import { countPeople } from './api.js';

const app = express();
const PORT = process.env.APP_PORT | 3000;


app.use(express.json());
app.use('/pessoas', router);
app.use('/contagem-pessoas', countPeople);

//DB

export const pool = new pg.Pool({
    host: 'localhost',
    user: 'postgres',
    password: 'postgres',
    database: 'rinhadb',
    max: 40,
    idleTimeoutMillis: 0,
    connectionTimeoutMillis: 60000,
  })
  

app.listen(PORT, () => console.log(`app is listening on port ${PORT}`));