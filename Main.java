import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
			
        PrintWriter writer = null;
        writer = new PrintWriter(new FileOutputStream("output.txt"));
        
		Customer customerObject = new Customer();
		File customer = new File("customer.txt"); /*customer.txt*/
        BufferedReader customer_reader = new BufferedReader(new FileReader(customer));
        customerFileRead(customer_reader,customerObject);
        
        Order orderObject = new Order();
        File order = new File("order.txt"); /*order.txt*/
        BufferedReader order_reader = new BufferedReader(new FileReader(order));
        orderFileRead(order_reader,orderObject);
        
        File input = new File(args[0]); /*input.txt*/
        BufferedReader input_reader = new BufferedReader(new FileReader(input));
        inputFileRead(input_reader,customerObject,orderObject,writer);
        writer.close();
        
        customerObject.writeFileSortByID(customerObject.elements);/*Update customer file*/
        orderObject.writeFileSortByID(orderObject.elementsOrder);/*Update order file*/
	}
	/**
	 * Reading Customer File and adding Customer List.*/
	public static void customerFileRead(BufferedReader customer,Customer customerObject) throws IOException {		
		String line = customer.readLine();		
		while(line!=null) {
			if(!line.isEmpty()) {/*for end of the file empty line*/
    			customerObject.add(line);
        	}			
			line = customer.readLine();
		}
	}
	/**
	 * Reading Order File and adding Order List.*/
	public static void orderFileRead(BufferedReader order,Order orderObject) throws IOException{
		
		String line = order.readLine();	
		int i=0;/*Control for new order */
		String  objectLine="";	
		while(line!=null) {				
			if(!line.isEmpty()) {/*for end of the file empty line*/
				String[] tokens=line.split(" ");
				if(tokens[0].equals("Order:")) {/*while to new order.*/
					if(i!=0) {/*except first order*/
						orderObject.add(objectLine);
						objectLine="";
					}
					i++;
					objectLine+=tokens[1]+" "+tokens[2];
				}else {/*Order's elements*/
					objectLine+="\n";
					for(int k=0;k<tokens.length;k++) {
						objectLine+=tokens[k]+" ";
					}
				}
        	}	
				line = order.readLine();
			
		}
		orderObject.add(objectLine);/* last order added*/
	}
	public static void inputFileRead(BufferedReader input,Customer customerObject,Order orderObject,PrintWriter writer) throws IOException, ClassNotFoundException{
		String line = input.readLine();		
		while(line!=null) {	
			String newLine="";
			if(!line.isEmpty()) {/*for end of the file empty line*/
				String[] tokens = line.split(" ");
				if(tokens[0].equals("AddCustomer")) {					
					int i;
					for(i=1;i<tokens.length;i++){/*read and of line*/
						newLine+=tokens[i]+" ";
					}
					customerObject.add(newLine);
					writer.println("Customer "+tokens[1]+" "+tokens[2]+" added");
					
				}else if(tokens[0].equals("RemoveCustomer")) {					
					int ID = Integer.parseInt(tokens[1]);
					customerObject.deleteByID(ID,writer);
					
				}else if(tokens[0].equals("List")){
					writer.println("Customer List:");
					customerObject.getALL(writer);
					
				}else if(tokens[0].equals("CreateOrder")) {
					String  objectLine="";
					writer.println("Order "+tokens[1]+" created");
					objectLine = tokens[1]+" "+tokens[2];
					orderObject.add(objectLine);
					
				}else if(tokens[0].equals("AddPizza")||tokens[0].equals("AddDrink")) {
					int ID = Integer.parseInt(tokens[1]);
						String addingLine="";
						if(tokens[0].equals("AddDrink")) {
							writer.println("Drink added to order "+ID);
						}else {
							writer.println(tokens[2]+" pizza added to order "+ID);
						}
						
							for(int k=2;k<tokens.length;k++) {
								addingLine+=tokens[k]+" ";
							}
						orderObject.addPizza(ID,addingLine);
				}else if(tokens[0].equals("PayCheck")) {
					int ID = Integer.parseInt(tokens[1]);
					writer.println("PayCheck for Order "+ID);
					orderObject.payCheck(ID,writer);
				}else if(tokens[0].equals("RemoveOrder")) {
					int ID = Integer.parseInt(tokens[1]);
					orderObject.deleteByID(ID,writer);
				}
        	}			
			line = input.readLine();
		}
	}
}