import express from 'express';
import compression from 'compression';
import path from 'path';
import helmet from 'helmet';
import { readFileSync, statSync, lstatSync } from 'fs';

//Create an app
const app = express();

// Global middlewares
app.use(compression());
app.use(helmet({
    contentSecurityPolicy: {
        directives: {
            defaultSrc: ["'self'"],
            imgSrc: [
                "'self'",
                "data:",
                "blob:"
            ],
            childSrc: [
                "'self'",
                "blob:"
            ],
            mediaSrc: [
                "'self'",

            ],
            styleSrc: [
                "'self'",  
            ],
            scriptSrc: [
                "'self'",

            ],
            connectSrc: [
                "'self'",
                "http://localhost:8080",
                "http://localhost:8081"
            ],
            workerSrc: [
                "'self'",
                "blob:"
            ]
        },
    },
})
);

app.disable('x-powered-by');

app.use(express.static('.'));

app.get('/*', function (req, res, next) {

    const basePath = `.`;
    const path = `${basePath}/index.html`;
    const ip = req.headers['x-forwarded-for'] || req.socket.remoteAddress 

    const file = readFileSync(path, "utf8");
    res.status(200).send(file);
});

app.get('*', function (req, res) {
    console.log('Not found:', req.url);
    res.redirect('/');
});

//Listen port
const PORT = 3000;
app.listen(PORT);
console.log(`Running on port ${PORT}`);
