import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Order implements IDataAccessObject{
	ArrayList<String> elementsOrder = new ArrayList<>();
	
	@Override
	public int getByID(int ID) {
		String splitBy = " ";
		for(int i=0;i<elementsOrder.size();i++) {
			String[] splitSpace = ((String) elementsOrder.get(i)).split(splitBy);
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
		writer.println("Order "+ID+" removed");
		elementsOrder.remove(index);
		return false;
	}

	@Override
	public void add(Object object) {
		elementsOrder.add((String)object);		
	}

	@Override
	public void update(Object object){// new order
		
	}

	@Override
	public void getALL(PrintWriter writer) {
		
	}
	public static void writeFileSortByID(ArrayList<String> array) throws IOException {/* Insertion Sort, writing file*/
		FileWriter writer = new FileWriter("order.txt");// saving file
        StringBuilder sb = new StringBuilder();
    	int[] arr = new int[array.size()];
    	for(int j=0;j<array.size();j++) {
    		String[] splitNewLine = ((String) array.get(j)).split("\n");   		
    		String[] splitSpace = splitNewLine[0].split(" ");
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
	        	sb.append("Order: ");
	        	sb.append(array.get(i)+"\n");
	        }
	        writer.write(sb.toString());
            writer.close();
        }
	public void addPizza(int ID,String addingLine) {
		String splitBy = " ";	
    	for(int i=0;i<elementsOrder.size();i++) {
    		String[] splitSpace = ((String) elementsOrder.get(i)).split(splitBy);
    		int tempID = Integer.parseInt(splitSpace[0]);
    		if(tempID==ID) {
    			if(addingLine.equals("")) {
    				String small = (String) elementsOrder.get(i);
    				small+="\n"+"SoftDrink";
    				elementsOrder.set(i, small);
    			}else {
    				String small = (String) elementsOrder.get(i);
    				small+="\n"+addingLine;
    				elementsOrder.set(i, small);
    			}
    				
    		}
    	}
	}
	public void payCheck(int ID,PrintWriter writer) throws ClassNotFoundException {
		String splitBy = " ";
		String splitNewLine="\n";
		
		int total=0;
		int index=(int) getByID(ID);
		String[] splitLine = ((String) elementsOrder.get(index)).split(splitNewLine);
		for(int k=1;k<splitLine.length;k++) {
			writer.print("\t");
			int totalLine=0;
			String[] splitSpace = splitLine[k].split(splitBy);
			InterfacePizza className;
			if(splitSpace[0].equals("AmericanPan")) {
				totalLine+=5;
				writer.print(splitSpace[0]+" ");
			}else if(splitSpace[0].equals("Neapolitan")){
				totalLine+=10;
				writer.print(splitSpace[0]+" ");
			}else {/* soft drink*/
				totalLine+=2;
				writer.print(splitSpace[0]+" ");
			}
			for(int i=1;i<splitSpace.length;i++) {
				className = howdy(splitSpace[i]);
				totalLine+=className.cost();
				writer.print(splitSpace[i]+" ");
			}
			writer.println(totalLine+"$");
			total+=totalLine;
		}
		writer.println("\tTotal: "+total+"$");		
	}
	public static InterfacePizza howdy(String whichClass) {
		try {
			Class clazz = Class.forName(whichClass);
			return (InterfacePizza) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}