import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent extends DevelopmentAgent {


    public static void drawSnake(int[][] arr, String p, int n) {
        String[] out = p.split(" ");

        for (int i = 0; i < out.length - 1; i++) {
            arr = drawLine(arr, out[i], out[i + 1], n);
        }


    }
    public static int[][] drawLine(int[][] arr,String a,String b,int n){
        String[] p1=a.split(",");
        String[] p2=b.split(",");
        int x1=Integer.parseInt(p1[0]);
        int x2=Integer.parseInt(p2[0]);
        int y1=Integer.parseInt(p1[1]);
        int y2=Integer.parseInt(p2[1]);

        if(x1==x2) {
            while(y1<y2) {
                arr[y1][x1]=n;
                y1++;
            }
        }
        if(y1==y2) {
            while(x1<x2) {
                arr[y1][x1]=n;
                x1++;
            }
        }
        if(y1==y2){
            while(x1>=x2){
                arr[y1][x1]=n;
                x1--;
            }
        }
        if(x1==x2) {
            while(y2<=y1) {
                arr[y1][x1]=n;
                y1--;
            }
        }

        return arr;
    }

    public static int findMove(String a, String p) {
        int move=4;
        String[] z = a.split(",");
        String[] z1 = p.split(",");
        int x_fruit = Integer.parseInt(z1[1]);
        int x_snake = Integer.parseInt(z[1]);
        int y_fruit = Integer.parseInt(z1[0]);
        int y_snake = Integer.parseInt(z[0]);
        if (x_fruit == x_snake && y_fruit > y_snake) {
            move=1;
        } else if (x_fruit > x_snake && y_fruit == y_snake) {
            move=3;
        } else if (x_fruit == x_snake && y_fruit < y_snake) {
            move=0;
        } else if (x_fruit < x_snake && y_fruit == y_snake) {
            move=2;
        }
        return move;
    }


    public static LinkedList<String> backtrack(String [][]arr, int i, int j, int y, int x) {
        LinkedList<String> list = new LinkedList<>();
        boolean b=true;
        while (b) {
            String cord = arr[y][x];
            int curr_i=Integer.parseInt(cord.split(",")[0]);
            int curr_j=Integer.parseInt(cord.split(",")[1]);
            y=curr_i;
            x=curr_j;
            list.add(cord);
            if (curr_i==i && curr_j==j){
                b=false;
            }

        }
        return list;
    }
    public static void addParent(String [][]arr,String parent,int i,int j){
        arr[i][j]=parent;

    }
    public  static boolean isZero(int [][]arr,int i,int j){

        return arr[i][j] == 0;

    }

    public  static boolean isOne(int [][]arr,int i,int j){

        return arr[i][j] == -1;

    }
    public static LinkedList<String> show(int arr[][],int i,int j,String apple){
        LinkedList<String>list1=new LinkedList<>();
        int aa[][]=arr;
        int i_apple=Integer.parseInt(apple.split(",")[0]);
        int j_apple=Integer.parseInt(apple.split(",")[1]);
        int i_head=i;
        int j_head=j;
        Queue<String>queue=new LinkedList<>();

        String [][]arr1=new String[arr.length][arr.length];
        for (int ii=0;ii< arr.length;ii++){
            for (int jj=0;jj<arr[i].length;jj++) {
                arr1[ii][jj]="0,0";
            }
        }
        queue.add(i+","+j);
        arr[i][j]=-1;


        while (true) {


            if (j < arr.length - 1 && isZero(arr, i, j + 1)) {
                String left = i + "," + (j + 1);
                String parent = i + "," + j;
                addParent(arr1, parent, i, j + 1);
                queue.add(left);
                arr[i][j + 1] = -1;
            }
            if (i > 0 && isZero(arr, i - 1, j)) {
                String up = (i - 1) + "," + j;
                queue.add(up);
                String parent = i + "," + j;
                addParent(arr1, parent, i - 1, j);
                arr[i - 1][j] = -1;
            }
            if (j > 0 && isZero(arr, i, j - 1)) {
                String right = i + "," + (j - 1);
                queue.add(right);
                String parent = i + "," + j;
                addParent(arr1, parent, i, j - 1);
                arr[i][j - 1] = -1;
            }
            if (i < arr.length - 1 && isZero(arr, i + 1, j)) {
                String down = (i + 1) + "," + j;
                queue.add(down);
                String parent = i + "," + j;
                addParent(arr1, parent, i + 1, j);
                arr[i + 1][j] = -1;
            }
            queue.poll();
            if (queue.size()!=0) {
                String p[] = queue.peek().split(",");
                i = Integer.parseInt(p[0]);
                j = Integer.parseInt(p[1]);

            }
            if (i==i_apple && j==j_apple){
                list1 = backtrack(arr1, i_head, j_head, i_apple, j_apple);
                break;
            }  if (queue.size()==0){
                break;
            }

        }

        if (queue.size()==0){
            return list1;
        }
        else {
            return list1;
        }
    }
    public static LinkedList<String>bfs2(int [][]arr,int i,int j,String tail){
        LinkedList<String>list1=new LinkedList<>();
        int i_tail=Integer.parseInt(tail.split(",")[0]);
        int j_tail=Integer.parseInt(tail.split(",")[1]);
        int i_head=i;
        int j_head=j;
        Queue<String>queue=new LinkedList<>();

        String [][]arr1=new String[arr.length][arr.length];
        for (int ii=0;ii< arr.length;ii++){
            for (int jj=0;jj<arr[i].length;jj++) {
                arr1[ii][jj]="0,0";
            }
        }
        queue.add(i+","+j);
        while(true) {

            arr[i][j] = 0;
            if (j < arr.length - 1 && isOne(arr, i, j + 1)) {
                String left = i + "," + (j + 1);
                String parent = i + "," + j;
                addParent(arr1, parent, i, j + 1);
                queue.add(left);
                arr[i][j + 1] = 0;
            }
            if (i > 0 && isOne(arr, i - 1, j)) {
                String up = (i - 1) + "," + j;
                queue.add(up);
                String parent = i + "," + j;
                addParent(arr1, parent, i - 1, j);
                arr[i - 1][j] = 0;
            }
            if (j > 0 && isOne(arr, i, j - 1)) {
                String right = i + "," + (j - 1);
                queue.add(right);
                String parent = i + "," + j;
                addParent(arr1, parent, i, j - 1);
                arr[i][j - 1] = 0;
            }
            if (i < arr.length - 1 && isOne(arr, i + 1, j)) {
                String down = (i + 1) + "," + j;
                queue.add(down);
                String parent = i + "," + j;
                addParent(arr1, parent, i + 1, j);
                arr[i + 1][j] = 0;
            }
            queue.poll();
            if (queue.size()!=0) {
                String p[] = queue.peek().split(",");
                i = Integer.parseInt(p[0]);
                j = Integer.parseInt(p[1]);
            }
            if (i==i_tail && j==j_tail){
                list1 = backtrack(arr1, i_head, j_head, i_tail, j_tail);
                break;
            }  if (queue.size()==0){
                break;
            }

        }

        if (queue.size()==0){
            return list1;
        }
        else {
            return list1;
        }
    }

    public static int findPath(int [][]arr,int i,int j,int y,int x,String tail){

        String major_target="";
        int [][]arr_back=arr;
        LinkedList<String>list1=new LinkedList<>();
        LinkedList<String>list=new LinkedList<>();
        String target2=tail;
        int i_head=i;
        int j_head=j;
        String target=y+","+x;
        list=show(arr, i, j, target);
        if (list.size()==0) {
            list1=bfs2(arr_back,i_head,j_head,target2);
            major_target=target2;

        }
        else {
            list1=list;
            major_target=target;

        }
        int move=5;
        list1.add(0,major_target);

       if (list1.size()<=1 || list1.size()>80){
            int n=lookForNext(arr,i_head,j_head);
            move=n;
            return move;
        }  else {
            move = findMove(list1.getLast(), list1.get(list1.size() - 2));
            return move;
       }

    }

    private static int lookForNext(int[][] arr, int i_head, int j_head) {
        if (i_head < arr.length - 1-1 && (arr[i_head+1+1][j_head]==-1 || arr[i_head+1+1][j_head]==0)){
            return 1;
        }   if (j_head < arr.length -1- 1 && (arr[i_head][j_head+1+1]==-1 || arr[i_head][j_head+1+1]==0)){
            return 3;
        }
          if (j_head > 1 && (arr[i_head][j_head-1-1]==-1 || arr[i_head][j_head-1-1]==0)){
            return 2;
        }
          if (i_head > 1 && (arr[i_head-1-1][j_head]==-1 || arr[i_head-1-1][j_head]==0)){
            return 0;
        }
       else {
           return 4;
          }
    }


    public static String findString (String a){
        String cord = "";
        String[] p = a.split(" ");
        ArrayList<String> aa = new ArrayList<>();
        for (int ii = 0; ii < p.length; ii++) {
            aa.add(p[ii]);

        }
        if (aa.get(0).equals("alive")) {
            for (int jj = 3; jj < aa.size(); jj++) {
                cord = cord + aa.get(jj) + " ";
            }
        }
        return cord;

    }
    public static void coverHead(int[][]arr,int i,int j) {
    	 if (j < arr.length - 1 && isZero(arr, i, j + 1)) {
             
             arr[i][j + 1] = -1;
         }
         if (i > 0 && isZero(arr, i - 1, j)) {
            
             arr[i - 1][j] = -1;
         }
         if (j > 0 && isZero(arr, i, j - 1)) {
            
             arr[i][j - 1] = -1;
         }
         if (i < arr.length - 1 && isZero(arr, i + 1, j)) {
           
             arr[i + 1][j] = -1;
         }
    	
    }
 public static void addPoints(ArrayList<String> heads, int[][] matrix) {
		
		for(String head:heads) {
			int j=Integer.parseInt(head.split(",")[1]);
			int i=Integer.parseInt(head.split(",")[0]);
			//System.out.println("log"+head);
			
			if(i>0 && matrix[i-1][j]==0) {
				matrix[i-1][j]=10;
			}
			if(j>0 && matrix[i][j-1]==0) {
				matrix[i][j-1]=10;
			}
			if(i<matrix.length-1 && matrix[i+1][j]==0) {
				matrix[i+1][j]=10;
			}
			if(j<matrix.length-1 && matrix[i][j+1]==0) {
				matrix[i][j+1]=10;
			}
			
		}
		// TODO Auto-generated method stub
		
	}


    public static void main (String args[]){
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    @Override
    public void run () {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);

            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }
                int[][] arr = new int[50][50];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length; j++) {
                        arr[i][j] = 0;
                    }
                }

                String apple1 = line;
                String tail="";
                //do stuff with apples

                ArrayList<String>heads=new ArrayList<>();
                for (int zombie = 0; zombie < 6; zombie++) {
                    String zombieLine = br.readLine();
                    drawSnake(arr, zombieLine, zombie+10);
                    String[]s=zombieLine.split(" ");
                    heads.add(s[0]);
                    
                  
                }
               
                
                
                addPoints(heads, arr);
                heads.clear();
                int i_head = 0;
                int j_head = 0;
                int i_apple = Integer.parseInt(apple1.split(" ")[1]);
                int j_apple = Integer.parseInt(apple1.split(" ")[0]);
                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();

                    drawSnake(arr, findString(snakeLine), i+20);
                    if (i == mySnakeNum) {
                        //hey! That's me :)
                        String[] pp = snakeLine.split(" ");
                        i_head = Integer.parseInt(pp[3].split(",")[1]);
                        j_head = Integer.parseInt(pp[3].split(",")[0]);
                        tail=pp[pp.length-1];
                    }
                    //do stuff with other snakes
                }

                //finished reading, calculate move:
            
                int move = findPath(arr, i_head, j_head, i_apple, j_apple,tail);
                System.out.println(move);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
