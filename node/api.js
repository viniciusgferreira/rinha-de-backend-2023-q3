import { insertPerson } from "./database.js";
import { pool } from "./index.js"
import { v4 as uuidv4 } from 'uuid';

export const createPerson = async (req, res) => {
    console.log('count:', pool.totalCount)
    console.log('idle:', pool.idleCount)
    console.log('waiting:', pool.waitingCount)
    console.log('---------------------------------------')

    const { apelido, nome, nascimento, stack } = req.body;
    const id = uuidv4();
    const query = {
        name: 'insert-pessoa',
        text: 'INSERT INTO pessoas(id, apelido, nome, nascimento, stack) VALUES($1, $2, $3, $4, $5)',
        values: [id, apelido, nome, nascimento, stack]
    }
    
    const result = await insertPerson(query)
    if (result === 201) res.set('Location', `/pessoas/${id}`)
    return res.status(result).send()
}

export const findPersonById = async (req, res) => {
    const query = {
        name: 'findbyid',
        text: `SELECT
        id,
        apelido,
        nome,
        to_char(nascimento, 'YYYY-MM-DD') as nascimento,
        stack
    FROM
        pessoas
    WHERE "id" = $1;`,
    values: [req.params.id]
    }
    try {
        const result = await pool.query(query);
        if (!result.rowCount) return res.sendStatus(404)
        return res.status(200).send(result.rows[0]);
    } catch (error) {
        console.log('findbyid: ', error.message)
        return res.status(400).send()
    } 
}

export const findPersonByQuery = async (req, res) => {
    const { t } = req.query
    const query = {
        name: 'findbyquery',
        text: `select id, apelido, nome, to_char(nascimento, 'YYYY-MM-DD') as nascimento, stack ` 
        + `FROM pessoas WHERE searchable ILIKE $1 LIMIT 50`,
        values: [`%${t}%`]
    }
    try {
        const result = await pool.query(query);
        return res.status(200).send(result.rows)
    } catch (error) {
        console.log('findbyQUERY: ', error.message)
        return res.status(500).send()
    }
}

export const countPeople = async (req, res) => {
    const result = await pool.query('SELECT COUNT(*) FROM pessoas');
    return res.status(200).send(result.rows[0].count);
}