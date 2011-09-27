Converti da plain text a SQL:
python imdbpy2sql.py -d imdb-plain -u mysql://root:password@localhost/imdb

Crea la lista di archi a partire dal DB
python sql2arcs

Crea il grafo
java it.unimi.dsi.webgraph.ScatteredArcsASCIIGraph -L -S imdbgraph < results.txt

Calcola il page rank (con il metodo delle potenze):
java -Xmx2G it.unimi.dsi.law.rank.PageRankPowerMethod imdbgraph imdbrank

Stampa i page rank:
java PrintRanks imdbrank.ranks