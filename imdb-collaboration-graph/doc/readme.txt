# GRAPH CREATION
Plain-text DB: http://www.imdb.com/interfaces

Covert the DB from plain-text to SQL:
python imdbpy2sql.py -d imdb-plain -u mysql://root:password@localhost/imdb

Create the list of arcs from the SQL DB:
python sql2arcs

Create the graph with Webgraph library
java it.unimi.dsi.webgraph.ScatteredArcsASCIIGraph -L -S imdbgraph < results/arcslists/imdbgrapharcslistfromdb.txt

# PAGE RANK
Calculate the page rank (with power method):
java -Xmx2G it.unimi.dsi.law.rank.PageRankPowerMethod results/graph/imdbgraph results/pageranks/imdbgraphrankpow
[this command creates results/pageranks/imdbgraphrankpow.ranks]

Write page rank for every node on results/pageranks/imdbgraphrankpow.res:
java WriteRanks results/pageranks/imdbgraphrankpow.ranks

Order results/pageranks/imdbgraphrankpow.res by page rank and write the results in results/pageranks/imdbgraphrankpow.ord:
java imdb.PageRankSort results/pageranks/imdbgraphrankpow.res

# DEGREE DISTRIBUTION
Export the ordered by node ID list of arcs:
java it.unimi.dsi.webgraph.ArcListASCIIGraph -g BVGraph results/graph/imdbgraph results/arcslists/imdbgrapharcslist

Calculate the degree distribution:
awk '{print $1}' results/arcslists/imdbgrapharcslist | uniq -c  | awk '{print $1}' | sort -n |  uniq -c | awk '{print $2, $1}' > results/dd/imdbgraphdd.txt

Plot the degree distribution:
python dd.py

# CLUSTERING

# ASSORTATIVITY


#
Given a node ID return the correspondent name:
java -Xmx2G imdbNode2sql [int]