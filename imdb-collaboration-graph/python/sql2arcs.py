from imdb import IMDb
import sys,commands

totalmovies = 1986952

#connect to DB
ia = IMDb('sql', uri='mysql://root:ingdis@localhost/imdb') #local mysql DB
#ia = IMDb() #online DB

#open results
results = open("../results/arcslists/imdbgrapharcslistfromdb.txt", 'w')

i=0
#for every movie
for movie_id in range(1,totalmovies+1):
	i = i+1
	mod = i%100
	if(mod == 0):
		print("Movie "+str(i)+" of "+str(totalmovies))
	
	#get movie from the DB
	movie = ia.get_movie(movie_id)
	
	#given a movie get cast, director, writer from DB
	cast= movie.get('cast')
	director = movie.get('director')
	writer = movie.get('writer')
	people=[]

	#add every person in cast, director, writer to the people array 
	try:
		for person in cast:
			people = people+[person.personID]		
	except:
		pass

	try:
		for person in director:
			people = people+[person.personID]
	except:
		pass

	try:	
		for person in writer:
			people = people+[person.personID]
	except:
		pass

	#create arcs between people working in the same movie (i.e in the people array)
	k=0
	for idd in people:
		k=k+1
		for j in range (k,len(people)):
			results.write(str(idd)+" "+str(people[j])+"\n")
			
results.close()