# Warehouse

  # Description
   WMS designed from the idea that huge saving in cost and time can be achieved by optimizing the performance of the warehouse by minimizing the manual work that has to be done.
## Dictionary 
- [Grid](src/main/java/org/example/Grid.java) the surface of the space where we can store products HashMap<(x,y),Node>
- [Node](src/main/java/org/example/Node.java) for at each space in grid we create Node 
- [NodeLinks](src/main/java/org/example/NodeLink.java) each node has 4 links to neighbour nodes.
- [Stack](src/main/java/org/example/Stack.java) in each node we have stack which is third dimension (n)
- Index  each Node contains his index.
 
 
![image](https://user-images.githubusercontent.com/26069406/167502621-6aedf731-4cf5-44cf-ab92-0b06e573e9bb.png)




# Setup

  Program based on Grid-1.txt creates Grid, for first data-package it creates Grid x,y , Stack Deepth of n with indexes:
  4 3 3 (x y n)
  HHSH
  
  HBHH
  
  HHOS
  
  Additionaly program puts string products in correct places for example line: 
  
  P1 3 2 1  (product x y n)
  
  Takes product "P1" put it to (3,2) Node at deepth 1.
  
  ![image](https://user-images.githubusercontent.com/26069406/167506302-68ad0d70-5072-43ea-8f39-79d31fe401fd.png)
  
  
  Next thing program does is opening job-1.txt 
  in this file we have 3 lines:
  
  1.line: bot starting coordninates
  
  2.line: station coordinates
  
  3.line: product - this product is our searched product.
  

  Knowing your storage configuration, program finds the fastest (time) route for the bot to from your chosen one
starting point on the grid to searched product and delivered it to the station
receiving at coordinates.

After program is done it writes results to output.txt file.
you can confirm correctness by reading comparing result and output file.
  
  
  
  
  
  



  
  
  
  







