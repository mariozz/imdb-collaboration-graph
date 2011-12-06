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

Approximate the pagerank (with 8 decimal values):
python pagerankapprox.py

Calculate the pagerank distribution:
awk '{print $1}' imdbgraphrankpow.res.approx | sort -g | uniq -c | awk '{print $2, $1}' > imdbgraphrankpow.distribution


# DEGREE DISTRIBUTION
Export the ordered by node ID list of arcs:
java it.unimi.dsi.webgraph.ArcListASCIIGraph -g BVGraph results/graph/imdbgraph results/arcslists/imdbgrapharcslist

Calculate the degree distribution:
awk '{print $1}' results/arcslists/imdbgrapharcslist | uniq -c  | awk '{print $1}' | sort -n |  uniq -c | awk '{print $2, $1}' > results/dd/imdbgraphdd.txt

Plot the degree distribution:
python dd.py


# CLUSTERING
Clustering has been done on a subgraph of 250000 nodes (results/metis/metis250000.graph). This subgraph has been obtained taking the firsts 250000 nodes of the graph constructed using webgraph.
java -Xmx2G imdb.Node2Metis
[output] : results/metis/metis250000.graph

Create 10 clusters with metis executing 5 refinement iteration of the algorithm 
gpmetis -niter=5 metis250000.graph 10
[output]: results/metis/metis250000.graph.part.10

Create a file for every cluster. For every node add a line to the file of the cluster it belongs. Each line contains contains [nodeID, name, pagerank score]
java imdb.Clusters

Sort the clusters according to the pagerank
java imdb.ClustersSort

Every cluster is characterized by mean of geographical areas where actors belongs or movie genre.
See: results/clusters/clusters10/clusters.info.txt


# ASSORTATIVITY
Calculate the assortativity for every node and the assortativity coefficient (the assortativity of x is the sum of neighbors ranks' / # of neighbors of x; the assortativity coefficient is the sum of nodes' assortativities / # of nodes).
java -Xmx2G imdb Assortativity
[output]: results/assortativity/assortativity --> the file contains the assortativity coefficient for each node
[output]: results/assortativity/assortativityCoeff --> the file contains the mean assortativity coefficient value


# OTHERS
Given a node ID return the correspondent name:
java -Xmx2G imdbNode2sql [int]