# progetto Fabrick

Ho cercato di fattorizzare il più possibile il codice.
Le chiamate Rest del AccountController passano attraverso un layer di servizio annotato con @Service che va a fare le chiamate delle API fabrick.
Con il restTemplate, ho avuto un po' di problemi di deserializzazione, pertciò ho usato le chiamate Http dirette.
Tutte le chiamate sono loggate tramite un interceptor che recupera la request e la response
tecnologie utilizzate : java, Springboot, spring core, spring data, jpa, flyway, mysql
