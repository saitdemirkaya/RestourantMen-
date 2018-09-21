
public class Onion implements InterfacePizza{
	int price=2;
	String explanation = "Onion ";
	 Onion(){
	 		explanation();
	 	}
 	public Onion(InterfacePizza dekor) {
 		price += dekor.cost();
 		explanation+=dekor.explanation();
 	}
	public int cost() {
		return price;
	}
	public String explanation() {
		return explanation;
	}
}