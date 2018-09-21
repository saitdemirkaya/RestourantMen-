
public class Salami implements InterfacePizza{
	int price=3;
	 String explanation = "Salami ";
	 Salami(){
	 		explanation();
	 	}
 	public Salami(InterfacePizza dekor) {
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