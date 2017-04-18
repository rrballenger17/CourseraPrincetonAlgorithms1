
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.lang.IllegalArgumentException;


public class Percolation {

	// 0 - n*n 
	private WeightedQuickUnionUF alg = null;

	private char[] states = null;

   //private char[] full = null;

	private int dimension = -1;


	private int getIndex(int row, int col){


		if(row < 1 || row > dimension){
			return -1;
		}


		if( col < 1 || col > dimension){
			return -1;
		}

		return (row-1)*dimension + col-1;

	}



	public Percolation(int n){

      if( n <= 0){
         throw new IllegalArgumentException();
      }

		alg = new WeightedQuickUnionUF(n * n);

		states = new char[n*n];

     // full = new char[n*n];

		for(int i=0; i<n*n; i++){
			states[i] = '0';
       //  full[i] = 0;
		}


		dimension = n;

	}               // create n-by-n grid, with all sites blocked




	public    void open(int row, int col) throws IndexOutOfBoundsException{

		//row--;
		//col--;

		Integer target = getIndex(row, col);

      if(target == - 1){
         throw new IndexOutOfBoundsException();
      }

      if(states[target] == '1'){
         return;
      }

		//System.out.println("OPENING" + target + "\n");

		states[target] = '1';


		Integer[] neighbors = new Integer[4];

		// above
		neighbors[0] = getIndex(row-1, col);

		// below
		neighbors[1] = getIndex(row+1, col);

		// left
		neighbors[2] = getIndex(row, col- 1);

		// right
		neighbors[3] = getIndex(row, col + 1);



		for(int i=0; i< 4; i++){

			int neighVal = neighbors[i];

			if(neighVal != -1){
				if(states[neighVal] == '1'){


               // find if components are the same first?

					alg.union(target, neighVal);

				}
			}

		}



		//(row - 1)*n + col;


	}    // open site (row, col) if it is not open already



   public boolean isOpen(int row, int col) throws IndexOutOfBoundsException{
   		//row--;
   		//col--;

   		int index = getIndex(row, col);

         if(index == - 1){
            throw new IndexOutOfBoundsException();
         }

   		return states[index] == '1';//alg.find(20);
   }  // is site (row, col) open?
   

   public boolean isFull(int row, int col) throws IndexOutOfBoundsException{

   		//row--;
   		//col--;

   		Integer target = getIndex(row, col);

         if(target == - 1){
            throw new IndexOutOfBoundsException();
         }

         if(states[target] == '0'){
            return false;
         }



   		boolean fullBool = false;

         int prevTested = -1;

   		for(int i=1; i<=dimension; i++){

            Integer top = getIndex(1, i);


            if(states[top] == '0'){
               prevTested = 0;
               continue;
            }

            if(prevTested == 1){
               //prevTested = 0;
               continue;
            }

            prevTested = 1;




   			if(alg.connected(target, top)){
   				fullBool = true;
   				break;
   			}

            //prevIden = findResult;
   		}


         //if(fullBool){
         //   full[target] = 1;
         //}


   		return fullBool;
   }  // is site (row, col) full?




   public     int numberOfOpenSites(){


   		int count = 0;
   		for(int i= 0; i< dimension*dimension; i++){
   			if(states[i] == '1'){
               count++;
            }

   		}

   		return count;
   }       // number of open sites
   


   public boolean percolates(){

   		boolean complete = false;

         int prevTested = -1;


         int[] components = new int[dimension];
         for(int i=0; i<dimension; i++){
            components[i] = -1;
         }


   		for(int i=1; i<=dimension; i++){


            int spot = getIndex(dimension, i);

            if(states[spot] == '0'){
               prevTested = 0;
               continue;
            }

            if(prevTested == 1){
               continue;
            }


            prevTested = 1;

            int compIden = alg.find(spot);

            for(int j=0; j<dimension; j++){
               if(components[j] == compIden){
                  continue;
               }
            }

   			//if(isFull(dimension, i)){
   			//	complete = true;
   			//	break;
   			//}
            components[i-1] = compIden;
   		}



         prevTested = -1;


         for(int i=1; i<=dimension; i++){


            int spot = getIndex(1, i);

            if(states[spot] == '0'){
               prevTested = 0;
               continue;
            }

            if(prevTested == 1){
               continue;
            }

            prevTested = 1;

            int compIden = alg.find(spot);

            for(int j=0; j<dimension; j++){

               if(compIden == components[j]){
                  return true;
               }
            }

         }




   		return complete;
   }              // does the system percolate?





   public static void main(String[] args){


   	Percolation p = new Percolation(3);

   	//p.test();

      /*

   	p.open(1, 2);
   	p.test();
   	System.out.printf("Percolates " + p.percolates()+ "\n");
   		
   	p.open(2, 2);
   	p.test();
   	System.out.printf("Percolates " + p.percolates()  + "\n");


   	p.open(3, 2);
   	p.test();
   	System.out.printf("Percolates " + p.percolates()  + "\n");
   		//p.open(2, 2);
   		//System.out.printf("Percolates " + p.percolates());

   		//p.open(3, 3);
   		//System.out.printf("Percolates " + p.percolates());

   	//System.out.printf(p.isOpen(1, 2) + "");
      */

   }   // test client (optional)




}