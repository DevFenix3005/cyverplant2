const PROXY_CONFIG = [
    {
        context: ['/api', '/oauth2', '/login'],
        target: 'http://localhost:8081',
        secure: true,
        logLevel: 'debug'
    }
]

module.exports = PROXY_CONFIG;
