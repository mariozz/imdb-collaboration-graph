#! /usr/bin/python
import matplotlib.pyplot as plt
import sys

f = open("../results/pageranks/imdbgraphrankpow.ord", "r")
matrice = f.readlines()
ranks=[]
x=[]
for j in range (0,len(matrice)):
	tokens = matrice[j].split()
	x = x + [j]
	ranks = ranks + [tokens[1]]
	
plt.plot(x, ranks, 'rx')
#plt.xscale('log')
#plt.yscale('log')



plt.grid(True)
plt.ylabel('Ranks')
plt.show()
