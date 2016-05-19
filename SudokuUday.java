import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Wrapper{
	boolean isValid;
	int[][] sudoku;
}
public class SudokuUday {

	public static ArrayList<Integer> possibleValForRow(int sudoku[][], ArrayList<Integer> values, int row, int col){
		for(int j=0;j<col;j++){
			values.remove(new Integer(sudoku[row][j]));
		}
		return values;
	}
	
	public static ArrayList<Integer> possibleValForColumn(int sudoku[][], ArrayList<Integer> values, int row, int col){
		for(int i=0;i<row;i++){
			values.remove(new Integer(sudoku[i][col]));
		}
		return values;
	}
	
	public static ArrayList<Integer> possibleValForBlock(int sudoku[][], ArrayList<Integer> values, int row, int col){
		int blockNum = getBlockNum(row, col);
		switch(blockNum){
		case 1:
			//System.out.println("case 1");
			for(int i=0;i<2;i++){
				for(int j=0;j<2;j++){
					values.remove(new Integer(sudoku[i][j]));
					//System.out.println("In case 1: "+values.toString());
				}
			}
			break;
		case 2:
			//System.out.println("case 2");
			for(int i=0;i<2;i++){
				for(int j=2;j<4;j++){
					values.remove(new Integer(sudoku[i][j]));
					//System.out.println("In case 2: "+values.toString());
				}
			}
			break;
		case 3:
			//System.out.println("case 3");
			for(int i=2;i<4;i++){
				for(int j=0;j<2;j++){
					values.remove(new Integer(sudoku[i][j]));
					//System.out.println("In case 3: "+values.toString());
				}
			}
			break;
		case 4:
			//System.out.println("case 4");
			for(int i=2;i<4;i++){
				for(int j=2;j<4;j++){
					values.remove(new Integer(sudoku[i][j]));
					//System.out.println("In case 4: "+values.toString());
				}
			}
			break;
		default:
			System.out.println("getBlockNum() is not working properly");
		}
		return values;
	}
	
	public static int getBlockNum(int row, int col){
		if(row<2&&col<2)
			return 1;
		else if(row<2&&col>=2){
			return 2;
		}
		else if(row>=2&&col<2){
			return 3;
		}
		else{
			return 4;
		}
	}
	
	public Wrapper isSudokuGenerated(int[][] sudoku){
		Random rand = new Random();
		ArrayList<Integer> all;
		ArrayList<Integer> possible;
		String num = "";
		boolean flag = false;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				all = new ArrayList<>(Arrays.asList(1,2,3,4));
				//System.out.println("i: "+i+" j: "+j);
				possible = possibleValForRow(sudoku, all, i, j);
				//System.out.println("possible: "+possible.toString());
				possible = possibleValForColumn(sudoku, possible, i, j);
				//System.out.println("possible: "+possible.toString());
				possible = possibleValForBlock(sudoku, possible, i, j);
				//System.out.println("possible: "+possible.toString());
				//System.out.println("possible size: "+possible.size());
				if(possible.size()==0){
					flag = true;
					break;
				}
				sudoku[i][j] =  possible.get(rand.nextInt(possible.size()));
				num += sudoku[i][j];
				//System.out.println("numbers: "+num);
			}	
		}
		Wrapper w = new Wrapper();
		if(flag){
			w.isValid = false;
			
		}
		else
			w.isValid= true;	
		
		w.sudoku = sudoku;
		return w;
	}
	
	public int[][] generateSudoku(){
		Wrapper w = isSudokuGenerated(new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
		// printSudoku(w.sudoku);
		int count = 0;
		while(!w.isValid){
			w = isSudokuGenerated(new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
			// printSudoku(w.sudoku);
			count++;
			if(count==10)
				break;
		}
		return w.sudoku;
	}
	public void printSudoku(int[][] sudoku){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.print(sudoku[i][j]+ "  ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SudokuUday obj = new SudokuUday();
		int[][] sudoku = obj.generateSudoku();
		System.out.println("After running the code, the following suduko table is generated");
		obj.printSudoku(sudoku);
		System.out.println("we can get a different type of sudoku table by running the code again ");
	}

}