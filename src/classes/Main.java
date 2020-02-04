package classes;

import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Please enter the number of tests you want to run: ");
    	String numberOfTests = scanner.nextLine();
    	
    	try {
    		int numberOfTestsInt = Integer.parseInt(numberOfTests); 
    		InputRowColumn[] inputRowColumn = new InputRowColumn [numberOfTestsInt];

    		System.out.println("Please enter the row and columns for each test separated by a space, for example '5 5'");	
    		
    		for (int i =  0; i < numberOfTestsInt; i++) {
    			System.out.println("");	
    			System.out.println("Please enter the rows and columns for test " + i + ":");
    			
    			String s = scanner.nextLine();
    			String[] splited = s.split("\\s+");
    			inputRowColumn[i] = new InputRowColumn(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
    		}
    		
    		System.out.println("");	
    		for (int i =  0; i < numberOfTestsInt; i++) {  
    			makeTest(inputRowColumn[i].row, inputRowColumn[i].column);
    		}
    		
    	} catch (Exception e) {
    		System.out.println("Error in the execution input params.");
    	}
    }
    
    /* Iterate through array checking whether or not array has finished */
    public static boolean isArrayFinished(boolean[][] array, int rowSize, int columnSize) {
    	for(int i = 0; i < rowSize; i++) {
    		for(int j = 0; j < columnSize; j++) {
    			if (array[i][j] == false) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    
    public static boolean checkIfNextMoveIsWithinBound(int nextRow, int nextColumn, int rowSize, int columnSize) {
    	return (nextRow >= 0 && nextRow < rowSize) && (nextColumn >= 0 && nextColumn < columnSize);
    }
    
    public static String checkLastDirection(int direction) {
    	switch(direction) {
			case -1:
				return "U";
			case 0:
				return "R";
			case 1:
				return "D";
			case 2:
				return "L";
			default:
				return "Error";
		}
    }
    
    public static void makeTest(int row, int column) {
    	int rowSize = row;
        int columnSize = column;
        
        int currentRow = 0;
        int currentColumn = 0;
        
        boolean[][] array = new boolean[rowSize][columnSize];
        int directionAcc = 0;
        
        /* Check if the next movement is out of boundary or it is already visited */
        	/* If it is then change the direction and try again */
        /* If not then move (set value in array to true) */
        
        while(!isArrayFinished(array, rowSize, columnSize)) {
        	/* Start at 0,0 */
        	
        	array[currentRow][currentColumn] = true;
        	
        	int direction = directionAcc % 4;
        	int nextRow = 0;
        	int nextColumn = 0;
        	
        	switch(direction) {
        		case 0:
        			nextRow = currentRow;
        			nextColumn = currentColumn + 1;
        			break;
        		case 1:
        			nextRow = currentRow + 1;
        			nextColumn = currentColumn;
        			break;
        		case 2:
        			nextRow = currentRow;
        			nextColumn = currentColumn - 1;
        			break;
        		case 3:
        			nextRow = currentRow - 1;
        			nextColumn = currentColumn;
        			break;
        		default:
        			break;
        	}
        	
        	if (checkIfNextMoveIsWithinBound(nextRow, nextColumn, rowSize, columnSize)) {
        		if (!array[nextRow][nextColumn]) {
        			currentRow = nextRow;
        			currentColumn = nextColumn;
        			continue;
        		}
        	} 
        	
        	directionAcc++;
        }
        
        System.out.println(checkLastDirection((directionAcc % 4) -1));
    }
}
