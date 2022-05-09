# Bot in warehouse



## Dictionary 
- [Grid](src/main/java/org/example/Grid.java) the surface of the space where we can store products (x,y)
- [Node](src/main/java/org/example/Node.java) for at each space in grid we create Node 
- [NodeLinks](src/main/java/org/example/NodeLink.java) each node has 4 links to neighbour nodes.
- [Stack](src/main/java/org/example/Stack.java) in each node we have stack which is third dimension (n)
- Index  each Node contains his index.
 
 
![image](https://user-images.githubusercontent.com/26069406/167502621-6aedf731-4cf5-44cf-ab92-0b06e573e9bb.png)




# Description

  Program based on files creates Grid, for first data-package it creates Grid 4-3 , Stack Deepth of 3 with indexes:
  HHSH
  HBHH
  HHOS
  Additionaly program puts string products in correct places for example line: 
  P1 3 2 1
  Takes product "P1" put it to (3,2)Node at deepth 1.
  
  ![image](https://user-images.githubusercontent.com/26069406/167506302-68ad0d70-5072-43ea-8f39-79d31fe401fd.png)



  
  
  
  







