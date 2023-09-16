import { pool } from "./index.js"

export async function insertPerson(query) {
    try {
        const result = await pool.query(query)
        if (result.rowCount) return 201
    } catch (error) {
        if (error.constraint === 'pessoas_apelido_key') return 422
        console.log('insertError: ', error.message)
        return 400
    } 
}