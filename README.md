Ci tengo ad iniziare questo readme con un enorme ringraziamento a **madbob** che grazie al lavoro già fatto per studiare l'API di Muoversi a Torino mi ha permesso di fare questo sviluppo in modo agevole:
https://github.com/madbob/gtt-pirate-api

Questo progetto nasce dalla mia esigenza del crearmi un backend personale per poter ottenere comodamente i dati dei prossimi passaggi dei bus nella città di Torino.
Attualmente il codice è ad una primissima versione funzionante ma da revisionare per migliorarne la pulizia, trovare eventuali bug e aggiungere ulteriori funzionalità.

Al momento sto ospitando per me il servizio su questo endpoint: 

http://simonelambiase.it:8080/api/stop?stopNumber=754&lineNumber=49&hours=12

Il servizio potrebbe andare offline per manutenzione sul server o aggiornamenti dello stesso, quindi non assicuro il funzionamento 24h 
L'unico parametro mandatorio è lo **stopNumber**, gli altri parametri sono facoltativi.

http://simonelambiase.it:8080/api/stop?stopNumber=754

Il codice è tranquillamente clonabile e lanciabile sulla propria macchina come pacchetto jar una volta buildato con Maven.

Qualsiasi consiglio o richiesta di collaborazione è bene accetta.
Questo è un progetto senza scopo di lucro.

Per qualsiasi contatto, curiosità o richiesta sono contattabile su telegram con il nome @slambiase1.

