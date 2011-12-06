#! /usr/bin/python
import matplotlib.pyplot as plt
import sys

f = open("../results/pageranks/imdbgraphrankpow.distribution", "r")
matrice = f.readlines()
gradi=[]
nodi=[]
for j in matrice:
	tokens = j.split()
	gradi = gradi +[tokens[0]]
	nodi = nodi + [tokens[1]]
	
plt.plot(gradi, nodi, 'rx')
plt.xscale('log')
plt.yscale('log')



plt.grid(True)
plt.xlabel('PageRank')
plt.ylabel('# of Persons')
plt.title('IMDb collaboration graph - PageRank Distribution')
plt.show()
