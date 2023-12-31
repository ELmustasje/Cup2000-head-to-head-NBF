Cup2000 head to head backend webscrubing program that analyses norwegain badminton tournaments and creates a head to head statistics table i made as a hobby project.
This is not a finished program, i need to learn more about databases and frontend before i can launch a finished product.

The program consists of 5 classes used for finding information and one main class to controll. 

The get FindIDs class is for getting the ids that belong to the norweagian turnaments, since this system is used by he danish federation and the English federation aswell.
FindIDs has one function find ids witch takes a start id and an end id (depending on the timeframe you want, 2018-2023 is 8650 - 10600) and return an arrayList witch contians the ids thats belongs to norweaian tournaments.

The Class CreateTable will create a table witch has two voids: fulltable and Update table.
The fulltable is the dictionary witch contains all the players who have competed in these turnaments and their head to head stats.
Here you can use the .get() to search for specifig players so if i want to search for myself i search .get("Thomas Barth") and i will get all my head to head starts.
if i want to look at a specific opponent i can .get() their name aswell. So if i want to find my stats agains a rival i can do: fulltable.get("Thomas Barth).get("Andreas Holmedal").
this will get me the head to head between me and Andreas Holmedal during the period the tournament ids define.
The update table takes the Arraylist given by FindIDs and uses this to find matches and update results.

I have left an example in the main witch is my matches during the period 2018 - 2023.
