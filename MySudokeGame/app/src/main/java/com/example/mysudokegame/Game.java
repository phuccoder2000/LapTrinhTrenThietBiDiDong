package com.example.mysudokegame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Switch;

// khai bao ham class game
public class Game extends Activity {
    public static final String KEY_DIFFICULTY = "com.example.mysudokegame.dificulty";
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_HARD = 2;
    private int puzzle[];
    private PuzzleView puzzleView;
    private final String easyPuzzle =
            "360000000004230800000004200070460003820000014500013020001900000007048300000000045";
    private final String mediumPuzzle =
            "650000070000506000014000005007009000002314700000700800500000630000201000030000097";
    private final String hardPuzzle =
            "009000000080605020501078000000000700706040102004000000000720903090301080000000600";
   @Override
    protected void  onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       int diff = getIntent().getIntExtra(KEY_DIFFICULTY,DIFFICULTY_EASY);
       puzzle = getPuzzle(diff);
       calculateUsedTiles();
       puzzleView = new PuzzleView(this);
       setContentView(puzzleView);
       puzzleView.requestFocus();
   }
    boolean setTileIfValid(int x,int y,int value){
       int titles[] = getUsedTiles(x,y);
       if (value != 0){
           for (int tile : titles){
               if (tile == value){
                   return false;
               }
           }
       }
       setTile(x,y,value);
       calculateUsedTiles();
       return true;
    }
    private  final int used[][][] = new int [9][9][];
   protected int[] getUsedTiles(int x,int y){
       return  used[x][y];
   }
    private void calculateUsedTiles() {
       for (int x = 0;x < 9;x++){
           for (int y =0; y < 9; y++){
               used[x][y]=calculateUsedTiles(x,y);
           }
       }
    }
    private int[] calculateUsedTiles(int x,int y){
       int c[] = new  int [9];
       for (int i = 0;i <9 ; i++){
           if (i == y){
               continue;
           }
           int t = getTile(x , i);
           if (t !=0){
               c[t-1] = t;
           }
       }
        for (int i = 0;i <9 ; i++){
            if (i == x){
                continue;
            }
            int t = getTile(i , y);
            if (t !=0){
                c[t-1] = t;
            }
        }
        int startx = (x/ 3)*3;
        int starty = (y/ 3)*3;
        for (int i = startx; i < startx + 3;i++){
            for (int j = starty; j < starty + 3;j++){
                if (i == x && j == y){
                    continue;
                }
                int t = getTile(i,j);
                if (t != 0){
                    c[t-1] = t;
                }
            }
        }
       //compress
        int nused = 0;
        for (int t : c){
            if (t != 0){
                nused++;
            }
        }
        int c1[] = new int[nused];
        nused = 0;
        for (int t : c){
            if (t != 0){
                c1[nused++] = t;
            }
        }
        return c1;
    }
    //
    static protected  int[] fromPuzzleString(String string){
       int[] puz = new int[string.length()];
       for (int i = 0; i <puz.length;i++){
           puz[i] = string.charAt(i)- '0';
       }
       return puz;
    }
    private int[] getPuzzle(int diff){
       String puz;
        switch (diff){
            case 0:
            default:
                puz = "360000000004230800000004200070460003820000014500013020001900000007048300000000045";
                break;
            case 1:
                puz = "650000070000506000014000005007009000002314700000700800500000630000201000030000097";
                break;
            case 2:
                puz = "009000000080605020501078000000000700706040102004000000000720903090301080000000600";
        }

        return fromPuzzleString(puz);
    }
    private int getTile(int x,int y){
       return puzzle[y*9+x];
    }
    private void setTile(int x,int y,int value){
        puzzle[y*9+x]= value;
    }
    protected String getTileString(int x, int y) {
        int v = this.getTile(x, y);
        return v == 0 ? "" : String.valueOf(v);
    }

}
