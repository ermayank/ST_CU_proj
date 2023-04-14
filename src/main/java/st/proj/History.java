package st.proj;
import java.util.ArrayList;
import java.util.Arrays;

public class History {
    private String[] movesHistory = {};


    public String[] getHistory(){
//        System.out.println(Arrays.toString(movesHistory));
        return movesHistory;

    };
    public void addToHistory(String s){

        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(movesHistory));
        arrayList.add(s);
        movesHistory = arrayList.toArray(movesHistory);
    };



}
