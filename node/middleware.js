import { Router } from "express";
import { parse } from "date-fns";
import { createPerson, findPersonById, findPersonByQuery } from "./api.js";

export const router = Router();

const validateParams = (req, res, next) => {
    const regex = /^[0-9a-fA-F]{8}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{4}\b-[0-9a-fA-F]{12}$/gm
    if (!String(req.params.id).match(regex)) return res.status(404).send()
    next()
}

const validateBody = (req, res, next) => {
    const { apelido, nome, nascimento, stack } = req.body;
    if (isNaN(parse(nascimento, 'yyyy-MM-dd', new Date()))) return res.status(400).send()
    if (!apelido || !nome || !nascimento) return res.status(422).send()
    if (apelido.length > 32) return res.status(400).send()
    if (typeof nome !== 'string' || nome.length > 100) return res.status(400).send()
    if (!Array.isArray(stack) || stack.some(element => typeof element !== 'string' || element.length > 32)) return res.status(400).send()
    next()
}

const validateQuery = (req, res, next) => {
    const { t } = req.query;
    if(!t) return res.status(400).send()
    next()
}

router.post('/', validateBody, createPerson);

router.get('/:id', validateParams, findPersonById)

router.get('/', validateQuery, findPersonByQuery)
