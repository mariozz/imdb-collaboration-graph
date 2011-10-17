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
	
plt.plot(gradi, nodi, 'rx')
plt.xscale('log')
plt.yscale('log')



plt.grid(True)
plt.xlabel('Degree')
plt.ylabel('# of Person')
#plt.legend(title='Probability',loc=4)
#plt.title('Number of received messages with Gossiping(p,k)')
plt.show()

