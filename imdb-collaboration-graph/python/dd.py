#! /usr/bin/python
import matplotlib.pyplot as plt
import sys

f = open("../results/dd/imdbgraphdd.txt", "r")
matrice = f.readlines()
gradi=[]
nodi=[]
for j in matrice:
	tokens = j.split()
	gradi = gradi +[tokens[0]]
	nodi = nodi + [tokens[1]]
	
plt.plot(gradi, nodi, 'bx')
plt.xscale('log')
plt.yscale('log')



plt.grid(True)
plt.xlabel('Degree')
plt.ylabel('# of Persons')
plt.title('IMDb collaboration graph - Degree Distribution')
plt.show()

