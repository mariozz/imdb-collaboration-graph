#! /usr/bin/python
#from pylab import *
import matplotlib.pyplot as plt
import sys

f = open("../results/imdbgraphdd.txt", "r")
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
plt.xlabel('Grado')
plt.ylabel('# Persone')
#plt.legend(title='Probability',loc=4)
#plt.title('Number of received messages with Gossiping(p,k)')
plt.show()

