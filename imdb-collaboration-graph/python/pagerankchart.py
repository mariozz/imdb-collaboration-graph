#! /usr/bin/python
import matplotlib.pyplot as plt
import sys

f = open("../results/pageranks/imdbgraphrankpow.res", "r")
w = open("../results/pageranks/imdbgraphrankpow.res.approx", "w")
matrice = f.readlines()
ranks=[]
x=[]

print(round(float(6.032755206358235E-8),8))
print(round(float(4.830324610696718E-5),8))
for rank in matrice:
	approx = round(float(rank),8)
	w.write(str(approx)+"\n")
	
f.close()
w.close()
#plt.plot(x, ranks, 'rx')
#plt.xscale('log')
#plt.yscale('log')



#plt.grid(True)
#plt.ylabel('Ranks')
#plt.show()
