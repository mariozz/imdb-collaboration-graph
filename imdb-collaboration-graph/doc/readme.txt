Converti da plain text a SQL:
python imdbpy2sql.py -d imdb-plain -u mysql://root:password@localhost/imdb

Crea la lista di archi a partire dal DB
python sql2arcs

Crea il grafo
java it.unimi.dsi.webgraph.ScatteredArcsASCIIGraph -L -S imdbgraph < results.txt

Calcola il page rank (con il metodo delle potenze):
java -Xmx2G it.unimi.dsi.law.rank.PageRankPowerMethod imdbgraph imdbrankpow

Scrive i page rank su imdbgraphrankpow.res:
java WriteRanks imdbgraphrankpow.ranks

Ordina i risultati e li scrive in imdbgraphrankpow.ord:
java imdb.PageRankSort imdbgraphrankpow.res

Converti nodeId to name:
java -Xmx2G imdbNode2sql [int]

Esporta la lista ordinata degli archi:
java it.unimi.dsi.webgraph.ArcListASCIIGraph -g BVGraph imdbgraph imdbgrapharcslist

Calcola la degree distribution:
awk '{print $1}' imdbgrapharcslist | uniq -c  | awk '{print $1}' | sort -n |  uniq -c | awk '{print $2, $1}' > imdbgraphdd.txt 