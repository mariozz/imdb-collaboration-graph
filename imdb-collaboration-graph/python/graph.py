import networkx as nx
import matplotlib.pyplot as plt

G=nx.read_adjlist("/Users/mario/imdb-collaboration-graph/imdb-collaboration-graph/results/viz/adjlist.txt")
nx.draw(G)
plt.show()


