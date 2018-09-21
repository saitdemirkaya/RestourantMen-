import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Customer implements IDataAccessObject{
	
	ArrayList<String> elements = new ArrayList<>();
	
	@Override
	public int getByID(int ID) {
		String splitBy = " ";
    	for(int i=0;i<elements.size();i++) {
    		String[] splitSpace = ((String) elements.get(i)).split(splitBy);
    		int tempID = Integer.parseInt(splitSpace[0]);
    		if(tempID==ID) {
    			return i;
    		}
    	}
    	
		return 0;
	}

	@Override
	public boolean deleteByID(int ID,PrintWriter writer) {
		int index = getByID(ID);
		String[] splitSpace = ((String) elements.get(index)).split(" ");
		writer.println("Customer "+ID+" "+splitSpace[1]+" removed");
		elements.remove(index);
		return false;
	}

	@Override
	public void add(Object object) {
		elements.add((String) object);
		update(object);
	}

	@Override
	public void update(Object object) {
		InstertionSortByName(elements);
		
	}

	@Override
	public void getALL(PrintWriter writer) {
		InstertionSortByName(elements);
		for (int i = 0; i < elements.size(); i++) {
            String value = elements.get(i);
            writer.println(value);
        }
	}
	public static void writeFileSortByID(ArrayList<String> array) throws IOException {// Insertion Sort, for writing file
		FileWriter writer = new FileWriter("customer.txt");// saving file
        StringBuilder sb = new StringBuilder();
    	String splitBy = " ";
    	int[] arr = new int[array.size()];
    	for(int j=0;j<array.size();j++) {
    		String[] splitSpace = ((String) array.get(j)).split(splitBy);
    		int ID = Integer.parseInt(splitSpace[0]);
    		arr[j]=ID;
    	}
    	int temp;
	        for (int k = 1; k < arr.length; k++) {
	            for(int j = k ; j > 0 ; j--){
	                if(arr[j] < arr[j-1]){
	                    temp = arr[j];/*change array number*/
	                    arr[j] = arr[j-1];
	                    arr[j-1] = temp;
	                    String small = (String) array.get(j);/*change input line*/
	                    array.set(j, array.get(j-1));
	                    array.set(j-1, small);
	                }
	            }
	        }
	        for (int i = 0; i < array.size(); i++) {
	        	sb.append(array.get(i)+"\n");
	        }
	        writer.write(sb.toString());
            writer.close();
        }
	public static void InstertionSortByName(ArrayList<String> array) {// Insertion Sort for Order.
		
    	String splitBy = " ";
    	String[] arr = new String[array.size()];
    	for(int j=0;j<array.size();j++) {
    		String[] splitSpace = ((String) array.get(j)).split(splitBy);
    		arr[j]=splitSpace[1];
    	}
    	String temp;
	        for (int k = 1; k < arr.length; k++) {
	            for(int j = k ; j > 0 ; j--){
	            	temp=arr[j];
	                if(temp.compareTo(arr[j - 1]) < 0){
	                    temp = arr[j];
	                    arr[j] = arr[j-1];
	                    arr[j-1] = temp;
	                    String small = (String) array.get(j);
	                    array.set(j, array.get(j-1));
	                    array.set(j-1, small);
	                }
	            }
	        }
        }

}
