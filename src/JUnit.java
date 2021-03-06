import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  Author: E. Bailey Galacci
 *  Date: 4/24/2018
 *  Overview: This program makes change using a nested class with a method with inputs coinArray and amount
 */
public final class JUnit {
   
    public static Change test;
    
    public static void testEmptyArray() {
        int array[] = {};
        try{
        if(array.length == 0) {
            throw new IllegalArgumentException("Array of size 0 is not allowed");            
        }
        
        } catch(IllegalArgumentException e){
            System.out.println("Array of size 0 is not allowed\n");
        }
    }
    
    public void initialize(int[] coinArray, int change) {
        test = new Change(coinArray, change);
    }
    
    public static void testCase1() {
        int coinValues[] = {1, 5, 10, 25};
        int change = 45;
        
        JUnit testUnit = new JUnit();
        
        testUnit.initialize(coinValues, change);
    }
    
    public static void testCase2() {
        int coinValues[] = {1, 5, 10, 25};
        int change = 20;
        
        JUnit testUnit = new JUnit();
        
        testUnit.initialize(coinValues, change);
    }
    
    public static void testCase3() {
        int coinValues[] = {1, 5, 10, 21, 25};
        int change = 63;
        
        JUnit testUnit = new JUnit();
        
        testUnit.initialize(coinValues, change);
    }
    
    
    public class Change{
        private int count, newCoin;
        int[] minCoins; 
        int[] coinsUsed;
            
        public Change(int[] coinArray, int change){
            minCoins = new int[change+1]; 
            coinsUsed = new int[change+1];
            
            dpMakeChange(coinArray, change);
        }
        
        public void dpMakeChange(int[] coinArray, int change){
            
            
            Arrays.fill(minCoins, 0);
            Arrays.fill(coinsUsed, 0);
            
            for(int i=0; i<=change; i++){       // i tracks our current cent count, starting at 0, ending at change (the goal case)
                count = i;
                newCoin = 1;
                int k = 0;
                // find out how many coins are smaller than 
                for(int j=0; j<coinArray.length; j++){
                    if(coinArray[j] < i) k++;
                }
                // j is now our marker in coinArray for the values we want to observe
                
                for(int j=0; j<k; j++){ // check for each coin 
                    if(minCoins[i-coinArray[j] ] + 1 < count){
                        count = minCoins[i-coinArray[j] ] + 1;     // update the number of coins used to be the previous solution + 1
                        newCoin = coinArray[j];                    // update the value of the newest coin used
                    } // end if
                } // end inner for
                
                // we have now found the minimum number of coins to give us change for i
                minCoins[i] = count;        // fill in the slot for minCoins array
                coinsUsed[i] = newCoin;     // 
            }
            
            printCoins(coinsUsed, change);
            
            return;
        }
        
        public void printCoins(int[] coinsUsed, int change){
            int coin = change;
            int thisCoin = 0;
            
            while(coin > 0){
                thisCoin = coinsUsed[coin];
                System.out.printf("%d, ", thisCoin);
                coin = coin - thisCoin;
            }
        }
    
    }
    
    
}
