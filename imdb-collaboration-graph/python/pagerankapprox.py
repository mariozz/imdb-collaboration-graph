#! /usr/bin/python
import matplotlib.pyplot as plt
import sys

f = open("../results/pageranks/imdbgraphrankpow.res", "r")
w = open("../results/pageranks/imdbgraphrankpow.res.approx", "w")
ranks = f.readlines()

for rank in ranks:
	approx = round(float(rank),8)
	w.write(str(approx)+"\n")
	
f.close()
w.close()